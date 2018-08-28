package vn.geekup.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {

    public static final String ISO_8859_1 = "ISO_8859_1";
    public static final String US_ASCII = "US_ASCII";
    public static final String UTF_16 = "UTF_16";
    public static final String UTF_16BE = "UTF_16BE";
    public static final String UTF_16LE = "UTF_16LE";
    public static final String UTF_8 = "UTF_8";
    public static final String DASH_STRING = "--";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ISO_8859_1, US_ASCII, UTF_16, UTF_16BE, UTF_16LE, UTF_8})
    public @interface Endcoding {}

    public static final String PERCENT_TAG = "%";

}
