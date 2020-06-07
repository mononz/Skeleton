package com.mononz.skeleton.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mononz.skeleton.BR

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter<T>.BindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        val viewHolder = BindingViewHolder(binding)
        viewHolder.bind(this)
        return viewHolder
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj, position)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjForPosition(position: Int): T

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    abstract fun setData(data: List<T>)

    inner class BindingViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T, position: Int) {
            binding.setVariable(BR.obj, item)
            binding.setVariable(BR.position, position)
        }

        fun bind(adapter: BaseAdapter<T>) {
            binding.setVariable(BR.adapter, adapter)
            binding.executePendingBindings()
        }
    }
}
