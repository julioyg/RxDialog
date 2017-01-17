package com.yaguez.rxdialog;

import android.support.v7.app.AppCompatActivity;
import com.yaguez.rxdialog.di.ApplicationComponent;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class BaseActivity extends AppCompatActivity {
  private BehaviorSubject<Status> lifeCycleObservable = BehaviorSubject.create();

  public ApplicationComponent getApplicationComponent() {
    return ((App) getApplication()).getApplicationComponent();
  }

  @Override protected void onPause() {
    super.onPause();
    lifeCycleObservable.onNext(Status.ON_PAUSE);
  }

  @Override protected void onResume() {
    super.onResume();
    lifeCycleObservable.onNext(Status.ON_RESUME);
  }

  public Observable<Status> observeLifeCycle() {
    return lifeCycleObservable;
  }

  public enum Status {
    ON_PAUSE,
    ON_RESUME
  }
}
