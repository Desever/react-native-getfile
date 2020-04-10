
package com.des.getfile;


import android.database.Cursor;
import android.provider.MediaStore;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;


public class RNGetfileModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNGetfileModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNGetfile";
  }


  //获取所有图片
  @ReactMethod
  public void getImageFileList(Integer pageSize,Integer pageIndex,Promise promise){

    //文件数组
    WritableArray fileArray=Arguments.createArray();

    //写入文件id
    String fileId="";

    try {
      //获取cursor
      Cursor cursor = getReactApplicationContext().getContentResolver().query(
              MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
              null,
              null,
              null,
              MediaStore.Images.Media.DATE_TAKEN+ " DESC limit " + pageSize + " offset " + pageIndex * pageSize);
      int indexPhotoPath = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      while (cursor.moveToNext()) {

        //文件元素
        WritableMap fileItem=Arguments.createMap();
        fileItem.putString("uri",cursor.getString(indexPhotoPath));
        fileItem.putBoolean("select",false);
        //文件类型
        fileItem.putString("fileType","img");
        //文件ID
        fileId=UUID.randomUUID().toString();
        fileItem.putString("id",fileId+"image");

        //写入图片路径
        fileArray.pushMap(fileItem);
      }
      promise.resolve(fileArray);
      cursor.close();
    }catch (Exception e) {
      promise.resolve(fileArray);
      e.printStackTrace();
    }
  }

  //获取所有的word
  @ReactMethod
  public void getWordFileList(Promise promise){

    //写入文件id
    String fileId="";

    String select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.doc'" + " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.docx'" + ")";
    WritableArray fileArray=Arguments.createArray();
    try {
      //获取cursor
      Cursor cursor = getReactApplicationContext().getContentResolver().query(
              MediaStore.Files.getContentUri("external"), null, select, null, MediaStore.Files.FileColumns.DATA+ " DESC"
      );
      int indexPhotoPath = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      while (cursor.moveToNext()) {

        //文件元素
        WritableMap fileItem=Arguments.createMap();
        fileItem.putString("uri",cursor.getString(indexPhotoPath));
        fileItem.putBoolean("select",false);
        //文件类型
        fileItem.putString("fileType","word");
        //写入文件id
        fileId=UUID.randomUUID().toString();
        fileItem.putString("id",fileId+"word");

        //写入图片路径
        fileArray.pushMap(fileItem);

      }
      promise.resolve(fileArray);
      cursor.close();
    }catch (Exception e) {
      promise.resolve(fileArray);
      e.printStackTrace();
    }
  }


  //获取所有excel
  @ReactMethod
  public void getExcelFileList(Promise promise){

    //写入文件id
    String fileId="";

    String select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.xls'" + " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.xlsx'" + ")";
    WritableArray fileArray=Arguments.createArray();
    try {
      //获取cursor
      Cursor cursor = getReactApplicationContext().getContentResolver().query(
              MediaStore.Files.getContentUri("external"), null, select, null, MediaStore.Files.FileColumns.DATA+ " DESC"
      );
      int indexPhotoPath = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      while (cursor.moveToNext()) {
        //文件元素
        WritableMap fileItem=Arguments.createMap();
        fileItem.putString("uri",cursor.getString(indexPhotoPath));
        fileItem.putBoolean("select",false);
        //文件类型
        fileItem.putString("fileType","excel");
        //写入文件id
        fileId=UUID.randomUUID().toString();
        fileItem.putString("id",fileId+"excel");

        //写入图片路径
        fileArray.pushMap(fileItem);
      }
      promise.resolve(fileArray);
      cursor.close();
    }catch (Exception e) {
      promise.resolve(fileArray);
      e.printStackTrace();
    }
  }


  //获取所有pdf
  @ReactMethod
  public void getPdfFileList(Promise promise){
    //写入文件id
    String fileId="";

    String select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.pdf'"  + ")";
    WritableArray fileArray=Arguments.createArray();
    try {
      //获取cursor
      Cursor cursor = getReactApplicationContext().getContentResolver().query(
              MediaStore.Files.getContentUri("external"), null, select, null, null
      );
      int indexPhotoPath = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      while (cursor.moveToNext()) {
        //文件元素
        WritableMap fileItem=Arguments.createMap();
        fileItem.putString("uri",cursor.getString(indexPhotoPath));
        fileItem.putBoolean("select",false);
        //文件类型
        fileItem.putString("fileType","pdf");
        //写入文件id
        fileId=UUID.randomUUID().toString();
        fileItem.putString("id",fileId+"pdf");

        //写入图片路径
        fileArray.pushMap(fileItem);
      }
      promise.resolve(fileArray);
      cursor.close();
    }catch (Exception e) {
      promise.resolve(fileArray);
      e.printStackTrace();
    }
  }



}