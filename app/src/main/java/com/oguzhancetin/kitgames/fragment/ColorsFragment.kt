package com.oguzhancetin.kitgames.fragment

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import com.oguzhancetin.kitgames.R
import com.oguzhancetin.kitgames.databinding.FragmentColorsBinding
import com.oguzhancetin.kitgames.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class ColorsFragment : BaseFragment<FragmentColorsBinding>() {

    private var filled = 0
        set(value) {
            field = value
            if (value == 4) {
                gameFinished()
            }
        }
    private val drawables = hashMapOf(
        "orange" to R.drawable.orange_ball,
        "green" to R.drawable.green_ball,
        "red" to R.drawable.red_ball,
        "yellow" to R.drawable.yellow_ball
    )
    private val audios = hashMapOf(
        "orange" to R.raw.orange,
        "green" to R.raw.green,
        "red" to R.raw.red,
        "yellow" to R.raw.yellow
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageviewListenColorGreen.apply {
            setOnClickListener { makeSoundWithTag(audios) }
            setOnDragListener(::myOnDragListener)
        }
        binding.imageviewListenColorRed.apply {
            setOnClickListener { makeSoundWithTag(audios) }
            setOnDragListener(::myOnDragListener)
        }
        binding.imageviewListenOrange.apply {
            setOnClickListener { makeSoundWithTag(audios) }
            setOnDragListener(::myOnDragListener)
        }
        binding.imageviewListenColorYellow.apply {
            setOnClickListener { makeSoundWithTag(audios) }
            setOnDragListener(::myOnDragListener)
        }

        binding.imageviewColorGreen.setOnTouchListener(::myOnTouchListener)
        binding.imageviewColorOrange.setOnTouchListener(::myOnTouchListener)
        binding.imageviewColorRed.setOnTouchListener(::myOnTouchListener)
        binding.imageviewColorYellow.setOnTouchListener(::myOnTouchListener)

    }

    override fun getDataBinding(): FragmentColorsBinding {
        return FragmentColorsBinding.inflate(layoutInflater, null, false)
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
            emitter = Emitter(duration = 100, java.util.concurrent.TimeUnit.MILLISECONDS).max(100)
        )
        viewModel.addProgress(1)
        binding.konfettiView.start(party = party)
    }

    private fun View.setViewBackgroundImage() =
        drawables[this.tag]?.let {
            this.setBackgroundResource(it)
        } ?: run {
            this.setBackgroundColor(Color.parseColor("#43F3D8D8"))
        }
}