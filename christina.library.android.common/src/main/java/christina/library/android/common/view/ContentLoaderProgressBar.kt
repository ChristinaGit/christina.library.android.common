package christina.library.android.common.view

import android.content.Context
import android.os.Build
import android.os.SystemClock
import android.support.annotation.CallSuper
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar

open class ContentLoaderProgressBar : ProgressBar {
    companion object {
        @JvmStatic
        val MINIMUM_SHOW_TIME = 500L
        @JvmStatic
        val MINIMUM_DELAY = 500L
        @JvmStatic
        private val NO_TIME = -1L
    }

    constructor(context: Context) : super(context) {
        initialize(null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize(attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
        : super(context, attrs, defStyleAttr) {
        initialize(attrs, defStyleAttr, 0)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
        : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(attrs, defStyleAttr, defStyleRes)
    }

    @CallSuper
    open fun hide() {
        if (shown) {
            shown = false
            if (attachedToWindow) {
                removeCallbacks(showCallback)
            }
            val diff = SystemClock.uptimeMillis() - startTime
            if (startTime == NO_TIME || diff >= MINIMUM_SHOW_TIME) {
                visibility = View.GONE
                startTime = NO_TIME
            } else {
                postDelayed(hideCallback, MINIMUM_SHOW_TIME - diff)
            }
        }
    }

    @CallSuper
    open fun reset(show: Boolean) {
        removeCallbacks(hideCallback)
        removeCallbacks(showCallback)
        shown = show
        startTime = NO_TIME
        visibility = if (show) View.VISIBLE else View.GONE
    }

    @CallSuper
    open fun show() {
        if (!shown) {
            shown = true
            if (attachedToWindow) {
                removeCallbacks(hideCallback)
                if (startTime == NO_TIME) {
                    postDelayed(showCallback, MINIMUM_DELAY)
                }
            }
        }
    }

    @CallSuper
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        attachedToWindow = true
        if (shown && visibility != View.VISIBLE) {
            postDelayed(showCallback, MINIMUM_DELAY)
        }
    }

    @CallSuper
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        attachedToWindow = false
        removeCallbacks(hideCallback)
        removeCallbacks(showCallback)
        if (!shown && startTime != NO_TIME) {
            visibility = View.GONE
        }
        startTime = NO_TIME
    }

    private var attachedToWindow = false

    private var shown: Boolean = false

    private var startTime = NO_TIME

    private val hideCallback = Runnable {
        visibility = View.GONE
        startTime = -1L
    }

    private val showCallback = Runnable {
        startTime = SystemClock.uptimeMillis()
        visibility = View.VISIBLE
    }

    private fun initialize(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        shown = visibility == View.VISIBLE
    }
}