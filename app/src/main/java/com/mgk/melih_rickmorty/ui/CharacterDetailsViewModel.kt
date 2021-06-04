package com.mgk.melih_rickmorty.ui

import androidx.lifecycle.ViewModel
import com.mgk.melih_rickmorty.data.RmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: RmRepository): ViewModel()