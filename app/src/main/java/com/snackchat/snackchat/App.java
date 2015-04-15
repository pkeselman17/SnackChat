package com.snackchat.snackchat;

import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "wUrfxoU1iAUNYvw4ZVBEnmZSx0pESxh6SyoU20ZQ", "OeBtoezn1vexfQU9UmHO8aJSiX09WOQZfbxlplun");
    }
}
