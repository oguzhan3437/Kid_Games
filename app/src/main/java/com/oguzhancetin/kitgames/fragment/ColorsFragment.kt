package com.oguzhancetin.kitgames.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzhancetin.kitgames.R
import com.oguzhancetin.kitgames.databinding.FragmentAnimalsBinding
import com.oguzhancetin.kitgames.databinding.FragmentColorsBinding
import com.oguzhancetin.kitgames.databinding.FragmentFoodsBinding
import com.oguzhancetin.kitgames.util.BaseFragment


class ColorsFragment : BaseFragment<FragmentColorsBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun getDataBinding(): FragmentColorsBinding {
        return FragmentColorsBinding.inflate(layoutInflater,null,false)
    }

}