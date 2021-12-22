package escola.c6.challenge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import escola.c6.challenge.R
import escola.c6.challenge.database.ChallengeDatabase
import escola.c6.challenge.lifecycle.LoggerSample
import escola.c6.challenge.network.cache.CacheType

class RepositoryFragment : Fragment(R.layout.fragment_repository) {

    private val recyclerView: RecyclerView? by lazy { view?.findViewById(R.id.recycler_view) }

    private val viewModel: RepositoryViewModel by viewModels {
        RepositoryViewModelFactory(ChallengeDatabase.getDatabase(requireContext()).repositoryDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.layoutManager = LinearLayoutManager(context)
        lifecycle.addObserver(LoggerSample(this::class.java.canonicalName))

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.fetchRepositories(CacheType.ROOM)

        viewModel.repositories.observe(this) {
            recyclerView?.adapter = RepositoryAdapter(it) { author ->
                findNavController(this).navigate(
                    RepositoryFragmentDirections.repositoryToAuthor(author)
                )
            }
        }
    }
}