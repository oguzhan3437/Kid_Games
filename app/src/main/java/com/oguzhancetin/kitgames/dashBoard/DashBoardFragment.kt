package com.oguzhancetin.kitgames.dashBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzhancetin.kitgames.R
import com.oguzhancetin.kitgames.databinding.DashBoardFragmentLandBinding
import com.oguzhancetin.kitgames.util.BaseFragment

class DashBoardFragment : BaseFragment<DashBoardFragmentLandBinding>() {


    private lateinit var viewModel: DashBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dash_board_fragment_land, container, false)
    }

    override fun getDataBinding(): DashBoardFragmentLandBinding =
        DashBoardFragmentLandBinding.inflate(layoutInflater, null, false)


}