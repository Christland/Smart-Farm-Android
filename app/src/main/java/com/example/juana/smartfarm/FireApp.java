package com.example.juana.smartfarm;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Juana on 07/04/2018.
 */

public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
