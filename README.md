
# react-native-getfile

## Getting started

`$ npm install react-native-getfile --save`


## Attention

# Android < 6.0
```javascript
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-feature android:name="android.hardware.camera" android:required="false" />
<uses-feature android:name="android.hardware.camera.front" android:required="false" />
```

# Android > 6.0
```javascript
import {
    PermissionsAndroid
} from 'react-native';

var permission = {

    //权限列表
    permissions: [
        PermissionsAndroid.PERMISSIONS.WRITE_EXTERNAL_STORAGE,
        PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
        PermissionsAndroid.PERMISSIONS.CAMERA
    ],
    //获取动态权限
    getPermissionsAndroid: function () {
        return new Promise(async (resolve, reject) => {
            try {
                //返回得是对象类型
                const granteds = await PermissionsAndroid.requestMultiple(this.permissions);
                var errorMsg = "";
                if (granteds["android.permission.ACCESS_FINE_LOCATION"] != "granted") {
                    errorMsg += "位置";
                }
                if (granteds["android.permission.CAMERA"] != "granted") {
                    errorMsg += ",相机";
                }
                if (granteds["android.permission.WRITE_EXTERNAL_STORAGE"] != "granted") {
                    errorMsg += ",存储";
                }
                if (errorMsg == "") {
                    resolve({
                        state: 1,
                        msg: "所有权限都获得"
                    });
                } else {
                    resolve({
                        state: 0,
                        msg: errorMsg
                    });
                }
            } catch (err) {
                resolve({
                    state: 0,
                    msg: "出错"
                });
            }
        })
    }
}

module.exports = permission;
```


### Mostly automatic installation
# RN < 0.60
react-native link react-native-getfile
# RN > 0.60
auto link


## Usage
```javascript
import RNGetfile from 'react-native-getfile';
//get images
//pageIndex=1
//pageSize=10
const images=await RNGetfile.getImageFileList(pageIndex,pageSize);
//get word
const words=await RNGetfile.getWordFileList();
//get excel
const excel=await RNGetfile.getExcelFileList();
//pdf
const pdf=await RNGetfile.getPdfFileList();
```
