package com.evgeny_m.testapp.presentation

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.evgeny_m.testapp.data.models.Post
import com.evgeny_m.testapp.data.models.PostToAdapter
import com.evgeny_m.testapp.databinding.ItemPostBinding

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var posts = mutableListOf<PostToAdapter>()

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        if (post.uri.isEmpty() && post.type == "text") {
            holder.binding.type.text = post.type
            holder.binding.textView.text = post.text
        } else {
            holder.binding.type.text = post.type
            holder.binding.textView.text = post.uri
            holder.binding.card.setOnClickListener {
                val uri = post.uri
                val intent = Intent(Intent.ACTION_VIEW, uri.toUri())
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = posts.size

    fun addPosts(list: Post?) {
        val post = PostToAdapter(
            type = list?.type ?: "",
            text = list?.payload?.text ?: "",
            uri = list?.payload?.url ?: ""
        )
        posts.add(post)
        notifyDataSetChanged()
    }
}