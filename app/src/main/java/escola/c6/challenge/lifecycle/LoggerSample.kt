package escola.c6.challenge.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class LoggerSample(private val componentName: String) : DefaultLifecycleObserver {

    private val tag = "Escola C6"

    override fun onCreate(owner: LifecycleOwner) {
        Log.d(tag, "onCreate: $componentName")
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d(tag, "onStart: $componentName")
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d(tag, "onResume: $componentName")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(tag, "onPause: $componentName")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(tag, "onStop: $componentName")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(tag, "onDestroy: $componentName")
    }
}