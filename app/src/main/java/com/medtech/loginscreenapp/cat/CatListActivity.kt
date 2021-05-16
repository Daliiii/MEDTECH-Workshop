package com.medtech.loginscreenapp.cat

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.medtech.loginscreenapp.R
import com.medtech.loginscreenapp.cat.network.APIInterface
import com.medtech.loginscreenapp.cat.network.ApiClient
import kotlinx.android.synthetic.main.activity_cat_list.*
import kotlinx.android.synthetic.main.activity_main_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatListActivity : AppCompatActivity() {

    private val catList = arrayListOf<CatItem>()
    private lateinit var adapter: CatAdapter
    private lateinit var apiInterface: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_list)
        initRecyclerView()
        swipeRefreshLayout.setOnRefreshListener {
            fetchData()
        }
        apiInterface = ApiClient().getClient().create(APIInterface::class.java)
        fetchData()
    }

    private fun initRecyclerView() {
        adapter = CatAdapter(this, catList) { catItem, position ->
            Toast.makeText(this, getString(R.string.clicked_cat, catItem.title), Toast.LENGTH_SHORT)
                .show()
            catList.remove(catItem)
            adapter.notifyDataSetChanged()

        }
        catRecyclerView.adapter = adapter
        catRecyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun fetchData(){
        apiInterface.getCats().enqueue(object : Callback<List<CatItem>> {
            override fun onResponse(call: Call<List<CatItem>>, response: Response<List<CatItem>>) {
                swipeRefreshLayout.isRefreshing = false
                progressBar.visibility = GONE
                val res = response.body()
                res?.let { catItems ->
                    if (catItems.isEmpty()) {
                        errorTextView.visibility = VISIBLE
                    } else {
                        catRecyclerView.visibility = VISIBLE
                        catList.clear()
                        catList.addAll(catItems)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<CatItem>>, t: Throwable) {
                catRecyclerView.visibility = GONE
                swipeRefreshLayout.isRefreshing = false
                progressBar.visibility = GONE
                Snackbar.make(swipeRefreshLayout, getString(R.string.loading_failed), Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry){
                    progressBar.visibility = VISIBLE
                    fetchData()
                }.show()
            }
        })
    }
}