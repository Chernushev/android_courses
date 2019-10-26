package ru.mobiledimension.nasaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class GallaryAdapter(val glide: RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var urlList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GalleryHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_gallery, parent, false))
    }

    override fun getItemCount(): Int = 15

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}

    fun setData(urlList: List<String>) {
        this.urlList = urlList.toMutableList()
    }
}

class GalleryHolder(itemView: View): RecyclerView.ViewHolder(itemView)