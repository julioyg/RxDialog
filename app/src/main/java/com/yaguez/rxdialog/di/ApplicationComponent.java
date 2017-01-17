package com.yaguez.rxdialog.di;

import android.content.Context;
import com.yaguez.rxdialog.App;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {
  void inject(App app);

  Context context();
}
