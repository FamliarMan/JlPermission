package com.jianglei.permission;


import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

/**
 * @author jianglei created on 18-12-10
 */
public class PermissionFragment extends Fragment {
    public static final int PERMISSION_CODE = 100;
    private ActivityCompat.OnRequestPermissionsResultCallback onRequestPermissionsResultCallback;
    private String[] requestPermissions;
    /**
     * 在onAttach时是否需要请求权限
     */
    private boolean isNeedRequest;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE && onRequestPermissionsResultCallback != null) {
            onRequestPermissionsResultCallback.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermissions(String[] permissions, ActivityCompat.OnRequestPermissionsResultCallback
            onRequestPermissionsResultCallback) {
        this.onRequestPermissionsResultCallback = onRequestPermissionsResultCallback;
        this.requestPermissions = permissions;
        if (isAdded()) {
            requestPermissions(permissions, PERMISSION_CODE);
            isNeedRequest = false;
        } else {
            isNeedRequest = true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(isNeedRequest){
            requestPermissions(this.requestPermissions,PERMISSION_CODE);
        }
    }
}
