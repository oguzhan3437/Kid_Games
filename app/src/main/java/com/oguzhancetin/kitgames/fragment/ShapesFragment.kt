package com.oguzhancetin.kitgames.fragment

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.oguzhancetin.kitgames.R
import com.oguzhancetin.kitgames.databinding.FragmentShapesBinding
import com.oguzhancetin.kitgames.util.BaseFragment
import com.oguzhancetin.kitgames.util.MyDragShadowBuilder

class ShapesFragment : BaseFragment<FragmentShapesBinding>() {
    private val drawables = hashMapOf(
        "square" to R.drawable.square_svg,
        "triangle" to R.drawable.triangle_svg,
        "star" to R.drawable.star_svg
    )

    override fun getDataBinding(): FragmentShapesBinding =
        FragmentShapesBinding.inflate(layoutInflater, null, false)


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imageViewSquareSource.setOnTouchListener(::myOnTouchListener)
        binding.imageViewStarSource.setOnTouchListener(::myOnTouchListener)
        binding.imageViewTriangleSource.setOnTouchListener(::myOnTouchListener)

        binding.imageViewSquareDestination.setOnDragListener(::myOnDragListener)
        binding.imageViewStarDestination.setOnDragListener(::myOnDragListener)
        binding.imageViewTriangleDestination.setOnDragListener(::myOnDragListener)


    }

    private fun myOnTouchListener(v: View, motionEvent: MotionEvent): Boolean {
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

    private fun myOnDragListener(v: View, dragEvent: DragEvent): Boolean {

        val sourceView = dragEvent.localState as ImageView
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                v.invalidate()
                return true
            }
            DragEvent.ACTION_DROP -> {
                val sourceTag = sourceView.tag
                return if (sourceTag.equals(v.tag)) {
                    sourceView.setBackgroundColor(0x00000000)
                    v.setViewBackgroundImage()
                    v.invalidate()
                    true
                } else {
                    false
                }
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                if (!dragEvent.result) {
                    sourceView.visibility = View.VISIBLE
                }
                v.invalidate()
                return true
            }
            else -> return false
        }
    }

    private fun View.setViewBackgroundImage() =
        drawables[this.tag]?.let {
            this.setBackgroundResource(it)
        } ?: run {
            this.setBackgroundColor(Color.parseColor("43F3D8D8"))
        }

}






