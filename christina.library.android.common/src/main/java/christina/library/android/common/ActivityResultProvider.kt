package christina.library.android.common

import christina.library.android.common.event.data.ActivityResultEventData
import christina.common.event.Event

interface ActivityResultProvider {
    val onActivityResult: Event<ActivityResultEventData>
}

