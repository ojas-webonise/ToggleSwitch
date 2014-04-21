package com.ojas.toggleswitch;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.widget.SlidingDrawer;

@SuppressLint("HandlerLeak")
@SuppressWarnings("deprecation")
public class ToggleSwitch implements SlidingDrawer.OnDrawerScrollListener {

    private OnSwitchChange mOnSwitchChange;
    private SlidingDrawer mDrawer;
    private static boolean isPrevDrawerStateOpen;

    public ToggleSwitch (SlidingDrawer mDrawer, boolean isDrawerOpen, OnSwitchChange mOnSwitchChange){
        this.mOnSwitchChange = mOnSwitchChange;
        this.mDrawer = mDrawer;
        isPrevDrawerStateOpen = isDrawerOpen;
        //        if (isPrevDrawerStateOpen) {
        //            mDrawer.animateOpen();
        //        } else {
        //            mDrawer.animateClose();
        //        }
    }

    @Override
    public void onScrollEnded() {
        new Thread(mRunnable).start();
    }

    @Override
    public void onScrollStarted() {
    }

    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            // While the SlidingDrawer is moving; do nothing.
            while (mDrawer.isMoving())	{
                // Allow another thread to process its instructions.
                Thread.yield();
            }

            // When the SlidingDrawer is no longer moving; trigger mHandler.
            mHandler.sendEmptyMessage(0);
        }
    };

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (mDrawer.isOpened()) {
                // TODO: Case 1 - If the SlidingDrawer is completely opened...
                if (!isPrevDrawerStateOpen) {
                    mOnSwitchChange.switchOpened();
                    isPrevDrawerStateOpen = true;
                }
            }
            else {
                // TODO: Case 2 - If the SlidingDrawer is completely closed...
                if (isPrevDrawerStateOpen) {
                    mOnSwitchChange.switchClosed();
                    isPrevDrawerStateOpen = false;
                }
            }
        }
    };

    public interface OnSwitchChange{
        public void switchOpened();
        public void switchClosed();
    }

}
