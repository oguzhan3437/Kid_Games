package com.oguzhancetin.kitgames.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<VB: ViewDataBinding> : Fragment() {

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



}