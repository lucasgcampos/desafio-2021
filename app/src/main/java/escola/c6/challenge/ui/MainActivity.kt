package escola.c6.challenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import escola.c6.challenge.R
import escola.c6.challenge.lifecycle.LoggerSample

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(RepositoryViewModel::class.java) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        lifecycle.addObserver(LoggerSample(this::class.java.canonicalName))

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.fetchRepositories()

        viewModel.repositories.observe(this) {
            recyclerView.adapter = RepositoryAdapter(it)
        }
    }
}