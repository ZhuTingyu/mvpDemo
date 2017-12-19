package util;

import android.util.Log;

import java.util.Map;

/**
 * Created by chenshuai on 2017/11/3.
 */

public class LogUtil {

    public static void print(String s){
        Log.e("s: ", s);
    }

    public static void print(Map<String, String> map){
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        for (String key : map.keySet()) {
            sb.append(key + ": " + map.get(key) + "\n");
        }
        sb.append("}");
        Log.e("d",sb.toString());
    }
}
