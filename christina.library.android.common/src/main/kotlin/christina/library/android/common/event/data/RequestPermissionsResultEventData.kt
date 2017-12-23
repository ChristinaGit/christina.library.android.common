package christina.library.android.common.event.data

open class RequestPermissionsResultEventData(
    val requestCode: Int,
    val permissions: List<String>,
    val grantResults: List<Int>)