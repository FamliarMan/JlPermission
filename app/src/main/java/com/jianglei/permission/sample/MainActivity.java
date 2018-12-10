package com.jianglei.permission.sample;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jianglei.permission.JlPermission;
import com.jianglei.permission.OnPermissionResultListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_camera_storage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JlPermission.start(MainActivity.this)
                        .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .permission(Manifest.permission.CAMERA)
                        .build()
                        .request(new OnPermissionResultListener() {
                            @Override
                            public void onGranted(String[] permissions) {
                                Toast.makeText(MainActivity.this, Arrays.toString(permissions)+" 申请成功",
                                        Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onDenied(String[] permissions) {
                                Toast.makeText(MainActivity.this, Arrays.toString(permissions)+" 申请失败",
                                        Toast.LENGTH_LONG).show();

                            }
                        });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
