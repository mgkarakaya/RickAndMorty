package com.mgk.melih_rickmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mgk.melih_rickmorty.data.CharactersPagingDataSourceFactory
import com.mgk.melih_rickmorty.data.RmRepository
import com.mgk.melih_rickmorty.model.CharacterSingle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
* The ViewModel for CharacterListFragment.
* */

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: RmRepository) :
    ViewModel() {


    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(2* PAGE_SIZE)
        .build()
    private val dataSourceFactory = CharactersPagingDataSourceFactory(viewModelScope, repository)

    val charactersPagedListLiveData: LiveData<PagedList<CharacterSingle>> =
        LivePagedListBuilder(dataSourceFactory, pageListConfig).build()



    companion object {
        private const val PAGE_SIZE = 30
    }
}