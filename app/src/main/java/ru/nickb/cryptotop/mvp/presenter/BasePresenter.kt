package ru.nickb.cryptotop.mvp.presenter

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.nickb.cryptotop.mvp.view.BaseView


abstract class BasePresenter<V: BaseView>: MvpPresenter<V>() {

    private val subscribtion = CompositeDisposable()

    fun subscribe(subscription: Disposable) {
        subscribtion.add(subscription)
    }

    fun unsubscribe() {
        subscribtion.clear()
    }
}