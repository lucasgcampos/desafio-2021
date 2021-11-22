package escola.c6.challenge.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import escola.c6.challenge.R
import escola.c6.challenge.extension.inflate
import escola.c6.challenge.network.model.RepositoryResponse
import escola.c6.challenge.ui.widget.RepositoryView

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
            RepositoryViewHolder(RepositoryView(parent.context))
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

class RepositoryViewHolder(private val view: RepositoryView) : RecyclerView.ViewHolder(view) {

    fun bind(repository: RepositoryResponse) {
        view.setup(repository)
    }
}

abstract class ViewTypeSample(val viewType: Int)
class JavaViewType: ViewTypeSample(JAVA_VIEW_TYPE)
class RepositoryViewType(val repository: RepositoryResponse) : ViewTypeSample(REPOSITORY_VIEW_TYPE)

