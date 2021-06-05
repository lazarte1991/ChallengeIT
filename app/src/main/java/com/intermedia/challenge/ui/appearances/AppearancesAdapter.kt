package com.intermedia.challenge.ui.appearances

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Appearance
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.databinding.ViewComicItemBinding
import com.intermedia.challenge.databinding.ViewHeroItemBinding
import com.intermedia.challenge.ui.base.BaseAdapter
import com.intermedia.challenge.ui.characters.CharactersAdapter

class AppearancesAdapter : BaseAdapter<Appearance, AppearancesAdapter.AppearancesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppearancesViewHolder =
        AppearancesViewHolder(
            ViewComicItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_comic_item,
                    parent,
                    false
                )
            )
        )

    override fun onBindViewHolder(holder: AppearancesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class AppearancesViewHolder(
        private val binding: ViewComicItemBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Appearance) = with(itemView) {
            binding.comic = item

            }
        }

}