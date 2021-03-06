package com.javayhu.kiss.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.javayhu.kiss.R;
import com.javayhu.kiss.utils.SnackbarUtils;
import com.javayhu.kiss.utils.ToastUtils;


/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/10/17
 *     desc  : Snackbar工具类Demo
 * </pre>
 */
public class SnackbarActivity extends Activity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        findViewById(R.id.btn_short_snackbar).setOnClickListener(this);
        findViewById(R.id.btn_short_snackbar_with_action).setOnClickListener(this);
        findViewById(R.id.btn_long_snackbar).setOnClickListener(this);
        findViewById(R.id.btn_long_snackbar_with_action).setOnClickListener(this);
        findViewById(R.id.btn_indefinite_snackbar).setOnClickListener(this);
        findViewById(R.id.btn_indefinite_snackbar_with_action).setOnClickListener(this);
        findViewById(R.id.btn_add_view).setOnClickListener(this);
        findViewById(R.id.btn_add_view_with_action).setOnClickListener(this);
        findViewById(R.id.btn_cancel_snackbar).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final View snackBarRootView = findViewById(android.R.id.content);

        switch (view.getId()) {
            case R.id.btn_short_snackbar:
                SnackbarUtils.showShortSnackbar(snackBarRootView, "short snackbar", Color.WHITE, Color.BLUE);
                break;
            case R.id.btn_short_snackbar_with_action:
                SnackbarUtils.showShortSnackbar(snackBarRootView, "short snackbar", Color.WHITE, Color.BLUE,
                        "Short", Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShortToast("Click Short");
                            }
                        });
                break;
            case R.id.btn_long_snackbar:
                SnackbarUtils.showLongSnackbar(snackBarRootView, "long snackbar", Color.WHITE, Color.GREEN);
                break;
            case R.id.btn_long_snackbar_with_action:
                SnackbarUtils.showLongSnackbar(snackBarRootView, "long snackbar", Color.WHITE, Color.GREEN,
                        "Long", Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showLongToast("Click Long");
                            }
                        });
                break;
            case R.id.btn_indefinite_snackbar:
                SnackbarUtils.showIndefiniteSnackbar(snackBarRootView, "Indefinite snackbar", Color.WHITE, Color.RED);
                break;
            case R.id.btn_indefinite_snackbar_with_action:
                SnackbarUtils.showIndefiniteSnackbar(snackBarRootView, "Indefinite snackbar", Color.WHITE, Color.RED,
                        "Indefinite", Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShortToast("Click Indefinite");
                            }
                        });
                break;
            case R.id.btn_add_view:
                SnackbarUtils.showShortSnackbar(snackBarRootView, "short snackbar", Color.WHITE, Color.BLUE);
                SnackbarUtils.addView(R.layout.snackbar_add, 0);
                break;
            case R.id.btn_add_view_with_action:
                SnackbarUtils.showLongSnackbar(snackBarRootView, "short snackbar", Color.WHITE, Color.BLUE,
                        "Short", Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShortToast("Click Short");
                            }
                        });
                SnackbarUtils.addView(R.layout.snackbar_add, 0);
                break;
            case R.id.btn_cancel_snackbar:
                SnackbarUtils.dismissSnackbar();
                break;
        }
    }
}
