package com.oguzhancetin.kitgames.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzhancetin.kitgames.R
import com.oguzhancetin.kitgames.databinding.FragmentAnimalsBinding
import com.oguzhancetin.kitgames.databinding.FragmentColorsBinding
import com.oguzhancetin.kitgames.util.BaseFragment


class AnimalsFragment : BaseFragment<FragmentAnimalsBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getDataBinding(): FragmentAnimalsBinding {
        return FragmentAnimalsBinding.inflate(layoutInflater,null,false)
    }

}