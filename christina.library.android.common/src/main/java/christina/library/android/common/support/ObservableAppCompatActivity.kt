package christina.library.android.common.support

import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.CheckResult
import android.support.v7.app.AppCompatActivity
import christina.common.event.Event
import christina.common.event.Events
import christina.common.event.invoke
import christina.library.android.common.ActivityResultProvider
import christina.library.android.common.RequestPermissionsResultProvider
import christina.library.android.common.event.data.ActivityResultEventData
import christina.library.android.common.event.data.RequestPermissionsResultEventData
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle.bindUntilEvent
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid.bindActivity
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class ObservableAppCompatActivity : AppCompatActivity(),
    ActivityResultProvider,
    RequestPermissionsResultProvider,
    LifecycleProvider<ActivityEvent> {
    final override val onRequestPermissionsResult: Event<RequestPermissionsResultEventData>
        get() = onRequestPermissionsResultEvent

    final override val onActivityResult: Event<ActivityResultEventData>
        get() = onActivityResultEvent

    @CheckResult
    final override fun lifecycle(): Observable<ActivityEvent> = lifecycleSubject.hide()

    @CheckResult
    final override fun <T> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> =
        bindUntilEvent<T, ActivityEvent>(lifecycleSubject, event)

    @CheckResult
    final override fun <T> bindToLifecycle(): LifecycleTransformer<T> = bindActivity<T>(lifecycleSubject)

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        onActivityResultEvent(ActivityResultEventData(requestCode, resultCode, data))
    }

    @CallSuper
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        onRequestPermissionsResultEvent(RequestPermissionsResultEventData(requestCode, permissions.toList(), grantResults.toList()))
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        onInjectMembers()

        super.onCreate(savedInstanceState)

        riseLifecycleEvent(ActivityEvent.CREATE)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()

        riseLifecycleEvent(ActivityEvent.START)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        riseLifecycleEvent(ActivityEvent.RESUME)
    }

    @CallSuper
    override fun onPause() {
        riseLifecycleEvent(ActivityEvent.PAUSE)

        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        riseLifecycleEvent(ActivityEvent.STOP)

        super.onStop()
    }

    @CallSuper
    override fun onDestroy() {
        riseLifecycleEvent(ActivityEvent.DESTROY)

        super.onDestroy()

        onReleaseInjectedMembers()
    }

    @CallSuper
    protected open fun onInjectMembers() {
    }

    @CallSuper
    protected open fun onReleaseInjectedMembers() {
    }

    private fun riseLifecycleEvent(event: ActivityEvent) = lifecycleSubject.onNext(event)

    private val lifecycleSubject = BehaviorSubject.create<ActivityEvent>()
    private val onRequestPermissionsResultEvent = Events.basic<RequestPermissionsResultEventData>()
    private val onActivityResultEvent = Events.basic<ActivityResultEventData>()
}