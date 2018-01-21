package com.dch.perfect.core.ui.loading;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.dch.perfect.core.R;
import com.dch.perfect.core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by dch on 2018/1/21.
 */

public class PerfectLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LAODER = LoaderStyle.BallBeatIndicator.name();

    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context, type.name());
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.loading_style);
        AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();
        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth/LOADER_SIZE_SCALE;
            lp.height = deviceHeight/LOADER_SIZE_SCALE;
            lp.height = lp.height+deviceHeight/LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LAODER);
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog: LOADERS){
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
