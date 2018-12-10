# JlPermission
一款android动态权限申请库,直接回调，再也无需覆写onRequestPermissionsResult这种恶心的代码

## 引入

## 使用
```
                JlPermission.start(MainActivity.this)
                        //此处需要多少权限就直接写多少
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
```

