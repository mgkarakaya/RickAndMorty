package com.mgk.melih_rickmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.mgk.melih_rickmorty.data.RmRepository
import com.mgk.melih_rickmorty.model.CharacterSingle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* The ViewModel for CharacterListFragment.
* */

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: RmRepository) :
    ViewModel() {

    var searchKeyword: String? = null
        set(value) {
            field = value
            search()
        }

    private val flow: Flow<PagingData<CharacterSingle>> by lazy {
        repository.getSearchResultStream().cachedIn(viewModelScope)
    }

    private var _charactersPagingData = MutableLiveData<PagingData<CharacterSingle>>()
    val charactersPagingData: LiveData<PagingData<CharacterSingle>> = _charactersPagingData

    fun search() = viewModelScope.launch {
        flow.collectLatest {
            _charactersPagingData.postValue(it.filter { character ->
                searchKeyword?.let { text ->
                    character.name.contains(text, ignoreCase = true)
                } ?: true
            })
        }
    }
}