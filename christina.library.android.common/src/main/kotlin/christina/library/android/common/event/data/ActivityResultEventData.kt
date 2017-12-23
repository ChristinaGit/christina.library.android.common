package christina.library.android.common.event.data

import android.content.Intent

open class ActivityResultEventData(
    val requestCode: Int,
    val resultCode: Int,
    val data: Intent)