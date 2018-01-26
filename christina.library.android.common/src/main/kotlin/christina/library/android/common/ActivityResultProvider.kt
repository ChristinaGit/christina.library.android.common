package christina.library.android.common

import christina.library.android.common.event.ActivityResultEvent
import io.reactivex.Observable

interface ActivityResultProvider {
    val onActivityResult: Observable<ActivityResultEvent>
}