package com.ternence.facedetect;

import android.graphics.Bitmap;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by ternence on 16/7/23.
 */
public class FaceDetect {
    public static void detect(final Bitmap bm, final CallBack callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(Constant.KEY, Constant.SECRET, true, true);
                    Bitmap bmSmall = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmSmall.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] array = stream.toByteArray();
                    PostParameters parameters = new PostParameters();
                    parameters.setImg(array);
                    JSONObject jsonObject = requests.detectionDetect(parameters);
                    if (callback != null) {
                        callback.success(jsonObject);
                    }
                } catch (FaceppParseException e) {
                    if (callback != null) {
                        callback.error(e);
                    }
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void Calculate(String id, final Bitmap bm, final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(Constant.KEY, Constant.SECRET, true, true);
                    Bitmap bmSmall = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmSmall.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] array = stream.toByteArray();
                    PostParameters parameters = new PostParameters();
                    parameters.setImg(array);
                    System.out.println(parameters);
                    requests.detectionLandmark(parameters);
                    System.out.println(requests.detectionLandmark(parameters));
//                    if (callBack!=null){
//                        callBack.success();
//                    }
                } catch (FaceppParseException e) {
                    if (callBack != null) {
                        callBack.error(e);
                    }
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
