package hotfix.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

import hotfix.sample.DefaultAppLike;

/**
 * Created by dongyuangui on 2017/9/18.
 */

public class Util {
    private static final String TAG = "Tinker.Utils";

    public static String getApplicationMetaData(String metadataName, Context context) {

        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "getApplicationMetaData: null value exception");
        }
        return appInfo.metaData.getString(metadataName);
    }


    public static DefaultAppLike getApplicationLike(final Object base) {
        DefaultAppLike listenerObj = null;
        try {
            if (base == null) {
                return null;
            }

            Field field = ReflectionUtil.getField(base.getClass(), "applicationLike");
            if (field == null) {
                T.w("set stats could not find field!");
                return null;
            }
            listenerObj = (DefaultAppLike) ReflectionUtil.getFieldValue(field, base);


        } catch (Throwable ex) {
            T.e(TAG, "invokeListener 4444 -------- ");
            T.e(ex);
        } finally {
            T.e(TAG, "invokeListener 5555 -------- ");
        }

        return listenerObj;
    }
}
