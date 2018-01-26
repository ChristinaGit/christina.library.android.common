package christina.library.android.common

import christina.library.android.common.event.RequestPermissionsResultEvent
import io.reactivex.Observable

interface RequestPermissionsResultProvider {
    val onRequestPermissionsResult: Observable<RequestPermissionsResultEvent>
}