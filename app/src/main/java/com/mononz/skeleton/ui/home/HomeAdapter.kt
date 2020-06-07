package com.mononz.skeleton.ui.home

import com.mononz.skeleton.R
import com.mononz.skeleton.base.BaseAdapter
import com.mononz.skeleton.data.response.SkeletonResponse
import javax.inject.Inject

class HomeAdapter @Inject
constructor() : BaseAdapter<SkeletonResponse>() {

    private var data: List<SkeletonResponse> = ArrayList()
    private var callback: Callback? = null

    override fun getObjForPosition(position: Int): SkeletonResponse {
        return data[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.skeleton_element
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun setData(data: List<SkeletonResponse>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun onItemClicked(skeleton: SkeletonResponse, position: Int) {
        callback?.itemSelected(skeleton, position)
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun itemSelected(skeleton: SkeletonResponse, position: Int)
    }
}
