package escola.c6.challenge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import escola.c6.challenge.R
import escola.c6.challenge.lifecycle.LoggerSample

class RepositoryFragment : Fragment(R.layout.fragment_repository) {

    private val viewModel by lazy { ViewModelProvider(this).get(RepositoryViewModel::class.java) }
    private val recyclerView: RecyclerView? by lazy { view?.findViewById(R.id.recycler_view) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.layoutManager = LinearLayoutManager(context)
        lifecycle.addObserver(LoggerSample(this::class.java.canonicalName))

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.fetchRepositories()

        viewModel.repositories.observe(this) {
            recyclerView?.adapter = RepositoryAdapter(it) { author ->
                findNavController(this).navigate(
                    RepositoryFragmentDirections.repositoryToAuthor(author)
                )
            }
        }
    }
}