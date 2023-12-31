package com.example.drawingapp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private var drawingView:DrawingView?=null
    private var btnBrush:ImageButton?=null
    private var mImageCurrentPaint:ImageButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView=findViewById(R.id.drawing_view)
        btnBrush = findViewById(R.id.ib_brush)

        val linearLayoutPaintColor=findViewById<LinearLayout>(R.id.ll_paint_colors)

        btnBrush!!.setOnClickListener {
            showBrushSizeChooserDialog()
        }
        mImageCurrentPaint=linearLayoutPaintColor[0] as ImageButton


    }

    private fun  showBrushSizeChooserDialog(){
        val brushDialog=Dialog(this)
        brushDialog.setContentView(R.layout.brushsize_dialog)
        val btnSmall: ImageButton =brushDialog.findViewById(R.id.ib_small_brush)
        val btnMedium:ImageButton=brushDialog.findViewById(R.id.ib_medium_brush)
        val btnLarge:ImageButton=brushDialog.findViewById(R.id.ib_large_brush)
        btnSmall.setOnClickListener{
             drawingView!!.setSizeForBrush(5.toFloat())
            brushDialog.dismiss()
        }
        btnMedium.setOnClickListener{
            drawingView!!.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        btnLarge.setOnClickListener{
            drawingView!!.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()

    }
    fun paintedClicked(view: View){
        if(view != mImageCurrentPaint){
            val imageButton=view as ImageButton
            val colorTag=imageButton.tag.toString()
            drawingView?.setColor(colorTag)

            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallet_normal_pressed)

            )

            mImageCurrentPaint!!.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallet_normal)

            )
            mImageCurrentPaint=view

        }

    }
}