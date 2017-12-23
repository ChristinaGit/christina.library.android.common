package christina.library.android.common

import christina.common.event.core.Event
import christina.library.android.common.event.data.RequestPermissionsResultEventData

interface RequestPermissionsResultProvider {
    val onRequestPermissionsResult: Event<RequestPermissionsResultEventData>
}