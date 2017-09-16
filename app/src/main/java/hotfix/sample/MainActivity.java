package hotfix.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hotfix.sample.com.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sample);
//        System.out.println(100 / 0);
    }
}
