package com.robodex;

import android.app.Application;
import android.content.Context;

public class Robodex extends Application {
    public static final boolean DEBUG = true;

    public static Context sAppContext;

    @Override
    public void onCreate() {
    	super.onCreate();
    	sAppContext = this.getApplicationContext();
    }
}
