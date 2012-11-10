package com.robodex;

import android.app.Application;
import android.content.Context;

public class Robodex extends Application {
    public static final boolean DEBUG = true;

    public static final boolean ENFORCE_HASH_CHECK = false;

    public static final int ITEM_LIST = 1;

    public static Context sAppContext;

    @Override
    public void onCreate() {
    	super.onCreate();
    	sAppContext = this.getApplicationContext();
    }
}
