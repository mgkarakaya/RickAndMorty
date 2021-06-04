package com.mgk.melih_rickmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mgk.melih_rickmorty.databinding.ListItemCharacterBinding
import com.mgk.melih_rickmorty.model.CharacterSingle
import com.mgk.melih_rickmorty.ui.CharacterListFragmentDirections

class CharacterListAdapter: PagedListAdapter<CharacterSingle,RecyclerView.ViewHolder>(CharacterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(
            ListItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val characterSingle = getItem(position)
        if (characterSingle != null) {
            (holder as CharacterViewHolder).bind(characterSingle)
        }
    }
    class CharacterViewHolder(
        private val binding: ListItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.character?.let { ch ->
                    navigateToCharacter(ch, it)
                }
            }
        }

        private fun navigateToCharacter(
            character: CharacterSingle,
            view: View
        ) {
            val direction =
                CharacterListFragmentDirections.actionDetail(
                    character
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: CharacterSingle) {
            binding.apply {
                character = item
                executePendingBindings()
            }
        }
    }
}
private class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterSingle>() {

    override fun areItemsTheSame(oldItem: CharacterSingle, newItem: CharacterSingle): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterSingle, newItem: CharacterSingle): Boolean {
        return oldItem == newItem
    }
}