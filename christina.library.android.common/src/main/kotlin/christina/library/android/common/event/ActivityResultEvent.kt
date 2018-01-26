package christina.library.android.common.event

import android.content.Intent
import christina.common.rx.event.Event

open class ActivityResultEvent(
    val requestCode: Int,
    val resultCode: Int,
    val data: Intent
) : Event