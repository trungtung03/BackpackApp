package com.example.backpackapp.model.home.modelChild

import android.util.Log
import com.example.backpackapp.ApiService
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

data class PopularDestinationsModel(
    @SerializedName("image")
    val imagePost: String? = null,
    @SerializedName("country")
    val nameCountry: String? = null
) {
    companion object {
        fun mCallPopularTrip(): ArrayList<PopularDestinationsModel> {
            val mListData = arrayListOf<PopularDestinationsModel>()
            ApiService.mApiService.mGetApiPopular().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<PopularDestinationsModel>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        Log.e("get_fail", e.toString())
                    }

                    override fun onComplete() {
                        Log.e("get_fail", "success")
                    }

                    override fun onNext(t: List<PopularDestinationsModel>) {
                        mListData.addAll(t)
                    }

                })
            return mListData
        }
    }
}