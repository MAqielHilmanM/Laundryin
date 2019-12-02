package com.creative.iam.laundryin.network;

import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.DoDeliveryResponseDao;
import com.creative.iam.laundryin.network.response.DoOrderResponseDao;
import com.creative.iam.laundryin.network.response.DoPacketResponseDao;
import com.creative.iam.laundryin.network.response.DoTransactionResponseDao;
import com.creative.iam.laundryin.network.response.DoUlasanResponseDao;
import com.creative.iam.laundryin.network.response.GetAllOrderResponseDao;
import com.creative.iam.laundryin.network.response.GetAllPacketResponseDao;
import com.creative.iam.laundryin.network.response.GetOrderResponseDao;
import com.creative.iam.laundryin.network.response.GetProfileResponseDao;
import com.creative.iam.laundryin.network.response.LoginResponseDao;
import com.creative.iam.laundryin.network.response.UpdateStatusOrderResponseDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("Login.php")
    @FormUrlEncoded
    Call<BaseDao<LoginResponseDao>> doLogin(
        @Field("username") String username,
        @Field("password") String password
    );

    @POST("Register.php")
    @FormUrlEncoded
    Call<BaseDao<Boolean>> doRegister(
        @Field("username") String username,
        @Field("password") String password,
        @Field("nama") String nama,
        @Field("email") String email,
        @Field("alamat") String alamat
    );

    @POST("DoOrder.php")
    @FormUrlEncoded
    Call<BaseDao<DoOrderResponseDao>> doOrder(
        @Field("username") String username,
        @Field("id_paket") String idPaket,
        @Field("catatan") String catatan,
        @Field("address") String address
    );

    @POST("DoTransaction.php")
    @FormUrlEncoded
    Call<BaseDao<DoTransactionResponseDao>> doTransaction(
        @Field("id_order") String idOrder,
        @Field("total_bayar") String totalBayar
    );

    @POST("DoDelivery.php")
    @FormUrlEncoded
    Call<BaseDao<DoDeliveryResponseDao>> doDelivery(
        @Field("kode_transaksi") String kodeTransaksi,
        @Field("id_kurir") String idKurir
    );

    @POST("DoUlasan.php")
    @FormUrlEncoded
    Call<BaseDao<DoUlasanResponseDao>> doUlasan(
        @Field("id_paket") String id_paket,
        @Field("username") String username,
        @Field("ulasan") String ulasan
    );

    @POST("DoPacket.php")
    @FormUrlEncoded
    Call<BaseDao<DoPacketResponseDao>> doPacket(
        @Field("nama_paket") String nama_paket,
        @Field("harga_paket") String harga_paket,
        @Field("estimasi_paket") String estimasi_paket,
        @Field("keterangan") String keterangan,
        @Field("url_picture") String url_picture
    );
    @POST("UpdateStatusOrder.php")
    @FormUrlEncoded
    Call<BaseDao<UpdateStatusOrderResponseDao>> updateStatusOrder(
        @Field("id_order") String id_order,
        @Field("status") String status
    );

    @GET("GetAllPackets.php")
    Call<BaseDao<List<GetAllPacketResponseDao>>> getAllPacket();

    @GET("GetPacket.php")
    Call<BaseDao<GetAllPacketResponseDao>> getPacket(
            @Query("id_paket") String idPaket
    );

    @GET("GetAllOrder.php")
    Call<BaseDao<List<GetAllOrderResponseDao>>> getAllOrder(
            @Query("username") String username
    );

    @GET("GetOrder.php")
    Call<BaseDao<GetOrderResponseDao>> getOrder(
            @Query("id_order") String id_order
    );

    @GET("GetProfile.php")
    Call<BaseDao<GetProfileResponseDao>> getProfile(
            @Query("username") String username
    );

}
