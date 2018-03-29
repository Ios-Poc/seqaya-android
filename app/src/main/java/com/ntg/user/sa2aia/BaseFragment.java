package com.ntg.user.sa2aia;

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


/**
 * Acts as base class for all fragments in the app
 *
 * @author Sara Elmoghazy
 */
public class BaseFragment extends Fragment {
    private Dialog loadingIndicator = null;

    /**
     * Display progress that indicates loading process is in progress.
     */

    public void showLoadingIndicator() {
        if (loadingIndicator == null && isAdded()) {
            loadingIndicator = new Dialog(getActivity());
            loadingIndicator.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loadingIndicator.setContentView(R.layout.partial_blocking_loading_indicator);
            loadingIndicator.setCancelable(false);
            loadingIndicator.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loadingIndicator.show();
        }
    }

    /**
     * Hide previously displayed progress indicator to indicates that the process was finished.
     */

    public void hideLoadingIndicator() {
        if (loadingIndicator != null) {
            loadingIndicator.dismiss();
            loadingIndicator = null;
        }
    }

    /**
     * Handle common errors
     *
     * @param message
     */

    public void showErrorMessage(String message) {
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }


    public void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}
