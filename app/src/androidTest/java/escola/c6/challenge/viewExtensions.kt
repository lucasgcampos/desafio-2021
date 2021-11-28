package escola.c6.challenge

import android.content.Intent
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry

fun View.launch() {
    val context = InstrumentationRegistry.getInstrumentation().targetContext

    val intent = Intent(context, EmptyTestingActivity::class.java)
    ActivityScenario.launch<EmptyTestingActivity>(intent).onActivity {
        it.setContentView(this@launch)
    }
}