package hotfix.sample;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dx168.patchsdk.FullUpdateHandler;
import com.dx168.patchsdk.IPatchManager;
import com.dx168.patchsdk.Listener;
import com.dx168.patchsdk.PatchManager;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import hotfix.sample.tinker.SampleApplicationLike;
import hotfix.sample.utils.Utils;

/**
 * Created by dongyuangui on 2017/8/28.
 */

@DefaultLifeCycle(application = "testtinker.com.jinniu.modao.test.BaseApplication",//通过注解，由tinker自动生成MyApplication
        flags = ShareConstants.TINKER_ENABLE_ALL,                 //tinkerFlags
        loaderClass = "com.tencent.tinker.loader.TinkerLoader",   //loaderClassName, 我们这里使用默认
        loadVerifyFlag = false)

public class DefaultAppLike extends SampleApplicationLike {
    private static final String TAG = "DefaultAppLike";

    public DefaultAppLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        System.out.println("init my application.............................");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String appId = "20170907163813557-7443";
        String appSecret = "a94728234232444db6f65b6d18971536";
        PatchManager.getInstance().init(getApplication(), "http://192.168.3.16:8080/hotfix-apis/",
                appId, appSecret, new IPatchManager() {
                    @Override
                    public void patch(Context context, String path) {
                        TinkerInstaller.onReceiveUpgradePatch(context, path);
                    }

                    @Override
                    public void cleanPatch(Context context) {
                        TinkerInstaller.cleanPatch(context);
                    }
                });
        PatchManager.getInstance().register(new Listener() {
            @Override
            public void onQuerySuccess(String response) {
                Log.d(TAG, "onQuerySuccess response=" + response);
            }
            @Override
            public void onQueryFailure(Throwable e) {
                Log.d(TAG, "onQueryFailure e=" + e);
            }

            @Override
            public void onDownloadSuccess(String path) {
                Log.d(TAG, "onDownloadSuccess path=" + path);
            }

            @Override
            public void onDownloadFailure(Throwable e) {
                Log.d(TAG, "onDownloadFailure e=" + e);
            }

            @Override
            public void onPatchSuccess() {
                Log.d(TAG, "onPatchSuccess");
            }

            @Override
            public void onPatchFailure() {
                Log.d(TAG, "onPatchFailure");
            }

            @Override
            public void onLoadSuccess() {
                Log.d(TAG, "onLoadSuccess");
            }

            @Override
            public void onLoadFailure() {
                Log.d(TAG, "onLoadFailure");
            }
        });
//        PatchManager.getInstance().setTag("your tag");
        String channel = Utils.getApplicationMetaData("HOTFIX_CHANNEL", getApplication());
        PatchManager.getInstance().setChannel(channel);
        PatchManager.getInstance().setFullUpdateHandler(new FullUpdateHandler());
        PatchManager.getInstance().queryAndPatch();
    }
}
