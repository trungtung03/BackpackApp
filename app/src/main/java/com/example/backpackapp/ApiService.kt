package com.example.backpackapp

import com.example.backpackapp.model.chat.MemberChatModel
import com.example.backpackapp.model.home.modelChild.PopularDestinationsModel
import com.example.backpackapp.model.home.modelChild.PostsTripModel
import com.example.backpackapp.model.location.post_item.PostModel
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiService {
    companion object {
        private val mLoggingInterceptor: HttpLoggingInterceptor
            get() = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        private val mOkHttpBuilder: OkHttpClient.Builder
            get() = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(mLoggingInterceptor)

        private val mGson = GsonBuilder().setLenient().create()

        val mApiService: ApiService
            get() = Retrofit.Builder()
                .baseUrl("https://michaeltung.000webhostapp.com/BackpackApp/")
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(mOkHttpBuilder.build())
                .build().create(ApiService::class.java)
    }

    @FormUrlEncoded
    @POST("insertUserId.php")
    fun mInsertUserUid(
        @Field("user_uid") userUid: String
    ): Observable<MemberChatModel>

    @FormUrlEncoded
    @POST("uploadTrip.php")
    fun mInsertPostTrip(
        @Field("avatar") urlAvatar: String,
        @Field("name") userName: String,
        @Field("image") urlImage: String,
        @Field("country") country: String,
        @Field("describes") describes: String,
        @Field("more_image") moreImage: Int,
        @Field("video") urlVideo: String
    ): Observable<List<PostModel>>

    @GET("getPostsTrip.php?page=1")
    fun mGetApiPosts(): Observable<List<PostsTripModel>>

    @GET("getPopularTrip.php?page=2")
    fun mGetApiPopular(): Observable<List<PopularDestinationsModel>>
}