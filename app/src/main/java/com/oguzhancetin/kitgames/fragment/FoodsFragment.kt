package com.oguzhancetin.kitgames.fragment

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.oguzhancetin.kitgames.R
import com.oguzhancetin.kitgames.databinding.FragmentFoodsBinding
import com.oguzhancetin.kitgames.util.BaseFragment
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit


class FoodsFragment : BaseFragment<FragmentFoodsBinding>() {


    private var filled = 0
        set(value) {
            field = value
            if (value == 4) {
                gameFinished()
            }
        }


    private val drawables = hashMapOf(
        "apple" to R.drawable.square_svg,
        "raspberry" to R.drawable.triangle_svg,
        "pear" to R.drawable.star_svg,
        "banana" to R.drawable.star_svg
    )
    private val audios = hashMapOf(
        "apple" to R.raw.apple,
        "raspberry" to R.raw.rasberry,
        "pear" to R.raw.pear,
        "banana" to R.raw.banana
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonListenApple.apply {
            setOnClickListener { playAppleName() }
            setOnDragListener(::myOnDragListener)
        }
        binding.buttonListenBanana.apply {
            setOnClickListener { playAppleName() }
            setOnDragListener(::myOnDragListener)
        }
        binding.buttonListenPear.apply {
            setOnClickListener { playAppleName() }
            setOnDragListener(::myOnDragListener)
        }
        binding.buttonListenRaspberry.apply {
            setOnClickListener { playAppleName() }
            setOnDragListener(::myOnDragListener)
        }

        binding.imageviewApple.setOnTouchListener(::myOnTouchListener)
        binding.imageviewBanana.setOnTouchListener(::myOnTouchListener)
        binding.imageviewPear.setOnTouchListener(::myOnTouchListener)
        binding.imageviewRaspberry.setOnTouchListener(::myOnTouchListener)
    }

    private fun View.playAppleName() {
        val audio = audios[this.tag]
        audio?.let {
            MediaPlayer.create(context, audio).start()
        }

    }

    override fun getDataBinding(): FragmentFoodsBinding {
        return FragmentFoodsBinding.inflate(layoutInflater, null, false)
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
                    filled++
                    true
                } else {
                    false
                }
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                if (!dragEvent.result) {
                    sourceView.visibility = View.VISIBLE
                    sourceView.invalidate()
                }
                v.invalidate()
                return true
            }
            else -> return false
        }
    }

    private fun gameFinished() {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            position = Position.Relative(0.5, 0.3),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100)
        )
        binding.konfettiView.start(party = party)
    }

    private fun View.setViewBackgroundImage() =
        drawables[this.tag]?.let {
            this.setBackgroundResource(it)
        } ?: run {
            this.setBackgroundColor(Color.parseColor("#43F3D8D8"))
        }


}