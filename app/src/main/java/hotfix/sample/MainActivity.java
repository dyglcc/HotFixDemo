package hotfix.sample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import testtinker.com.jinniu.modao.test.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
