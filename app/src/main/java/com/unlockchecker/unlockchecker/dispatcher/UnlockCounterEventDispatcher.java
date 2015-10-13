package com.unlockchecker.unlockchecker.dispatcher;

public interface UnlockCounterEventDispatcher {
    void onLock();
    void onUnlock();
}
