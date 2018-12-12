# JlPermission
一款android动态权限申请库,直接回调，再也无需覆写onRequestPermissionsResult这种恶心的代码

## 引入
```
    implementation 'com.jianglei:jlpermission:1.0'
```

## 使用
```
                JlPermission.start(MainActivity.this)
                        .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .permission(Manifest.permission.CAMERA)
                        .build()
                        .request(new OnPermissionResultListener() {
                            @Override
                            public void onGranted(String[] permissions) {
                                //此处如果permssions为空，本方法不会被回调
                                Toast.makeText(MainActivity.this, Arrays.toString(permissions)+" 申请成功",
                                        Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onDenied(String[] permissions) {
                                //此处如果permssions为空，本方法不会被回调
                                Toast.makeText(MainActivity.this, Arrays.toString(permissions)+" 申请失败",
                                        Toast.LENGTH_LONG).show();

                            }
                        });
```
如果有问题希望大家尽快反馈！
