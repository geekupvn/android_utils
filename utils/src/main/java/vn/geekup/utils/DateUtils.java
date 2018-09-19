package vn.geekup.utils;

import android.content.Context;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    private static Context mContext;
    private static Locale mLocale;

    private DateUtils() {
        throw new IllegalAccessError(DateUtils.class.getName());
    }

    public static void init(Context context) {
        mContext = context;
        mLocale = Locale.getDefault();
    }

    public static void init(Context context, Locale locale) {
        mContext = context;
        mLocale = locale;
    }

    /**
     * Parse time in day
     * @param secondInDay ex 0h00 is 0 second
     *                    ex 2h15 is 8100 second
     * @param separate separates 2 components hour vs minute
     * @return String is time in day by format hh{separate}mm
     */
    public static String timeInday(int secondInDay, String separate) {
        int hour = secondInDay / Constants.A_HOUR_SECOND;
        int minute = (secondInDay - hour * Constants.A_HOUR_SECOND) / Constants.A_MINUTE_SECOND;
        return String.format(Locale.getDefault(), "%02d", hour) + separate + String.format(Locale.getDefault(), "%02d", minute);
    }

    /**
     * The number of whole days between the two specified days
     * @param from in millisecond
     * @param to in millisecond
     * @return
     */
    public static int daysBetween(long from, long to) {
        return Days.daysBetween(new LocalDate(from), new LocalDate(to)).getDays();
    }

    /**
     * @param millis
     * @return true if diff, false if same
     */
    public static boolean isOtherYear(long millis) {
        return new LocalDate(millis).getYear() != new LocalDate(System.currentTimeMillis()).getYear();
    }

    /**
     * @return true if the supplied when is today else false
     */
    public static boolean isToday(long millis) {
        return android.text.format.DateUtils.isToday(millis);
    }

    /**
     * @return true if the supplied when is before today else false
     */
    public static boolean isBefore(long millis) {
        return new DateTime().isAfter(millis);
    }

    /**
     * @return true if the supplied when is after today else false
     */
    public static boolean isAfter(long millis) {
        return new DateTime().isBefore(millis);
    }

    /**
     * @return true if the supplied when is yesterday else false
     */
    public static boolean isYesterday(long date) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(date);
        now.add(Calendar.DATE, 1);
        return isToday(now.getTimeInMillis());
    }

    /**
     * @return true if the supplied when is tomorrow else false
     */
    public static boolean isTomorrow(long date) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(date);
        now.add(Calendar.DATE, -1);
        return isToday(now.getTimeInMillis());
    }

    /**
     * Convert time in millisecond to time in second
     * @param millis
     * @return Values of time in seconds
     */
    public static long convertMillisToSecond(int millis) {
        return TimeUnit.MILLISECONDS.toSeconds(millis);
    }

    /**
     * Convert time in second to time in millisecond
     * @param second
     * @return Values of time in milliseconds
     */
    public static long convertSecondToMillis(int second) {
        return TimeUnit.SECONDS.toMillis(second);
    }

    /**
     * Convert time in second to Date in Java
     * @param second
     * @return Values of time in Date
     */
    public static Date convertSecondToDate(int second) {
        return new Date(DateUtils.convertSecondToMillis(second));
    }

    /**
     * Convert time in millis to Date in Java
     * @param millis
     * @return Values of time in Date
     */
    public static Date convertMilliToDate(long millis) {
        return new Date(millis);
    }

    /**
     * Formats a Date into a date/time string.
     * @param date the time value to be formatted into a time string.
     * @param format format you want
     * @return the formatted time string.
     */
    public static String formatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, mLocale);
        return sdf.format(date);
    }

    /**
     * Formats a Millisecond into a date/time string.
     * @param milliSeconds the time value to be formatted into a time string.
     * @param format format you want
     * @return the formatted time string.
     */
    public static String formatDateTime(long milliSeconds, String format) {
        Date date = new Date(milliSeconds);
        return formatDateTime(date, format);
    }

    /**
     * Formats a seconds into a date/time string.
     * @param seconds the time value to be formatted into a time string.
     * @param format format you want
     * @return the formatted time string.
     */
    public static String formatDateTime(int seconds, String format) {
        Date date = new Date(DateUtils.convertSecondToMillis(seconds));
        return formatDateTime(date, format);
    }

    /**
     * @return the current time in milliseconds.  Note that
     * while the unit of time of the return value is a millisecond,
     * the granularity of the value depends on the underlying
     * operating system and may be larger.  For example, many
     * operating systems measure time in units of tens of
     * milliseconds.
     */
    public static long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Get start today in millisecond
     * @return start today in millisecond
     */
    public static long getStartTodayInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Get end today in millisecond
     * @return end today in millisecond
     */
    public static long getEndTodayInMillis() {
        return getStartTodayInMillis() + Constants.A_DAY_IN_MILLIS - 1;
    }

    /**
     * Constant popular for Date
     */
    public static class Constants {
        /**
         * Number of milliseconds in 1 second
         */
        public static final long A_SECOND_MILLIS = 1000;

        /**
         * Number of milliseconds in 1 minute
         */
        public static final long A_MINUTE_MILLIS = 60 * A_SECOND_MILLIS;

        /**
         * Number of milliseconds in 1 hour
         */
        public static final long A_HOUR_MILLIS = 60 * A_MINUTE_MILLIS;

        /**
         * Number of seconds in 1 minute
         */
        public static final int A_MINUTE_SECOND = 60;

        /**
         * Number of seconds in 1 hour
         */
        public static final int A_HOUR_SECOND = 60 * A_MINUTE_SECOND;

        /**
         * Number of milliseconds in 1 day
         */
        public static final long A_DAY_IN_MILLIS = 24 * A_HOUR_MILLIS;

        /**
         * Number of seconds in 1 day
         */
        public static final long A_DAY_IN_SECOND = 24 * A_HOUR_SECOND;
    }

    /**
     * Format popular for format time string
     */
    public class FormatSimple {
        public static final String MMM_DD_YYYY = "MMM dd, yyyy";
        public static final String MMMM_DD_YYYY = "MMMM dd, yyyy";
        public static final String DD_MM_YYYY = "dd/MM/yyyy";
        public static final String EEEE_DD_MM = "EEEE, dd/MM";
        public static final String EEEE_DD_MM_YYYY = "EEEE, dd/MM/yyyy";
        public static final String DD_MMM = "dd MMM";
        public static final String DD_MM = "dd/MM";
        public static final String MMM_DD = "MMM dd";
        public static final String DD = "dd";
        public static final String MM = "MM";
        public static final String MMM = "MMM";
        public static final String YYYY = "yyyy";
        public static final String MMMM_YYYY = "MMMM - yyyy";
        public static final String MM_YYY = "MM/yyyy";
        public static final String HHMM = "HH'h'mm";
    }
}
