package escola.c6.challenge.ui.author

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import escola.c6.challenge.R

class AuthorFragment : Fragment(R.layout.fragment_author) {

    private val args: AuthorFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.author).text = args.author
    }
}