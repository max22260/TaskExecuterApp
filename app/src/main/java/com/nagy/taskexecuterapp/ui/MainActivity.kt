package com.nagy.taskexecuterapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.nagy.taskexecuterapp.R
import com.nagy.taskexecuterapp.databinding.ActivityMainBinding
import com.nagy.taskexecuterapp.ui.adapter.LogAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val  viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }



    private fun setupUI() {
        setupTabBar()
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewStateUpdates(adapter)
    }

    private fun observeViewStateUpdates(adapter: LogAdapter) {

        lifecycleScope.launchWhenStarted {
            viewModel.viewEffects.collect {

                withContext(Dispatchers.Main){
                    val newList = adapter.currentList.toMutableList()
                    newList.add(it)
                    adapter.submitList(newList)
                    adapter.notifyDataSetChanged()
                }

            }
        }
    }

    private fun setupRecyclerView(LogAdapter:LogAdapter ) {
        binding.recyclerView.apply {
            adapter = LogAdapter
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
        }
    }

    private fun createAdapter() : LogAdapter {
        return LogAdapter()
    }

    private fun setupTabBar() {

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
                when(tab?.text.toString()){
                    resources.getString(R.string.task1) -> viewModel.onEvent(RunTaskEvent.TaskOne)
                    resources.getString(R.string.task2)-> viewModel.onEvent(RunTaskEvent.TaskTwo)
                    resources.getString(R.string.task3) -> viewModel.onEvent(RunTaskEvent.TaskTree)
                    resources.getString(R.string.task4) -> viewModel.onEvent(RunTaskEvent.TaskFour)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }

}