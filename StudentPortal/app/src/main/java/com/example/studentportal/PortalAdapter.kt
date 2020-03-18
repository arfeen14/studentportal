package com.example.studentportal

import android.graphics.Color
import android.net.Uri
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.portal.view.*

class PortalAdapter(private val portals: List<Portal>) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    val CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";

    private var mCustomTabsServiceConnection: CustomTabsServiceConnection? = null
    private var mClient: CustomTabsClient? = null
    private var mCustomTabsSession: CustomTabsSession? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.portal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle: TextView = itemView.tvTitle
        private val tvUrl: TextView = itemView.tvUrl

        fun bind(portal: Portal) {
            tvTitle.text = portal.title
            tvUrl.text = portal.url

            itemView.setOnClickListener{
                var url = portal.url
                val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
                    .setShowTitle(true)
                    .build()

                customTabsIntent.launchUrl(itemView.context, Uri.parse(url))
            }

        }
    }
}