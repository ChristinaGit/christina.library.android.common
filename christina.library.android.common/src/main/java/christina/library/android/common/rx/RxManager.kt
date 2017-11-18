package christina.library.android.common.rx

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface RxManager {
    fun autoManage(target: Completable): Completable
    fun <T> autoManage(target: Maybe<T>): Maybe<T>
    fun <T> autoManage(target: Single<T>): Single<T>
    fun <T> autoManage(target: Observable<T>): Observable<T>
    fun <T> autoManage(target: Flowable<T>): Flowable<T>
}