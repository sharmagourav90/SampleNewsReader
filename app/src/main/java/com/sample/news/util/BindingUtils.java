package com.sample.news.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.sample.news.R;
import com.squareup.picasso.Picasso;

/**
 * Cutom binding of views and data defined in this class.
 */
public class BindingUtils {

    @BindingAdapter("app:url")
    public static void loadImage(ImageView image, String url) {
        if(url != null) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.place_holder)
                    .into(image);
        } else {
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(image);
        }
    }
}
