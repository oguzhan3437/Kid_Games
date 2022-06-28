package com.oguzhancetin.kitgames.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.oguzhancetin.kitgames.databinding.DashBoardFragmentLandBinding
import com.oguzhancetin.kitgames.util.BaseFragment
import com.stephenvinouze.segmentedprogressbar.SegmentedProgressBar
import com.stephenvinouze.segmentedprogressbar.models.SegmentColor
import com.stephenvinouze.segmentedprogressbar.models.SegmentCoordinates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashBoardFragment : BaseFragment<DashBoardFragmentLandBinding>() {


    private var progress = 0
        set(value) {
            field = value
            updateProgressBar()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycleScope.launch {
            //viewModel.clearProgress()
            // The block passed to repeatOnLifecycle is executed when the lifecycle
            // is at least STARTED and is cancelled when the lifecycle is STOPPED.
            // It automatically restarts the block when the lifecycle is STARTED again.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Safely collect from locationFlow when the lifecycle is STARTED
                // and stops collection when the lifecycle is STOPPED
                viewModel.progressFlow.collect { _progress ->
                    progress = _progress
                }
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateProgressBar()
        binding.buttonGotoShapes.setOnClickListener {
            val action = DashBoardFragmentDirections.actionDashBoardFragmentToShapesFragment()
            findNavController().navigate(action)
        }
        binding.buttonGotoFruits.setOnClickListener {
            val action = DashBoardFragmentDirections.actionDashBoardFragmentToFoodsFragment()
            findNavController().navigate(action)
        }
        binding.buttonGotoAnimals.setOnClickListener {
            val action = DashBoardFragmentDirections.actionDashBoardFragmentToAnimalsFragment()
            findNavController().navigate(action)
        }
        binding.buttonGotoColors.setOnClickListener {
            val action = DashBoardFragmentDirections.actionDashBoardFragmentToColorsFragment()
            findNavController().navigate(action)
        }

    }

    override fun getDataBinding(): DashBoardFragmentLandBinding {
        return DashBoardFragmentLandBinding.inflate(layoutInflater, null, false)
    }

    private fun updateProgressBar() {
        binding.segmentedProgressBar.setContent {
            MaterialTheme {
                SegmentedProgressBar(
                    segmentCount = 4,
                    modifier = Modifier
                        .width(200.dp)
                        .height(20.dp),
                    spacing = 6.dp,
                    angle = 30f, // Can also be negative to invert the bevel side
                    progress = progress.toFloat(),
                    segmentColor = SegmentColor(
                        color = Color.Gray,
                        alpha = 0.3f,
                    ),
                    progressColor = SegmentColor(
                        color = Color.Green,
                        alpha = 1f,
                    ),
                    drawSegmentsBehindProgress = false, // See Javadoc for more explanation on this parameter
                    progressAnimationSpec = tween(
                        // You can give any animation spec you'd like
                        durationMillis = 1000,
                        easing = LinearEasing,
                    ),
                    onProgressChanged = { progress: Float, progressCoordinates: SegmentCoordinates ->
                        // Get notified at each recomposition cycle when a progression occurs.
                        // You can use the current progression or the coordinates the progress segment currently has.
                    },
                    onProgressFinished = {
                        // Get notified when the progression animation ends.
                    }
                )
            }

        }
        binding.root.invalidate()
    }
}


