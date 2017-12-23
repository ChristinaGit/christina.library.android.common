package christina.library.android.common.rx

import android.os.Looper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxSchedulers {
    @JvmStatic
    fun io(): Scheduler = Schedulers.io()

    @JvmStatic
    fun computation(): Scheduler = Schedulers.computation()

    @JvmStatic
    fun new(): Scheduler = Schedulers.newThread()

    @JvmStatic
    fun main(): Scheduler = AndroidSchedulers.mainThread()

    @JvmStatic
    fun from(looper: Looper): Scheduler = AndroidSchedulers.from(looper)
}