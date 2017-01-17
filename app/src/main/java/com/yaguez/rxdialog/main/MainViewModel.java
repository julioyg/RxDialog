package com.yaguez.rxdialog.main;

import android.databinding.ObservableBoolean;
import com.yaguez.rxdialog.R;
import com.yaguez.rxdialog.di.PerActivity;
import com.yaguez.rxdialog.rx.ActionTrigger;
import com.yaguez.rxdialog.rx.RxDialog;
import javax.inject.Inject;

@PerActivity public class MainViewModel {
  public final ObservableBoolean showDetails = new ObservableBoolean(true);
  final ActionTrigger<Boolean> actionTrigger = ActionTrigger.create();
  private final RxDialog rxDialog;

  @Inject public MainViewModel(final RxDialog dialog) {
    rxDialog = dialog;
  }

  public void hideDetails() {
    rxDialog.create().withMessage(R.string.confirmation_message).show().subscribe(button -> {
      if (button == RxDialog.BUTTON_POSITIVE) {
        showDetails.set(false);
      }
    });
  }
}
