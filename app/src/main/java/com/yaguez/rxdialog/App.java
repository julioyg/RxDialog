package com.yaguez.rxdialog;

import android.app.Application;
import com.yaguez.rxdialog.di.ApplicationComponent;
import com.yaguez.rxdialog.di.ApplicationModule;
import com.yaguez.rxdialog.di.DaggerApplicationComponent;

public class App extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();

    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
