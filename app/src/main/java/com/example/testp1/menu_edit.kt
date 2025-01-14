package com.example.testp1

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random

class menu_edit : AppCompatActivity() {
    var formal_result: TextView? = null
    var formal_info: TextView? = null
    var formal_gen: Button? = null
    var back_gen_menu: ImageButton? = null
    var selectedGender: String? = null
    var isFormalMenuClicked: Boolean = false
    private var mAuth: FirebaseAuth? = null
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef: DatabaseReference = database.getReference("users")
    private val textArray = mutableListOf<String>()

    private var inputText: EditText? = null
    private var addToArrayList: ImageButton? = null
    private var hideText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_edit)
        supportActionBar?.hide()
        init()
        mAuth = FirebaseAuth.getInstance()

        back_gen_menu!!.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        inputText = findViewById(R.id.inputText)
        addToArrayList = findViewById(R.id.addToArrayList)
        formal_gen = findViewById(R.id.formal_gen)


        addToArrayList?.setOnClickListener {
            val input = inputText?.text.toString().trim()
            if (input.isNotEmpty()) {
                textArray.add(input)
                formal_info?.text = textArray.joinToString(", ")
                inputText?.text?.clear()
            }
        }
        formal_gen?.setOnClickListener {
            if (textArray.isNotEmpty()) {
                generateClothes()  // เรียกใช้ generateClothes() เมื่อกดปุ่ม formal_gen
            }
        }
        hideText = findViewById(R.id.hidetext)

        addToArrayList?.setOnClickListener {
            val input = inputText?.text.toString().trim()
            if (input.isNotEmpty()) {
                textArray.add(input)
                formal_info?.text = textArray.joinToString(", ")
                hideText?.text = textArray.joinToString(", ")  // Update the hidetext TextView
                inputText?.text?.clear()
            }
        }

    }

    fun init() {
        formal_result = findViewById(R.id.formal_result)
        formal_info = findViewById(R.id.formal_info)
        formal_gen = findViewById(R.id.formal_gen)
        back_gen_menu = findViewById(R.id.back_gen_menu)
    }

    fun generateClothes() {
        formal_result?.text = "xxxxxxxx"
        formal_info?.text = ""

        val rotationXAnimator = ObjectAnimator.ofFloat(
            formal_result,
            View.ROTATION_X,
            0f,
            7200f
        )
        rotationXAnimator.duration = 4000
        rotationXAnimator.interpolator = AccelerateDecelerateInterpolator()

        rotationXAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                showRandomText()
            }
        })

        rotationXAnimator.start()
    }

    fun showRandomText() {
        if (textArray.isNotEmpty()) {
            val randomIndex = Random.nextInt(textArray.size)
            val randomText = textArray[randomIndex]
            formal_result?.text = randomText
        }
    }
}