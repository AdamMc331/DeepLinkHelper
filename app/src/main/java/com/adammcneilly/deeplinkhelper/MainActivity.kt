package com.adammcneilly.deeplinkhelper

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adammcneilly.deeplinkhelper.data.DLDatabase
import com.adammcneilly.deeplinkhelper.data.DLRepository
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private val adapter = DeepLinkAdapter(this::deepLinkClicked)
    private lateinit var viewModel: MainActivityViewModel

    private var uriInput: TextInputEditText? = null

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

        setupViews()
        setupViewModel()
        setupRecyclerView()
        setupSendButton()
    }

    private fun setupViews() {
        uriInput = findViewById(R.id.uri_input)
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.deep_link_list)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        viewModel.deepLinks.observe(this, Observer {
            it?.let(adapter::deepLinks::set)
        })

        viewModel.inputText.observe(this, Observer {
            uriInput?.setText(it)
        })
    }

    private fun setupSendButton() {
        findViewById<Button>(R.id.send_button).setOnClickListener {
            val input = uriInput?.text.toString()
            viewModel.deepLinkSent(input)
            launchUri(input)
        }
    }

    private fun launchUri(input: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(input))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, R.string.no_apps_available, Toast.LENGTH_SHORT).show()
        }
    }
}
