package hotfix.sample.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by dongyuangui on 2017/9/7.
 */

public class Utils {

    private static final String TAG = "demo Utils";

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


}
