package com.bw.fyc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 *@auther: 封英超
 *@Date: 2019/11/26
 *@Time:14:09
 *@Description:${DESCRIPTION}
 *
 */public class NetUtiler {
    //单例模式
    private static NetUtiler netUtiler = new NetUtiler();

    public NetUtiler() {

    }

    public static NetUtiler getInstance() {
        return netUtiler;
    }

    //getjson
    @SuppressLint("StaticFieldLeak")
    public void getjson(final String httpurl, final MyCallback myCallback) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                myCallback.onGetjson(s);
            }

            @Override
            protected String doInBackground(Void... voids) {
                InputStream inputStream = null;
                String json = "";
                try {
                    URL url = new URL(httpurl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        inputStream = httpURLConnection.getInputStream();
                        json = io2String(inputStream);
                    } else {
                        Log.d("e","请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return json;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    //io2String
    private String io2String(InputStream inputStream) throws IOException {
        //三件套
        byte[] bytes = new byte[1024];
        int len = -1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);
        }
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        String json = new String(bytes1);
        return json;
    }


    //getphoto
    @SuppressLint("StaticFieldLeak")
    public void getphoto(final String httpurl, final ImageView imageView) {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            protected Bitmap doInBackground(Void... voids) {
                InputStream inputStream = null;
                Bitmap bitmap = null;
                try {
                    URL url = new URL(httpurl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        inputStream = httpURLConnection.getInputStream();
                        bitmap = io2Bitmap(inputStream);
                    } else {
                        Log.d("e","请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bitmap;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    //io2Bitmap
    private Bitmap io2Bitmap(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }


    //判断是否有网
    //hasNet
    public boolean hasNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    //isWifi
    public boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else {
            return false;
        }
    }

    //isModule
    public boolean isModule(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        } else {
            return false;
        }
    }
    //接口
    public interface MyCallback {
        void onGetjson(String json);
    }


}
