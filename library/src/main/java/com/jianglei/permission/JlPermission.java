package com.jianglei.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianglei created on 18-12-10
 */
public class JlPermission {
    private static final String FRAGMENT_TAG = "permission";
    private String[] permissions;
    private Activity activity;

    private JlPermission(Builder builder) {
        this.permissions = builder.permissions.toArray(new String[builder.permissions.size()]);
        this.activity = builder.activity;
    }

    @SuppressLint("NewApi")
    public void request(@NonNull final OnPermissionResultListener onPermissionResultListener) {
        if (Build.VERSION.SDK_INT < 23) {
            onPermissionResultListener.onGranted(permissions);
            return;
        }
        final List<String> grantedPermissions = new ArrayList<>();
        final List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
                grantedPermissions.add(permission);
            } else {
                deniedPermissions.add(permission);
            }
        }
        if(deniedPermissions.size() == 0){
            onPermissionResultListener.onGranted(grantedPermissions.toArray(new String[grantedPermissions.size()]));
            return;
        }
        PermissionFragment fragment = getFragment(activity);
        fragment.requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]),
                new ActivityCompat.OnRequestPermissionsResultCallback() {
            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                deniedPermissions.clear();
                for (int i = 0; i < grantResults.length; ++i) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        grantedPermissions.add(permissions[i]);
                    } else {
                        deniedPermissions.add(permissions[i]);
                    }
                }
                if(grantedPermissions.size() != 0) {
                    onPermissionResultListener.onGranted(grantedPermissions.toArray(new String[grantedPermissions.size()]));
                }
                if(deniedPermissions.size() != 0) {
                    onPermissionResultListener.onDenied(deniedPermissions.toArray(
                            new String[deniedPermissions.size()]));
                }
            }
        });
    }

    private PermissionFragment getFragment(Activity activity) {
        FragmentManager fm = activity.getFragmentManager();
        PermissionFragment fragment;
        if (fm.findFragmentByTag(FRAGMENT_TAG) == null) {
            fragment = new PermissionFragment();
            fm.beginTransaction().add(fragment, FRAGMENT_TAG).commit();
        } else {
            fragment = (PermissionFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        }
        return fragment;
    }

    public static Builder start(Activity activity) {
        return new Builder(activity);
    }



    public static class Builder {
        private List<String> permissions = new ArrayList<>();
        private Activity activity;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder permission(String permission) {
            permissions.add(permission);
            return this;
        }

        public JlPermission build() {
            return new JlPermission(this);
        }
    }

}
