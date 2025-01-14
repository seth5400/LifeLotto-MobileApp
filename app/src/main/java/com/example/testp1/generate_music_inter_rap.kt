package com.example.testp1

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random

class generate_music_inter_rap : AppCompatActivity() {
    var back_musicrapinter: ImageButton? = null
    var rapinter_result: TextView? = null
    var rapinter_info: TextView? = null
    var rapinter_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_inter_rap)
        supportActionBar?.hide()
        init()
        back_musicrapinter!!.setOnClickListener {
            val intent = Intent(this, menu_music_nation::class.java)
            startActivity(intent)
        }
        rapinter_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatethairap()

        }
    }

    fun generatethairap() {
        // Clear the textview value to the default value
        rapinter_result?.text = "xxxxxxxx"
        rapinter_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            rapinter_result,
            View.ROTATION_X,
            0f,
            7200f
        ) // Rotate twice (7200 degrees)
        rotationXAnimator.duration = 4000 // 4 seconds
        rotationXAnimator.interpolator =
            AccelerateDecelerateInterpolator() // Add acceleration and deceleration

        // Set an AnimatorListenerAdapter to handle onAnimationEnd
        rotationXAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // When the animation ends, call showRandomText() to display the result
                showRandomText()
            }
        })

        // Start the animation
        rotationXAnimator.start()
    }

    fun showRandomText() {
        val allClothes = listOf(
            "Alright",
            "No Role Modelz",
            "Started from the Bottom",
            "Super Bass",
            "Stan",
            "Empire State of Mind",
            "Stronger",
            "Ms. Jackson",
            "Juicy",
            "Straight Outta Compton"
        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        rapinter_result?.text = selectedClothes

        when (selectedClothes) {
            "Alright" -> rapinter_info?.text =
                "ศิลปิน:Kendrick Lamar เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับการต่อสู้เพื่อความเท่าเทียมทางเชื้อชาติในอเมริกา"

            "No Role Modelz" -> rapinter_info?.text =
                "ศิลปิน:J. Cole เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับการเติบโตขึ้นมาโดยไม่มีแบบอย่างที่ดี"

            "Started from the Bottom" -> rapinter_info?.text =
                "ศิลปิน:Drake เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับการต่อสู้จนประสบความสำเร็จ"

            "Super Bass" -> rapinter_info?.text =
                "ศิลปิน:Nicki Minaj เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับความมั่นใจในตัวเอง"

            "Stan" -> rapinter_info?.text =
                "ศิลปิน:Eminem เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับแฟนคลับที่คลั่งไคล้จนเกินเหตุ"

            "Empire State of Mind" -> rapinter_info?.text =
                "ศิลปิน:Jay-Z & Alicia Keys เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับความรักในเมืองนิวยอร์ก"

            "Stronger" -> rapinter_info?.text =
                "ศิลปิน:Kanye West เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับการเอาชนะอุปสรรค"

            "Ms. Jackson" -> rapinter_info?.text =
                "ศิลปิน:Outkast เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับการขอโทษแฟนเก่า"

            "Juicy" -> rapinter_info?.text =
                "ศิลปิน:The Notorious B.I.G. เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับชีวิตที่ร่ำรวย"

            "Straight Outta Compton" -> rapinter_info?.text =
                "ศิลปิน:N.W.A. เป็นเพลงแร็พที่มีเนื้อหาเกี่ยวกับชีวิตในย่านคอมป์ตัน"

            else -> rapinter_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_musicrapinter = findViewById(R.id.back_gen_rapinter)
        rapinter_result = findViewById(R.id.interrap_result)
        rapinter_info = findViewById(R.id.interrap_info)
        rapinter_gen = findViewById(R.id.geninter_rap)
    }
}