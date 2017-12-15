package com.ncatz.jmed.dicoveringrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        getRealmObject().close();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("test1")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Realm getRealmObject() {
        return Realm.getDefaultInstance();
    }
}
