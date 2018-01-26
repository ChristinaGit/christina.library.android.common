package christina.library.android.common.event

import christina.common.rx.event.Event

open class RequestPermissionsResultEvent(
    val requestCode: Int,
    val permissions: List<String>,
    val grantResults: List<Int>
) : Event