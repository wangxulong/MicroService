package com.shu.microservice.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shu.microservice.R;

/**
 * Created by wxl on 2016/2/25.
 */
public class ToastUtil{
    private static int GRAVITY = Gravity.CENTER;

    public static void showLong( String message) {
        show( message, Toast.LENGTH_LONG);
    }

    public static void showShort( String message) {
        show( message, Toast.LENGTH_SHORT);
    }

    public static void showLong(int textId) {
        show(textId+"", Toast.LENGTH_LONG);
    }

    public static void showShort(int textId) {
        show(textId+"", Toast.LENGTH_SHORT);
    }

    public static void show( String text, int duration) {
        Toast toast = Toast.makeText(AppContext.getAppContext(), text, duration);
       // toast.setGravity(GRAVITY, 80, 80);
        toast.show();
    }

    public static void show(int textId, int duration) {
        Toast toast = Toast.makeText(AppContext.getAppContext(), textId, duration);
      //  toast.setGravity(GRAVITY, 80, 80);
        toast.show();
    }

//    public static void showSuccess(int textId) {
//        showIconToast(AppContext.getAppContext(), textId, R.drawable.ic_success, R.color.holo_blue);
//    }
//
//    public static void showFailure( int textId) {
//        showIconToast(AppContext.getAppContext(), textId, R.drawable.ic_failure, R.color.warn);
//    }
//
//    public static void showIconToast(Context context, int textId, int iconId,
//                                     int colorId) {
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View layout = inflater.inflate(R.layout.toast, null);
//        ((TextView) layout).setText(textId);
//        ((TextView) layout).setTextColor(context.getResources().getColor(
//                colorId));
//        ((TextView) layout).setCompoundDrawablesWithIntrinsicBounds(iconId, 0,
//                0, 0);
//        Toast toast = new Toast(context);
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(layout);
//        toast.show();
//    }

    /**
     * <TextView xmlns:android="http://schemas.android.com/apk/res/android"
     android:layout_width="match_parent"
     android:layout_height="match_parent"
     android:background="@drawable/toast_bg"
     android:drawablePadding="20dp"
     android:gravity="center"
     android:padding="20dp"
     android:text="success"
     android:textSize="16sp"
     android:textStyle="bold" />
     */

}
