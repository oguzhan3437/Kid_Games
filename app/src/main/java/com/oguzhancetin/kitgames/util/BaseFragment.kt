package com.oguzhancetin.kitgames.util

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oguzhancetin.kitgames.viewModel.MainActivityViewModel


abstract class BaseFragment<VB: ViewDataBinding> : Fragment() {

    val viewModel: MainActivityViewModel by viewModels()

    private var _binding: VB? = null
    val binding get() = this._binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = getDataBinding()
        return binding.root
    }

    abstract fun getDataBinding(): VB

    fun View.makeSoundWithTag(@IntegerRes audios: HashMap<String,Int>) {
        val audio = audios[this.tag]
        audio?.let {
            MediaPlayer.create(this@BaseFragment.requireContext(), audio).start()
        }

    }



}