package escola.c6.challenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import escola.c6.challenge.R
import escola.c6.challenge.network.CacheLocalDisk

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CacheLocalDisk.initDatabase(this)
    }
}