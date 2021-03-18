package com.example.itishub.utils

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers


class RxUtils {
    companion object {
        fun <T> asyncSingle(): SingleTransformer<T, T> = SingleTransformer<T, T> {single ->
            single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

        fun <T> asyncSingleBoth(): SingleTransformer<T, T> = SingleTransformer<T, T> {single ->
            single.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
        }
    }
}