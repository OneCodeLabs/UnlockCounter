package com.unlockchecker.unlockchecker.helper;

import com.unlockchecker.unlockchecker.db.UnlockCounterDB;
import com.unlockchecker.unlockchecker.db.impl.SugarDB;

public class ReportManager {

    /**
     * Use an |UnlockCounterDB| to (maybe) generate a report. |forceGenerate| being true forces it
     * while a check is made otherwise to see if the day has changed.
     * TODO(gnardini): Add an enum or something to the Configuration file to see which database to
     * use.
     */
    public static void generateReport(boolean forceGenerate) {
        UnlockCounterDB unlockDB = new SugarDB();
        if (forceGenerate) {
            unlockDB.onDayChanged();
        } else {
            unlockDB.checkDayChange();
        }
    }

}
