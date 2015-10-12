package com.unlockchecker.unlockchecker.dispatcher;

public interface UnlockCounterEventDispatcher {
    public void initialize();
    public void onLock();
    public void onUnlock();
}
