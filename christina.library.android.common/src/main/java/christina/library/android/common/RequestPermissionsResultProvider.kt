package christina.library.android.common

import christina.library.android.common.event.data.RequestPermissionsResultEventData
import christina.common.event.Event

interface RequestPermissionsResultProvider {
    val onRequestPermissionsResult: Event<RequestPermissionsResultEventData>
}