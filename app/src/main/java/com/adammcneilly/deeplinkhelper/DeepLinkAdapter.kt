package com.adammcneilly.deeplinkhelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeepLinkAdapter(private val deepLinkClicked: (DeepLink) -> Unit) : RecyclerView.Adapter<DeepLinkAdapter.DeepLinkViewHolder>() {
    var deepLinks: List<DeepLink> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeepLinkViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_item_uri, parent, false)
        return DeepLinkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deepLinks.size
    }

    override fun onBindViewHolder(holder: DeepLinkViewHolder, position: Int) {
        holder.bindDeepLink(deepLinks[position], deepLinkClicked)
    }

    class DeepLinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val uriTextView = view.findViewById<TextView>(R.id.uri)

        fun bindDeepLink(deepLink: DeepLink, deepLinkClicked: (DeepLink) -> Unit) {
            uriTextView.text = deepLink.uri
            itemView.setOnClickListener {
                deepLinkClicked.invoke(deepLink)
            }
        }
    }
}