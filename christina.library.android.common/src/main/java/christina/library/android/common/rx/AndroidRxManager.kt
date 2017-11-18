package christina.library.android.common.rx

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

open class AndroidRxManager<TEvent>(private val lifecycleProvider: LifecycleProvider<TEvent>) : RxManager {
    override fun autoManage(target: Completable): Completable = target.bindToLifecycle(lifecycleProvider)

    override fun <T> autoManage(target: Maybe<T>): Maybe<T> = target.bindToLifecycle(lifecycleProvider)

    override fun <T> autoManage(target: Single<T>): Single<T> = target.bindToLifecycle(lifecycleProvider)

    override fun <T> autoManage(target: Observable<T>): Observable<T> = target.bindToLifecycle(lifecycleProvider)

    override fun <T> autoManage(target: Flowable<T>): Flowable<T> = target.bindToLifecycle(lifecycleProvider)
}