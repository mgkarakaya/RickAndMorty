package com.mgk.melih_rickmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mgk.melih_rickmorty.R
import com.mgk.melih_rickmorty.databinding.CharacterDetailsFragmentBinding
import com.mgk.melih_rickmorty.databinding.CharacterListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a single Character detail screen.
 * receiving character object through safe args. navArgs()
 *
 */

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: CharacterDetailsFragmentBinding
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private val args: CharacterDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= CharacterDetailsFragmentBinding.inflate(inflater,container,false)
        binding.characterDetail=args.character
        return binding.root
    }


}