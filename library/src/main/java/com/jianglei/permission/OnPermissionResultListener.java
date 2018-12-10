package com.jianglei.permission;

/**
 * @author jianglei created on 18-12-10
 */
public interface OnPermissionResultListener {
    /**
     * 权限申请成功
     */
    void onGranted(String[] permissions);

    /**
     * 权限被拒绝
     */
    void onDenied(String[] permissions);
}
