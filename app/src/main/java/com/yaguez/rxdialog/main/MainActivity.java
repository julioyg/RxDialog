package com.yaguez.rxdialog.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.yaguez.rxdialog.BaseActivity;
import com.yaguez.rxdialog.R;
import com.yaguez.rxdialog.databinding.ActivityMainBinding;
import com.yaguez.rxdialog.di.ActivityModule;
import com.yaguez.rxdialog.di.ApplicationComponent;
import com.yaguez.rxdialog.di.PerActivity;
import dagger.Component;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {
  @Inject MainViewModel mainViewModel;

  @Override protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final ActivityMainBinding binding =
        DataBindingUtil.setContentView(this, R.layout.activity_main);

    inject();

    binding.setViewModel(mainViewModel);
  }

  private void inject() {
    DaggerMainActivity_MainComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(new ActivityModule(this))
        .build()
        .inject(this);
  }

  @PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
  public interface MainComponent {
    void inject(MainActivity activity);
  }
}
