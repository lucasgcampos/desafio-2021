package escola.c6.challenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import escola.c6.challenge.R
import escola.c6.challenge.extension.getString
import escola.c6.challenge.network.model.RepositoryResponse

class RepositoryAdapter(
    private val repositories: List<RepositoryResponse>
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount() = repositories.size
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val author = view.findViewById<TextView>(R.id.txt_author)
    private val name = view.findViewById<TextView>(R.id.txt_repository_name)
    private val description = view.findViewById<TextView>(R.id.txt_repository_description)

    fun bind(repository: RepositoryResponse) {
        name.text = repository.name
        author.text = getString(R.string.item_author_label, repository.owner.login)
        description.text = getString(R.string.item_repository_description_label, repository.description)
    }
}
