package com.ternence.facedetect;

import com.facepp.error.FaceppParseException;

import org.json.JSONObject;

/**
 * Created by ternence on 16/7/23.
 */
public interface CallBack {
    void success(JSONObject result);
    void error(FaceppParseException exception);
}
