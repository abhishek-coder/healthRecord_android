package health.tpg.com.health.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;


/**
 *  class for getter and setter methods for reading/writing to {@link SharedPreferences}.
 */
final public class SharedPrefsUtils {

    private SharedPrefsUtils() {
    }

    /**
     * Retrieve a boolean value from {@link SharedPreferences}.
     *
     * @param context      a {@link Context} object.
     * @param key
     * @param defaultValue defaultValue to return if the value could not be read.
     * @return The value from shared preferences, or the provided default.
     */
    public static boolean getBooleanPreference(Context context, String key, boolean defaultValue) {
        boolean value = defaultValue;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getBoolean(key, defaultValue);
        }
        return value;
    }

    /**
     * Retrive  a float value from {@link SharedPreferences}.
     *
     * @param context      a {@link Context} object.
     * @param key
     * @param defaultValue defaultvalue  to return if the value could not be read.
     * @return The value from shared preferences, or the provided default.
     */
    public static float getFloatPreference(Context context, String key, float defaultValue) {
        float value = defaultValue;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getFloat(key, defaultValue);
        }
        return value;
    }

    /**
     * Set a float value to {@link SharedPreferences}.
     *
     * @param context a {@link Context} object.
     * @param key
     * @param value
     * @return
     */
    public static boolean setFloatPreference(Context context, String key, float value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat(key, value);
            return editor.commit();
        }
        return false;
    }

    /**
     * Set a boolean value to {@link SharedPreferences}.
     *
     * @param context a {@link Context} object.
     * @param key
     * @param value
     * @return true if the new value was successfully written to persistent storage.
     */
    public static boolean setBooleanPreference(Context context, String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            return editor.commit();
        }
        return false;
    }


    /***
     * Retrive string value from {@link SharedPreferences}
     * @param context {@link Context} object
     * @param key
     * @return
     */
    public static String getStringPreference(Context context, String key) {
        String value = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getString(key, null);
        }
        return value;
    }
    /**
     * Retrive a long value from {@link SharedPreferences}.
     *
     * @param context      a {@link Context} object.
     * @param key
     * @param defaultValue defaultvalue to return if the value could not be read.
     * @return The value from shared preferences, or the provided default.
     */

    public static String getStringPreferenceWithDefault(Context context, String key, String defaultValue) {
        String value = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getString(key, defaultValue);
        }
        return value;
    }

    /***
     *  Set string value from {@link SharedPreferences}
     * @param context {@link Context} object
     * @param key
     * @param value
     * @return
     */
    public static boolean setStringPreference(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            return editor.commit();
        }
        return false;
    }


    /**
     * Retrive a long value from {@link SharedPreferences}.
     *
     * @param context      a {@link Context} object.
     * @param key
     * @param defaultValue defaultvalue to return if the value could not be read.
     * @return The value from shared preferences, or the provided default.
     */
    public static long getLongPreference(Context context, String key, long defaultValue) {
        long value = defaultValue;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getLong(key, defaultValue);
        }
        return value;
    }

    /**
     * Set a long value to {@link SharedPreferences}.
     *
     * @param context a {@link Context} object.
     * @param key
     * @param value
     * @return true if the new value was successfully written to persistent storage.
     */
    public static boolean setLongPreference(Context context, String key, long value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(key, value);
            return editor.commit();
        }
        return false;
    }

    /**
     * Retrieve an integer value from {@link SharedPreferences}.
     *
     * @param context      {@link Context} object.
     * @param key
     * @param defaultValue defaultValue to return if the value could not be read.
     * @return The value from shared preferences, or the provided default.
     */
    public static int getIntegerPreference(Context context, String key, int defaultValue) {
        int value = defaultValue;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getInt(key, defaultValue);
        }
        return value;
    }

    /**
     * Set an integer value to {@link SharedPreferences}.
     *
     * @param context {@link Context} object.
     * @param key
     * @param value
     * @return true if the new value was successfully written to persistent storage.
     */
    public static boolean setIntegerPreference(Context context, String key, int value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            return editor.commit();
        }
        return false;
    }

    /**
     * Removes the specified key.
     */
    public static boolean remove(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key);
            return editor.commit();
        }
        return false;
    }

}
