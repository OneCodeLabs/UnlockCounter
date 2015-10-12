package com.unlockchecker.unlockchecker.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NavigationUtils {

    public static void jumpToClearingTask(Context context, Class clazz) {
        Intent intent = new Intent(context.getApplicationContext(), clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void jumpTo(Context context, Class clazz) {
        Intent intent = new Intent(context.getApplicationContext(), clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void jumpTo(Context context, Bundle extras, Class clazz) {
        Intent intent = new Intent(context.getApplicationContext(), clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(extras);
        context.startActivity(intent);
    }
}