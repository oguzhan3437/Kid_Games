package com.oguzhancetin.kitgames.fragment

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.oguzhancetin.kitgames.R
import com.oguzhancetin.kitgames.databinding.FragmentShapesBinding
import com.oguzhancetin.kitgames.util.BaseFragment
import com.oguzhancetin.kitgames.util.MyDragShadowBuilder

class ShapesFragment : BaseFragment<FragmentShapesBinding>() {
    override fun getDataBinding(): FragmentShapesBinding =
        FragmentShapesBinding.inflate(layoutInflater, null, false)


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


         binding.imageViewSquareSource.setOnTouchListener { v, motionEvent ->
             val item = ClipData.Item(v.tag as? CharSequence)
             val dragData = ClipData(
                 v.tag as? CharSequence,
                 arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                 item
             )
             v.startDragAndDrop(
                 dragData,
                 View.DragShadowBuilder(v),
                 v,
                 0
             )
             v.visibility = View.INVISIBLE
             true
         }


        binding.imageViewSquareDestination.setOnDragListener { v, dragEvent ->
            val sourceView = dragEvent.localState as ImageView
            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> {

                    v.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {
                    val sourceTag = sourceView.tag
                    if (sourceTag.equals("square")) {
                        sourceView.setBackgroundColor(0x00000000)
                        v.setBackgroundColor(
                            ContextCompat.getColor(
                                this.requireContext(),
                                R.color.black
                            )
                        )
                        v.invalidate()
                        true
                    } else {
                        false
                    }

                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    sourceView.visibility = View.VISIBLE
                    v.invalidate()
                    false
                }
                else -> false
            }


        }

    }

    private fun myOnTouchListener(v: View, motionEvent: MotionEvent) :Boolean{
        val item = ClipData.Item(v.tag as? CharSequence)
        val dragData = ClipData(
            v.tag as? CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            item
        )
        v.startDragAndDrop(
            dragData,
            View.DragShadowBuilder(v),
            v,
            0
        )
        v.visibility = View.INVISIBLE
        return true
    }


}