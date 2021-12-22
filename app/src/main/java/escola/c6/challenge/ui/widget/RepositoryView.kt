package escola.c6.challenge.ui.widget

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import escola.c6.challenge.R
import escola.c6.challenge.extension.inflate
import escola.c6.challenge.ui.model.Repository

class RepositoryView(context: Context) : ConstraintLayout(context) {

    private val author by lazy { findViewById<TextView>(R.id.txt_author) }
    private val name by lazy { findViewById<TextView>(R.id.txt_repository_name) }
    private val description by lazy { findViewById<TextView>(R.id.txt_repository_description) }

    init {
        inflate(R.layout.item_repository, attachToRoot = true)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        setPadding(resources.getDimensionPixelSize(R.dimen.spacing_medium))
    }

    fun setup(repository: Repository) {
        name.text = repository.name
        author.text = context.getString(R.string.item_author_label, repository.owner.login)
        description.text = context.getString(R.string.item_repository_description_label, repository.description)
    }
}