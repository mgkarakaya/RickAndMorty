package com.mgk.melih_rickmorty.ui

import androidx.lifecycle.*
import androidx.paging.*
import com.mgk.melih_rickmorty.data.RmRepository
import com.mgk.melih_rickmorty.model.CharacterSingle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* The ViewModel for CharacterListFragment.
* */

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: RmRepository) :
    ViewModel() {


    fun getCharacters(): Flow<PagingData<CharacterSingle>> {

        return repository.getSearchResultStream().cachedIn(viewModelScope)

    }

}