package com.tamic.novate.config;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tamic.novate.util.FileUtil;

import java.util.HashMap;

/**
 * Created by Tamic on 2016-11-07.
 */

public class ConfigLoader {

    private static Config config;

    private final static String CONFIG_NAME = "novate-config.json";



    public static boolean checkSucess(Context context, int code) {
        loadConfig(context);
        Log.v("Novate", "web :" + code + ">>>>>>>>>>>>isOk：" + config.getSucessCode().contains(String.valueOf(code)));
        return config.getSucessCode().contains(String.valueOf(code));
    }

    public static Config loadConfig(Context context) {

        if (config != null) {
            return config;
        }
        String jsonStr = FileUtil.loadFromAssets(context, CONFIG_NAME);
        if (jsonStr == null) {
            return null;
        }
        try {
            config =  new Gson().fromJson(jsonStr, Config.class);
        } catch(JsonSyntaxException exception) {
            Log.e("Novate", "loaderConfig 配置数据无法解析: 请正确配置 <" + CONFIG_NAME + ">文件");
        }
        return config = new Gson().fromJson(jsonStr, Config.class);
    }

    public static boolean isFormat(Context context) {
        loadConfig(context);
        return TextUtils.equals(config.getIsFormat(), "ture");
    }

    public static HashMap<String, String> getErrorConfig() {
       return config.getErrorInfo();
    }

}