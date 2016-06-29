package com.example.administrator.myapplication.tiledscrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.administrator.myapplication.R;

/**
 * Tiled scroll view main class.
 * <p/>
 * This class supports scrolling through a huge picture based on image tiles.
 * Most of the real "work" is done in the {@link TiledScrollViewWorker} class.
 *
 * @author Sebastian Roth <sebastian.roth@gmail.com>
 */
public class TiledScrollView extends FrameLayout {

    private TiledScrollViewWorker mScrollView;
    private ImageButton mBtnZoomDown;
    private ImageButton mBtnZoomUp;
    private boolean mZoomButtonsEnabled = true;

    public enum ZoomLevel {
        DEFAULT,
        LEVEL_1,
        LEVEL_2;

        public ZoomLevel upLevel() {
            switch (this) {
                case DEFAULT:
                    return LEVEL_1;
                case LEVEL_1:
                    return LEVEL_2;
                case LEVEL_2:
                    return LEVEL_2;
            }

            return this;
        }

        public ZoomLevel downLevel() {
            switch (this) {
                case DEFAULT:
                    return DEFAULT;
                case LEVEL_1:
                    return DEFAULT;
                case LEVEL_2:
                    return LEVEL_1;
            }

            return this;
        }
    }

    public TiledScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public TiledScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater lf = LayoutInflater.from(getContext());

        mScrollView = new TiledScrollViewWorker(getContext(), attrs);
        mScrollView.setOnZoomLevelChangedListener(new OnZoomLevelChangedListener() {
            public void onZoomLevelChanged(ZoomLevel newLevel) {
                updateZoomButtons();
            }
        });

        addView(mScrollView);

        final TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.asia_ivity_android_tiledscrollview_TiledScrollView);
        mZoomButtonsEnabled = a.getBoolean(R.styleable.asia_ivity_android_tiledscrollview_TiledScrollView_zoom_buttons, true);

        if (mZoomButtonsEnabled) {
            View btns = lf.inflate(R.layout.ll_tiledscroll_view_zoom_buttons, this, false);

            mBtnZoomDown = (ImageButton) btns.findViewById(R.id.btn_zoom_down);
            mBtnZoomUp = (ImageButton) btns.findViewById(R.id.btn_zoom_up);

            mBtnZoomDown.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mScrollView.zoomDown();
                }
            });
            mBtnZoomUp.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mScrollView.zoomUp();
                }
            });

            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            addView(btns, params);

            updateZoomButtons();
        }
    }

    public void addConfigurationSet(ZoomLevel level, ConfigurationSet set) {
        mScrollView.addConfigurationSet(level, set);

        updateZoomButtons();
    }

    private void updateZoomButtons() {
        if (mZoomButtonsEnabled) {
            if (!mScrollView.canZoomFurtherDown() && !mScrollView.canZoomFurtherUp()) {
                mBtnZoomDown.setVisibility(GONE);
                mBtnZoomUp.setVisibility(GONE);
            } else {
                mBtnZoomDown.setVisibility(VISIBLE);
                mBtnZoomUp.setVisibility(VISIBLE);
                mBtnZoomDown.setEnabled(mScrollView.canZoomFurtherDown());
                mBtnZoomUp.setEnabled(mScrollView.canZoomFurtherUp());
            }
        }
    }

    public void cleanupOldTiles() {
        mScrollView.cleanupOldTiles();
    }

    public void addMarker(int x, int y, String description) {
        mScrollView.addMarker(x, y, description);
    }

    public void setMarkerOnClickListener(OnClickListener listener) {
        mScrollView.setMarkerOnClickListener(listener);
    }
}
