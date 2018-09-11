package vn.geekup.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static vn.geekup.utils.Constants.Endcoding;
import static vn.geekup.utils.Constants.PERCENT_TAG;
import static vn.geekup.utils.Constants.UTF_8;

public class StringUtils {

    private static final String TAG = StringUtils.class.getSimpleName();

    private static Context mContext = null;

    public static void init(Context context) {
        mContext = context;
    }

    public StringUtils() {
        throw new IllegalAccessError(StringUtils.class.getName());
    }

    /**
     * Returns a localized string from the application's package's
     * default string table.
     *
     * @param resId Resource id for the string
     * @return The string data associated with the resource, stripped of styled
     * text information.
     */
    @NonNull
    public static String getString(@StringRes int resId) {
        return mContext.getString(resId);
    }

    /**
     * Returns a localized formatted string from the application's package's
     * default string table, substituting the format arguments as defined in
     * {@link java.util.Formatter} and {@link String#format}.
     *
     * @param resId Resource id for the format string
     * @param formatArgs The format arguments that will be used for
     * substitution.
     * @return The string data associated with the resource, formatted and
     * stripped of styled text information.
     */
    @NonNull
    public static String getString(@StringRes int resId, Object... formatArgs) {
        return mContext.getString(resId, formatArgs);
    }

    /**
     * Return the string array associated with a particular resource ID.
     *
     * @param id The desired resource identifier, as generated by the aapt
     * tool. This integer encodes the package, type, and resource
     * entry. The value 0 is an invalid identifier.
     * @return The string array associated with the resource.
     */
    @NonNull
    public String[] getStringArray(@ArrayRes int id) {
        return mContext.getResources().getStringArray(id);
    }

    /**
     * Formats the string necessary for grammatically correct pluralization
     * of the given resource ID for the given quantity, using the given arguments.
     * Note that the string is selected based solely on grammatical necessity,
     * and that such rules differ between languages. Do not assume you know which string
     * will be returned for a given quantity.
     *
     * @param id The desired resource identifier, as generated by the aapt
     * tool. This integer encodes the package, type, and resource
     * entry. The value 0 is an invalid identifier.
     * @param quantity The number used to get the correct string for the current language's
     * plural rules.
     * @return String The string data associated with the resource,
     * stripped of styled text information.
     */
    public static String getQuantityString(@PluralsRes int id, int quantity) {
        return mContext.getResources().getQuantityString(id, quantity, quantity);
    }

    /**
     * Return the string data of query in url
     *
     * @return The string data of query in url
     */
    @NonNull
    public static String parseDataFromUrlByQuery(@NonNull String url, @NonNull String query) {
        String returnValue = "";
        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(query)) {
            int start = url.indexOf(query);
            if (start != -1) {
                int end = url.indexOf("&", start);
                if (end == -1) {
                    end = url.length();
                }
                returnValue = url.substring(start + query.length(), end);
            }
        }
        return returnValue;
    }

    /**
     * Return the string distance format by m/km
     *
     * @return the string distance format by m/km
     */
    public static String formatDistance(int distance) {
        if (distance > 1000) {
            return String.format(getString(R.string.text_distance_km), (float) distance / 1000);
        }
        return String.format(getString(R.string.text_distance_m), distance);
    }

    /**
     * Decodes a string using a specific encoding scheme.
     *
     * @param value the {@code String} to decode
     * @return the newly decoded string
     * @throws UnsupportedEncodingException If character encoding needs to be consulted, but
     */
    public static String urlDecodeString(String value) throws UnsupportedEncodingException {
        return urlDecodeString(value, UTF_8);
    }

    /**
     * Decodes a string using a specific encoding scheme.
     *
     * @param value the {@code String} to decode
     * @param encode The name of a supported
     * <a href="../lang/package-summary.html#charenc">character encoding</a>.
     * @return the newly decoded string
     * @throws UnsupportedEncodingException If character encoding needs to be consulted, but
     * named character encoding is not supported
     */
    public static String urlDecodeString(String value, @Endcoding String encode) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, encode);
    }

    /**
     * Translates a string into {@code application/x-www-form-urlencoded}
     * format using a specific encoding scheme. This method uses the
     * supplied encoding scheme to obtain the bytes for unsafe
     * characters.
     *
     * @param value {@code String} to be translated.
     * @return the translated {@code String}.
     * @throws UnsupportedEncodingException If the named encoding is not supported
     */
    public static String encode(String value) throws UnsupportedEncodingException {
        return encode(value, UTF_8);
    }

    /**
     * Translates a string into {@code application/x-www-form-urlencoded}
     * format using a specific encoding scheme. This method uses the
     * supplied encoding scheme to obtain the bytes for unsafe
     * characters.
     *
     * @param value {@code String} to be translated.
     * @param encode The name of a supported
     * @return the translated {@code String}.
     * @throws UnsupportedEncodingException If the named encoding is not supported
     */
    public static String encode(String value, @Endcoding String encode) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, encode);
    }

    public static String formatNumberWithoutZero(float value, int numFloat) {
        String format = "%,." + numFloat + "f";
        return Math.abs(value) - (int) Math.abs(value) > 0 ? String.format(Locale.US, format, value) : String.valueOf((int) value);
    }

    public static String formatNumberPercent(float percent, int numFloat) {
        return formatNumberWithoutZero(percent, numFloat) + PERCENT_TAG;
    }

    /**
     * Show numbers in money format and separate digits
     */
    public static String formatCurrency(Number value) {
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(value);
    }

    /**
     * Returns true if the string is null or 0-length.
     * @param text the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmptyOrNull(String text) {
        return text == null || text.isEmpty() || text.trim().isEmpty();
    }

    /**
     * Return in bounds (allocated by the caller) the smallest rectangle that
     * encloses all of the characters, with an implied origin at (0,0).
     *
     * @param text string to measure and return its bounds
     * @param textView textView will contain @text
     * @return the unioned bounds of all the text. Must be allocated by the caller
     */
    public static Rect getBoundString(TextView textView, String text) {
        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        return bounds;
    }

}
