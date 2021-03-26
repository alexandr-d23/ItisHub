package com.example.itishub.utils

import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers


class RxUtils {
    companion object {
        fun <T> asyncSingleBoth(): SingleTransformer<T, T> = SingleTransformer<T, T> { single ->
            single.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
        }
    }
}