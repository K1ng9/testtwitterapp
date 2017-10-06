package com.soft.unikey.vkluchak.testtwitterapp.app.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.widget.ImageView;

import com.soft.unikey.vkluchak.testtwitterapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by new on 03.10.17.
 */


public class ImageLoadUtil {
    private static final int ICON_PERSON_PLACEHOLDER = R.drawable.ic_person_black_24dp;
    private static final int ICON_BROKEN_PLACEHOLDER = R.drawable.ic_broken_image_black_24dp;

    public static void loadPersonImageByUrl(Context context, ImageView imageView, String photoUrl) {
        if (!TextUtils.isEmpty(photoUrl)) {
            usePicassoForLoadAndAdd(context, imageView, Uri.parse(photoUrl), ICON_PERSON_PLACEHOLDER);
        } else {
            setDefaultImageHolder(context, imageView, ICON_PERSON_PLACEHOLDER);
        }
    }

    private static void picassoLoadFromUri(Context context, ImageView imageView, Uri uri) {
        usePicassoForLoadAndAdd(context, imageView, uri, ICON_PERSON_PLACEHOLDER);
    }

    private static void usePicassoForLoadAndAdd(Context context, ImageView imageView, Uri uri, int drawableResId) {
        Picasso.with(context)
                .load(uri)
                .placeholder(drawableResId)
                .error(ICON_BROKEN_PLACEHOLDER)
                .into(imageView);
    }

    /**
     * Instrument for setting default Image Holder
     */
    private static void setDefaultImageHolder(Context context, ImageView imageView, int drawableId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setImageDrawable(context.getDrawable(drawableId));
        } else {
            imageView.setImageDrawable(context.getResources().getDrawable(drawableId));
        }
    }
}
