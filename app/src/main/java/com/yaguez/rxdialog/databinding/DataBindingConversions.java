package com.yaguez.rxdialog.databinding;

import android.databinding.BindingConversion;
import android.view.View;

public class DataBindingConversions {
  @BindingConversion public static int setVisibility(Boolean state) {
    return state != null && state ? View.VISIBLE : View.GONE;
  }
}
