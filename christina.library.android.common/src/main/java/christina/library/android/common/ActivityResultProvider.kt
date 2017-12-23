package christina.library.android.common

import christina.common.event.core.Event
import christina.library.android.common.event.data.ActivityResultEventData

interface ActivityResultProvider {
    val onActivityResult: Event<ActivityResultEventData>
}

