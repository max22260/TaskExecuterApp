package com.nagy.taskexecuterapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nagy.taskexecuterapp.databinding.RecyclerViewLogItemBinding
import com.nagy.taskexecuterapp.ui.model.UITaskLog


class LogAdapter :ListAdapter<UITaskLog, LogAdapter.LogViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding =RecyclerViewLogItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return LogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val item: UITaskLog = getItem(position)

        holder.bind(item)
    }

    inner class LogViewHolder(
        private val binding: RecyclerViewLogItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UITaskLog) {

            binding.taskExTime.text = item.dateTime
            binding.taskName.text = item.taskName
        }

    }
}
private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<UITaskLog>() {
    override fun areItemsTheSame(oldItem: UITaskLog, newItem: UITaskLog): Boolean {
        return oldItem.dateTime == oldItem.dateTime
    }

    override fun areContentsTheSame(oldItem: UITaskLog, newItem: UITaskLog): Boolean {
        return oldItem == newItem
    }
}