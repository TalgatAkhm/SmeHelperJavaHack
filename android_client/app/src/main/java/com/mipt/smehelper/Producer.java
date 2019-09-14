package com.mipt.smehelper;

import android.util.Log;

public class Producer extends Thread {
    private final static String TAG = "ProducerTAG";
    private final static int DELEAY = 500;

    private int millsPassed = 0;
    private boolean isRunning;

    @Override
    public void run() {
        Log.d(TAG, "Starting producer thread");
        while (isRunning) {
            try {
                sleep(DELEAY);
                millsPassed += DELEAY;
                checkStates();
            } catch (Exception e) {
                Log.e(TAG, "Exception in Producer thread", e);
            }
        }
    }

    private void checkStates() {
        //TODO:: check notifications
    }

    public void stopThread() {
        isRunning = false;
    }
}
