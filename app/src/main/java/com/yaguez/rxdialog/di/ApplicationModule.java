package com.yaguez.rxdialog.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Singleton @Module public class ApplicationModule {
  private final Context context;

  public ApplicationModule(final Context context) {
    this.context = context;
  }

  @Provides @Singleton Context provideContext() {
    return context;
  }
}
