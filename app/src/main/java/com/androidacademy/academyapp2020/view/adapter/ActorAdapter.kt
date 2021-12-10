package com.androidacademy.academyapp2020.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.data.model.Actor

class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ActorHolder>() {

    private val actorList = listOf(
        Actor(R.drawable.actor_1, R.string.robert_downey_jr),
        Actor(R.drawable.actor_2, R.string.chris_evans),
        Actor(R.drawable.actor_3, R.string.mark_ruffalo),
        Actor(R.drawable.actor_4, R.string.chris_hemsworth)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder =
        ActorHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_actor,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.bind(actorList[position])
    }

    override fun getItemCount(): Int = actorList.size

    class ActorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(actor: Actor) {
            itemView.findViewById<ImageView>(R.id.iv_actor_photo).setImageResource(actor.imageUrl)
            itemView.findViewById<TextView>(R.id.tv_actor_name).text =
                itemView.context.getString(actor.name)
        }
    }
}