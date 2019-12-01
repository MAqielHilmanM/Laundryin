package com.creative.iam.laundryin.tools;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class Tools {
    public static String TIME_FORMAT = "hh:mm a";
    public static String FULLDATE_FORMAT = "dd/MMM/yyyy HH:mm";
    public static String DATE_FORMAT = "EEEE, dd/MMM/yyyy";
    public static String SIMPLE_DATE_FORMAT = "dd/MM/yyyy";

    public static void showError(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String converterPrice(Double price){
        Locale locale = new Locale("in","ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(locale);
        return formatRupiah.format(price);
    }

    public static void loadImage(ImageView imageView, String url){
        if (!TextUtils.isEmpty(url) && url.contains("http")) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.color.colorPrimaryDark)
                    .error(android.R.color.holo_red_light)
                    .into(imageView);
        }
    }
}
