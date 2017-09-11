package hotfix.sample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import hotfix.reporter.SampleTinkerReport;
import testtinker.com.jinniu.modao.test.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sample);

        Button toastInfo = (Button) findViewById(R.id.toastInfo);
        toastInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除补丁
                Toast.makeText(getApplicationContext(), "clean patch!", Toast.LENGTH_LONG).show();
                Tinker.with(getApplicationContext()).cleanPatch();
            }
        });
        Button loadPatchButton = (Button) findViewById(R.id.loadPatch);
        loadPatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加载补丁（加载成功以后patch文件会自动删掉）
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
            }
        });
        SampleTinkerReport report = new SampleTinkerReport();
        report.setReporter(new SampleTinkerReport.Reporter() {
            @Override
            public void onReport(final int key) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "key " + key, Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onReport(final String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "msg " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
