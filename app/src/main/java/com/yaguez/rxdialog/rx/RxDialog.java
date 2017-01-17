package com.yaguez.rxdialog.rx;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import com.yaguez.rxdialog.BaseActivity;
import com.yaguez.rxdialog.di.PerActivity;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

@PerActivity public class RxDialog {
  public static int BUTTON_POSITIVE = AlertDialog.BUTTON_POSITIVE;
  public static int BUTTON_NEGATIVE = AlertDialog.BUTTON_NEGATIVE;

  private final ActionTrigger<Dialog> actionTrigger = ActionTrigger.create();
  private final CompositeDisposable compositeSubscription = new CompositeDisposable();
  private final Activity activity;

  @Inject public RxDialog(final BaseActivity activity) {
    this.activity = activity;

    activity.observeLifeCycle().subscribe(activityStatus -> {
      if (activityStatus == BaseActivity.Status.ON_RESUME) {
        compositeSubscription.add(actionTrigger.observe().subscribe(this::showDialog));
      } else if (activityStatus == BaseActivity.Status.ON_PAUSE) {
        compositeSubscription.clear();
      }
    });
  }

  private void showDialog(Dialog dialog) {
    final AlertDialog.Builder builder = new AlertDialog.Builder(activity).setMessage(dialog.message)
        .setPositiveButton(dialog.positiveButton,
            ((dialog1, which) -> notifyClick(dialog.callback, which)));

    if (dialog.negativeButton > 0) {
      builder.setNegativeButton(dialog.negativeButton,
          ((dialog1, which) -> notifyClick(dialog.callback, which)));
    }

    if (dialog.title > 0) {
      builder.setTitle(dialog.title);
    }

    builder.show();
  }

  public Dialog create() {
    return new Dialog(actionTrigger);
  }

  private void notifyClick(SingleEmitter<Integer> callback, int button) {
    callback.onSuccess(button);
  }

  public static final class Dialog {
    private final ActionTrigger<Dialog> dialogActionTrigger;
    private SingleEmitter<Integer> callback;
    private int title;
    private int message;
    private int positiveButton;
    private int negativeButton;

    private Dialog(final ActionTrigger<Dialog> dialogActionTrigger) {
      this.dialogActionTrigger = dialogActionTrigger;
    }

    public Dialog withPositiveButton(final int val) {
      positiveButton = val;
      return this;
    }

    public Dialog withNegativeButton(final int val) {
      negativeButton = val;
      return this;
    }

    public Dialog withMessage(final int val) {
      message = val;
      return this;
    }

    public Dialog withTitle(final int val) {
      title = val;
      return this;
    }

    private Dialog withEmitter(final SingleEmitter<Integer> emitter) {
      this.callback = emitter;
      return this;
    }

    public Single<Integer> show() {
      if (positiveButton == 0) {
        positiveButton = android.R.string.ok;
      }

      return Single.create(emitter -> dialogActionTrigger.trigger(withEmitter(emitter)));
    }
  }
}
