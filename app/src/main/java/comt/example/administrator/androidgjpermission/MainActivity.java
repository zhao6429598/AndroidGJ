package comt.example.administrator.androidgjpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * TODO 动态获取权限
 *    step1:
 *    step2:
 *    step3:
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //判断版本号
        if(Build.VERSION.SDK_INT>22){

            //大于22进行权限判断
            int phoneState    = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            int contactsState = checkSelfPermission(Manifest.permission.WRITE_CONTACTS);
            Log.e(TAG, "onCreate:  phoneState:"+phoneState);

            if (phoneState != PackageManager.PERMISSION_GRANTED && contactsState != PackageManager.PERMISSION_GRANTED){
                //如果没有获取权限
                // ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA,android.Manifest.permission.READ_SMS},100);
                //动态获取权限
                ActivityCompat.requestPermissions(this,new String []{Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_CONTACTS},REQUEST_CODE);

            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE){

            for (String permission : permissions) {
                Log.e(TAG, "申请的权限: "+permission);
            }

            for (int grantResult : grantResults) {
                Log.e(TAG, "获取的权限: "+grantResult);
            }


        }
    }
}
