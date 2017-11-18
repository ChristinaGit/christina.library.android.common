package christina.library.android.common.rx

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

fun Completable.autoManage(manager: RxManager): Completable = manager.autoManage(this)

fun <T> Maybe<T>.autoManage(manager: RxManager): Maybe<T> = manager.autoManage(this)

fun <T> Single<T>.autoManage(manager: RxManager): Single<T> = manager.autoManage(this)

fun <T> Observable<T>.autoManage(manager: RxManager): Observable<T> = manager.autoManage(this)

fun <T> Flowable<T>.autoManage(manager: RxManager): Flowable<T> = manager.autoManage(this)