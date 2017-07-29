package health.tpg.com.health.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class Utils {
    private static final int SECONDS_IN_A_DAY = 24 * 60 * 60;
    private static final int THIRTY_MINUTES = 30 * 60;

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm a");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("dd MMM yyyy");
    public static final SimpleDateFormat DATE_FORMAT_TOOLBAR = new SimpleDateFormat("dd MMM");

    public static NotificationManager mManager;

    /***
     *  Display {@link Toast}  by providing  string.
     * @param context
     * @param message
     * @param duration
     */

    public static void displayToastByText(Context context, String message, int duration) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(context, message, duration).show();
        }
    }

    /***
     *  Display {@link Toast}  by providing resource Id .
     * @param context
     * @param id
     * @param duration
     */
    public static void displayToastById(Context context, int id, int duration) {
        String message = context.getString(id);
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(context, message, duration).show();
        }
    }

    public static int getStatusBarHeight(Context mContext) {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * to check if string is valid or not
     *
     * @param data
     * @return
     */
    public static boolean isNotNull(String data) {
        return !(data == null || data.equalsIgnoreCase("") || data.equalsIgnoreCase(" ") || data.equalsIgnoreCase("NA"));
    }

    /**
     * to request read_sms permission
     *
     * @param fragment
     * @return
     */
    public static boolean requestPermission(Fragment fragment, int requestCOde, String... permissions) {
        if (permissions == null) {
            return true;
        }

        // checking all permissions, if one id ca
        boolean hasPermissions = true;
        for (String permission : permissions) {
            int result = ContextCompat.checkSelfPermission(fragment.getActivity(), permission);
            hasPermissions = hasPermissions && (result == PackageManager.PERMISSION_GRANTED);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (!hasPermissions) {
                fragment.requestPermissions(permissions, requestCOde);
                return false;

            }

        return true;

    }


    public static void setVisibilityOnKeyboardOpenClose(View rootView, final View changeVisibilityView) {
        if (rootView == null || changeVisibilityView == null)
            return;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        final int screenHeight = rootView.getRootView().getHeight();
        // r.bottom is the position above soft keypad or device button.
        // if keypad is shown, the r.bottom is smaller than that before.
        final int keypadHeight = screenHeight - r.bottom;

        if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
            // keyboard is opened
            changeVisibilityView.setVisibility(View.GONE);
        } else {
            // keyboard is closed
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeVisibilityView.setVisibility(View.VISIBLE);
                }
            }, 100);
        }


    }

    public static void makeKeyboardInvisible(Activity context) {
        View view = context.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Check Internet connection is available or not.
     *
     * @param context is the {@link Context} of the {@link Activity}.
     * @return <b>true</b> is Internet connection is available.
     */
    public static boolean isInternetAvailable(Context context) {
        boolean isInternetAvailable = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && (networkInfo.isConnected())) {
                isInternetAvailable = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return isInternetAvailable;
    }





}

