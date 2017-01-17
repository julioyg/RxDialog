package com.yaguez.rxdialog.di;

import com.yaguez.rxdialog.BaseActivity;
import dagger.Module;
import dagger.Provides;

@PerActivity @Module public class ActivityModule {
  private final BaseActivity activity;

  public ActivityModule(final BaseActivity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity BaseActivity provideBaseActivity() {
    return activity;
  }
}
