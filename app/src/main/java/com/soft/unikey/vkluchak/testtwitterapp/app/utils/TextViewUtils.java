package com.soft.unikey.vkluchak.testtwitterapp.app.utils;

import android.content.Context;
import android.text.TextUtils;

import com.soft.unikey.vkluchak.testtwitterapp.R;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class TextViewUtils {
    public static String getTextForTextView(Context context, String text){
        if(!TextUtils.isEmpty(text)) {
            return text;
        }else {
            return context.getString(R.string.placeholder);
        }
    }
    public static String getTextForTextView(Context context, Integer text){
        if(text != null && text != 0) {
            return ""+ text;
        }else {
            return context.getString(R.string.placeholder);
        }
    }
    public static String getTextForTextView(Context context, Long text){
        if(text != null) {
            return ""+ text;
        }else {
            return context.getString(R.string.placeholder);
        }
    }
}
