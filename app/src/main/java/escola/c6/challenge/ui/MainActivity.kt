package escola.c6.challenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import escola.c6.challenge.R
import escola.c6.challenge.network.RetrofitConfig.api
import escola.c6.challenge.network.model.RepositoryWrapperResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchRepositories()
    }

    private fun fetchRepositories() {
        api.fetchRepositories().enqueue(object : Callback<RepositoryWrapperResponse> {
            override fun onResponse(
                call: Call<RepositoryWrapperResponse>,
                response: Response<RepositoryWrapperResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.items?.let {
                        recyclerView.adapter = RepositoryAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<RepositoryWrapperResponse>, t: Throwable) {
                // TODO: tratar falhas
            }
        })
    }
}