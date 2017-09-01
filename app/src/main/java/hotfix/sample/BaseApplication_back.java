package hotfix.sample;

import android.app.Application;

/**
 * Created by dongyuangui on 2017/8/28.
 */

public class BaseApplication_back extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("init my application.............................");
    }
}
