package com.example.backpackapp.model.home.modelChild

import android.util.Log
import com.example.backpackapp.ApiService
import com.example.backpackapp.model.home.ListDataModel
import com.example.backpackapp.model.home.modelChild.PopularDestinationsModel.Companion.mCallPopularTrip
import com.example.backpackapp.units.GA
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

@Suppress("DEPRECATION")
data class PostsTripModel(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("country")
    val countryName: String? = null,
    @SerializedName("describes")
    val describe: String? = null,
    @SerializedName("more_image")
    val moreImage: String? = null,
    @SerializedName("video")
    val videoTrip: String? = null
) {
    var isComplete = false
    val mDataBundle = arrayListOf<PostsTripModel>()

    fun getApiTotalList(): ArrayList<ListDataModel> {
        val mTotalList = arrayListOf<ListDataModel>()
        mTotalList.clear()

        mTotalList.add(ListDataModel(GA.TYPE_POPULAR_DESTINATIONS, null, mCallPopularTrip()))
        mTotalList.add(ListDataModel(GA.TYPE_POST, mCallPostsTrip(), null))
        Log.d("list_data", mCallPostsTrip().size.toString() + "  2")
        return mTotalList
    }

    private fun mCallPostsTrip(): ArrayList<PostsTripModel> {
        val mListData = arrayListOf<PostsTripModel>()
        mDataBundle.clear()
        mListData.clear()
        ApiService.mApiService.mGetApiPosts().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<PostsTripModel>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Log.e("call_data", e.toString())
                }

                override fun onComplete() {
                    Log.d("call_data", "success")
                    Log.d("list_data", mListData.size.toString() + "  1")
                    mDataBundle.addAll(mListData)
                }

                override fun onNext(t: List<PostsTripModel>) {
                    mListData.addAll(t)
                    isComplete
                }

            })
        return mListData
    }
}