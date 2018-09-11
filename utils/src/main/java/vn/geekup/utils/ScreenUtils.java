package vn.geekup.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by quoctrung on 7/31/2017.
 */

public class ScreenUtils {

    private static Context mContext;

    // Prevent direct instantiation.
    private ScreenUtils() {
        throw new IllegalAccessError(ScreenUtils.class.getName());
    }

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * @return The absolute width of the available display size in pixels.
     */
    public static int getWidthScreen() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * @return The absolute height of the available display size in pixels.
     */
    public static int getHeightScreen() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * Check device has soft navigation bar
     * @return true if has soft navigation bar
     */
    public static boolean hasNavBar() {
        int id = mContext.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && mContext.getResources().getBoolean(id);
    }

    /**
     * This method get height of navigation bar
     * @return 0 if device don't has soft navigation bar
     */
    public static int getNavigationBarHeight() {
        Resources resources = mContext.getResources();
        int id = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return id > 0 ? resources.getDimensionPixelSize(id) : 0;
    }

    /**
     * This method get height of status bar
     * @return
     */
    public static int getStatusBarHeight() {
        Resources resources = mContext.getResources();
        int id = resources.getIdentifier("status_bar_height", "dimen", "android");
        return id > 0 ? resources.getDimensionPixelSize(id) : 0;
    }

    /**
     * This method get height default of action bar
     * @return
     */
    public static int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics());
        } else {
            return 0;
        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp) {
        Resources resources = mContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px) {
        Resources resources = mContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * * @param id The desired resource identifier, as generated by the aapt
     *           tool. This integer encodes the package, type, and resource
     *           entry. The value 0 is an invalid identifier.
     *
     * @return Resource dimension value multiplied by the appropriate
     * metric and truncated to integer pixels.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID does not exist.
     */
    public static int getDimensionPixelOffset(@DimenRes int id) {
        return mContext.getResources().getDimensionPixelOffset(id);
    }

    /**
     * Hides the keyboard
     */
    public static void hideSoftKeyboard(@NonNull Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    /**
     * Show the keyboard
     */
    public static void showKeyBoard() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void setupViewClickHideKeyBoard(Activity activity, View... views) {
        // Set up touch listener for non-text box views to hide keyboard.
        for (View view : views) {
            if (view instanceof EditText) {
                continue;
            }
            view.setOnTouchListener((v, event) -> {
                if (activity != null && activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
                    hideSoftKeyboard(activity);
                }
                return false;
            });

            //If a layout container, iterate over children and seed recursion.
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    View innerView = ((ViewGroup) view).getChildAt(i);
                    setupViewClickHideKeyBoard(activity, innerView);
                }
            }
        }
    }
}
