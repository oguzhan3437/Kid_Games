package com.oguzhancetin.kitgames.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oguzhancetin.kitgames.R
import com.oguzhancetin.kitgames.viewModel.DashBoardViewModel
import com.oguzhancetin.kitgames.databinding.DashBoardFragmentLandBinding
import com.oguzhancetin.kitgames.util.BaseFragment

class DashBoardFragment : BaseFragment<DashBoardFragmentLandBinding>() {

    private val viewModel: DashBoardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val action = DashBoardFragmentDirections.actionDashBoardFragmentToShapesFragment()
            findNavController().navigate(action)
        }
        binding.button2.setOnClickListener {
            val action = FoodsFragmentDirections.actionFoodsFragmentToDashBoardFragment()
            findNavController().navigate(action)
        }
        binding.button3.setOnClickListener {
            val action = ColorsFragmentDirections.actionColorsFragmentToDashBoardFragment()
            findNavController().navigate(action)
        }
        binding.button4.setOnClickListener {
            val action = ShapesFragmentDirections.actionShapesFragmentToDashBoardFragment()
            findNavController().navigate(action)
        }
        binding.button5.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToDashBoardFragment()
            findNavController().navigate(action)
        }

    }
    override fun getDataBinding(): DashBoardFragmentLandBinding {
       return DashBoardFragmentLandBinding.inflate(layoutInflater,null,false)
    }

}