package christina.library.android.common

object AndroidConstant {
    inline fun <reified T> getSavedStateKey(key: String) = "${T::class.qualifiedName}:$key"
}