package ru.mobiledimension.nasaapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryAdapter(private val glide: RequestManager): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var urlList: List<APOD> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GalleryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_gallery,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(urlList.isEmpty()) return

        Log.wtf("url", if(urlList.lastIndex < position) urlList.last().url else urlList[position].url)
        glide.load(if(urlList.lastIndex < position) urlList.last().url else urlList[position].url)
            .placeholder(R.color.colorPrimary)
            .into(holder.itemView.apodIV)

        Log.wtf("date", if(urlList.lastIndex < position) urlList.last().date else urlList[position].date)
        holder.itemView.apodTV.text = if(urlList.lastIndex < position) urlList.last().date else urlList[position].date
        holder.itemView.apodTV.visibility = View.VISIBLE
    }

    fun setData(urlList: List<APOD>) {
        this.urlList = urlList
    }
}

class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

