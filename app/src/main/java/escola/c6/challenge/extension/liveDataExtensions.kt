package escola.c6.challenge.extension

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.isEmpty() = value == null