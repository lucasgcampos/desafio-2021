package escola.c6.challenge.extension

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.ViewHolder.getString(@StringRes string: Int, vararg formatArgs: Any) =
    itemView.context.getString(string, *formatArgs)
