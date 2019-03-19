package com.adammcneilly.deeplinkhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adammcneilly.deeplinkhelper.data.DLDatabase
import com.adammcneilly.deeplinkhelper.data.DLRepository

class MainActivity : AppCompatActivity() {
    private val adapter = DeepLinkAdapter(this::deepLinkClicked)
    private lateinit var viewModel: MainActivityViewModel

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(p0: Class<T>): T {
            val database = DLDatabase.getInMemoryDatabase(this@MainActivity)
            val repository = DLRepository(database)

            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }
    }

    private fun deepLinkClicked(deepLink: DeepLink) {
        viewModel.deepLinkClicked(deepLink)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.deep_link_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        viewModel.deepLinks.observe(this, Observer {
            it?.let(adapter::deepLinks::set)
        })
    }
}
