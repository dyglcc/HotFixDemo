package hotfix.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import hotfix.sample.com.R;
import hotfix.util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sample);
        System.out.println(100 / 1);
        Toast.makeText(this, "i am patch start 11111", Toast.LENGTH_LONG).show();

        String str = DefaultAppLike.like.getApplication().getResources().getString(R.string.app_name);
        DefaultAppLike.like.onAction();
//        String str = getApplication().getResources().getString(R.string.app_name);
        DefaultAppLike like = Util.getApplicationLike(getApplication());
        Toast.makeText(this, "str" + str, Toast.LENGTH_SHORT).show();
        findViewById(R.id.toastInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "patch", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
