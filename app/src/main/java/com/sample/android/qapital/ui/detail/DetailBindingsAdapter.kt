package com.sample.android.qapital.ui.detail

import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.android.qapital.util.Resource
import com.sample.android.qapital.viewmodels.DetailViewModel.DetailWrapper

object DetailBindingsAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView.context).load(imageUrl).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("items")
    fun bindItems(recyclerView: RecyclerView, resource: Resource<DetailWrapper>?) {
        if (resource is Resource.Success) {
            val feedAdapter = resource.data?.let { FeedAdapter(it.feeds) }
            recyclerView.apply {
                setHasFixedSize(true)
                addItemDecoration(
                    DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
                )
                adapter = feedAdapter
            }
        }
    }

    @JvmStatic
    @BindingAdapter("descriptive_date")
    fun setDescriptiveDate(view: TextView, timestamp: Long) {
        val now = System.currentTimeMillis()
        val relativeDate = DateUtils.getRelativeTimeSpanString(
            timestamp,
            now,
            DateUtils.SECOND_IN_MILLIS,
            DateUtils.FORMAT_ABBREV_RELATIVE
        )
        view.text = relativeDate
    }

    @JvmStatic
    @BindingAdapter("android:max")
    fun setProgressBarMax(view: ProgressBar, value: Float) {
        view.max = value.toInt()
    }

    @JvmStatic
    @BindingAdapter("android:progress")
    fun setProgressBarProgress(view: ProgressBar, value: Float) {
        view.progress = value.toInt()
    }

    @JvmStatic
    @BindingAdapter("sum")
    fun setSumText(textView: TextView, resource: Resource<DetailWrapper>?) {
        if (resource is Resource.Success) {
            textView.text = resource.data?.weekSumText
        }
    }

    @JvmStatic
    @BindingAdapter("rules")
    fun setRulesText(textView: TextView, resource: Resource<DetailWrapper>?) {
        if (resource is Resource.Success) {
            textView.text = resource.data?.savingRules
        }
    }

    @JvmStatic
    @BindingAdapter("showLoading")
    fun showLoading(view: ProgressBar, resource: Resource<*>?) {
        view.visibility = if (resource is Resource.Loading) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("showData")
    fun showData(view: View, resource: Resource<*>?) {
        view.visibility = if (resource is Resource.Success) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun visibleGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}