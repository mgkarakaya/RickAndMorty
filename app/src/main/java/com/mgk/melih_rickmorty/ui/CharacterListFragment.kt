package com.mgk.melih_rickmorty.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mgk.melih_rickmorty.R
import com.mgk.melih_rickmorty.adapter.CharacterListAdapter
import com.mgk.melih_rickmorty.autoFitColumns
import com.mgk.melih_rickmorty.databinding.CharacterListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/*
* Fragment that lists all characters from rickandmorty api.
* has a toolbar includes Grid/List layout changer icon (chooseLayout)
* and a search icon
*
* */
@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var binding: CharacterListFragmentBinding
    private val characterListViewModel: CharacterListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager: Boolean = true
    private val adapter = CharacterListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterListFragmentBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
        recyclerView.adapter = adapter

        characterListViewModel.search()
        characterListViewModel.charactersPagingData.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                adapter.submitData(pagingData)
            }
        }
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        } else {
            recyclerView.autoFitColumns(100)
            recyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
            )
        }

    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        // Set the drawable for the menu icon based on which LayoutManager is currently in use
        menuItem.icon = when {
            isLinearLayoutManager -> ContextCompat.getDrawable(
                this.requireContext(),
                R.drawable.ic_grid_layout
            )
            else -> ContextCompat.getDrawable(
                this.requireContext(),
                R.drawable.ic_linear_layout
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val layout_button = menu.findItem(R.id.action_switch_layout)
        setIcon(layout_button)

        val search = menu.findItem(R.id.action_search)

        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"

        //restore searchview after coming back from detail fragment
        searchView.setQuery(characterListViewModel.searchKeyword ?: "", false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (!isVisible) {
                    return true
                }
                characterListViewModel.searchKeyword = newText
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}