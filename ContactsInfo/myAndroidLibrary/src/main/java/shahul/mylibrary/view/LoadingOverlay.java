package shahul.mylibrary.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import shahul.mylibrary.R;


/**
 * Created by shahulhameed on 12/03/2017.
 */

public class LoadingOverlay {

    public static void show (Activity activity) {
        View loadingView = activity.findViewById(R.id.rlLoading);
        //if cant find loading overlay in this activity then create the overlay
        if (loadingView == null) {
            ViewGroup viewGroup = ((ViewGroup) activity.findViewById(android.R.id.content));
            View inflatedView = View.inflate(activity, R.layout.loading, viewGroup);
            loadingView = inflatedView.findViewById(R.id.rlLoading);
        }

        ProgressBar progressBar = (ProgressBar) loadingView.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.VISIBLE);
    }

    public static void dismiss (Activity activity) {
        if (activity == null) {
            return;
        }

        View loadingView = activity.findViewById(R.id.rlLoading);
        View progressBar = loadingView.findViewById(R.id.progressBar);

        if (loadingView == null) {
            return;
        }

        progressBar.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
    }

    private static boolean isLoading (Activity activity) {
        View loadingView = activity.findViewById(R.id.rlLoading);

        if (loadingView == null) {
            return false;
        }

        return loadingView.getVisibility() == View.VISIBLE;
    }
}
