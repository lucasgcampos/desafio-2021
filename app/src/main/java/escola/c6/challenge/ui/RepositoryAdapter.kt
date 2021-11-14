package escola.c6.challenge.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import escola.c6.challenge.R
import escola.c6.challenge.extension.getString
import escola.c6.challenge.extension.inflate
import escola.c6.challenge.network.model.RepositoryResponse

private const val JAVA_VIEW_TYPE = 42
private const val REPOSITORY_VIEW_TYPE = 7

class RepositoryAdapter(
    repositories: List<RepositoryResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ViewTypeSample>()

    init {
        repositories.forEach {
            items.add(RepositoryViewType(it))

            if (it.name.contains("java", ignoreCase = true)) {
                items.add(JavaViewType())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == REPOSITORY_VIEW_TYPE) {
            RepositoryViewHolder(parent.inflate(R.layout.item_repository))
        } else {
            JavaViewHolder(parent.inflate(R.layout.item_java))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryViewHolder) {
            holder.bind((items[position] as RepositoryViewType).repository)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].viewType
}

class JavaViewHolder(view: View) : RecyclerView.ViewHolder(view)

class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val author = view.findViewById<TextView>(R.id.txt_author)
    private val name = view.findViewById<TextView>(R.id.txt_repository_name)
    private val description = view.findViewById<TextView>(R.id.txt_repository_description)

    fun bind(repository: RepositoryResponse) {
        name.text = repository.name
        author.text = getString(R.string.item_author_label, repository.owner.login)
        description.text = getString(R.string.item_repository_description_label, repository.description)
    }
}

abstract class ViewTypeSample(val viewType: Int)
class JavaViewType: ViewTypeSample(JAVA_VIEW_TYPE)
class RepositoryViewType(val repository: RepositoryResponse) : ViewTypeSample(REPOSITORY_VIEW_TYPE)

