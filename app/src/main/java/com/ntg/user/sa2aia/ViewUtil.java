package com.ntg.user.sa2aia;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

public class ViewUtil {
    /**
     * Add shadow to view by setting background in pre LOLLIPOP devices otherwise add elevation
     *
     * @param context context
     * @param view    the view to add shadow for it.
     */
    public static void addShadowToView(Context context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(context.getResources().getDimension(R.dimen.card_recycler_elevation));
        } else {
            int paddingBottom = view.getPaddingBottom();
            int paddingStart = view.getPaddingStart();
            int paddingEnd = view.getPaddingEnd();
            int paddingTop = view.getPaddingTop();
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.card_bg));
            view.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom);
        }
    }
}
