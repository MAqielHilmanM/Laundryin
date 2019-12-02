package com.creative.iam.laundryin.tools;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
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

    public static void loadTextFromHTML(TextView tv, String source){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv.setText(Html.fromHtml(source));
        }
    }

    public static String loadStatus(int status){
        switch (status){
            case 0: return "Menunggu DiAmbil";
            case 1: return "Sedang DiCuci";
            case 2: return "Sedang DiKirim";
            case 3: return "Pesanan Selesai";
            default: return "Pesanan Gagal";
        }
    }
    public static String loadMessage(String id,int status){
        switch (status){
            case 0: return "Tunggu Pakaian Anda Diambil";
            case 1: return "Pakaian Anda Sedang di cuci";
            case 2: return "Pakaian Anda Sedang di kirim";
            case 3: return "Pesanan No."+id+" telah selesai";
            default: return "Pesanan No."+id+" gagal";
        }
    }
}
