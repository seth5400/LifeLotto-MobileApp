package com.example.testp1

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random

class generate_clothes_formal : AppCompatActivity() {
    var formal_result: TextView? = null
    var formal_info: TextView? = null
    var formal_gen: Button? = null
    var back_formal: ImageButton? = null
    var selectedGender: String? = null // เพิ่มตัวแปร selectedGender และกำหนดค่าเริ่มต้นเป็น null
    var isFormalMenuClicked: Boolean = false
    private var mAuth: FirebaseAuth? = null // เพิ่มการประกาศตัวแปร mAuth
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef: DatabaseReference = database.getReference("users") // ประกาศตัวแปร usersRef


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_clothes_formal)
        supportActionBar?.hide()
        init()
        mAuth = FirebaseAuth.getInstance() // กำหนดค่าให้ตัวแปร mAuth
        back_formal!!.setOnClickListener {
            val intent = Intent(this, menu_clothes::class.java)
            startActivity(intent)
        }
        getGenderFromFirebase() // เรียกใช้งานเพื่อดึงค่าเพศจาก Firebase Realtime Database
        formal_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generateClothes()
        }
    }

    fun init() {
        formal_result = findViewById(R.id.formal_result)
        formal_info = findViewById(R.id.formal_info)
        formal_gen = findViewById(R.id.formal_gen)
        back_formal = findViewById(R.id.back_gen_clothes_formal)
    }


    fun generateClothes() {
        // Clear the textview value to the default value
        formal_result?.text = "xxxxxxxx"
        formal_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            formal_result,
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
        // Check if the user has clicked on the formal menu before starting the random clothes generation
        if (isFormalMenuClicked) {
            val maleClothes = listOf(
                "เสื้อเชิ้ต (Dress shirt)",
                "เสื้อโปโล (Polo shirt)",
                "เสื้อยีนส์ (Dress shirt with jeans)"
            )
            val femaleClothes = listOf("เสื้อโค้ท (Blouse)", "เสื้อเฟิร์ม (Formal blouse)")

            val randomClothes = if (selectedGender == "Male") {
                maleClothes
            } else {
                femaleClothes
            }
            val randomIndex = Random.nextInt(randomClothes.size)
            val selectedClothes = randomClothes[randomIndex]
            formal_result?.text = selectedClothes

            when (selectedClothes) {
                "เสื้อเชิ้ต (Dress shirt)" -> formal_info?.text =
                    " เป็นเสื้อที่มีคอพับหรือคอเรียบ มักมีปุ่มด้านหน้าและมีแขนยาว เหมาะสำหรับการสวมใส่ร่วมกับสูทหรือกางเกงขายาวในงาน"

                "เสื้อโปโล (Polo shirt)" -> formal_info?.text =
                    "เป็นเสื้อที่มีคอพอโล และมักมีแขนสั้น มักถูกใส่ในสถานการณ์ที่ต้องการความเป็นกันเองและมีความสะดวกสบาย เช่น งานสังสรรค์หรือกิจกรรมนอกสถานที่"

                "เสื้อยีนส์ (Dress shirt with jeans)" -> formal_info?.text =
                    "เสื้อเชิ้ตที่ผสมผสานกับกางเกงยีนส์ ซึ่งเป็นสไตล์ที่มีความเป็นกันเองและสะดวกสบาย เหมาะสำหรับสถานที่ทำงานที่ไม่มีบรรยากาศเป็นกันเองมากนัก"

                "เสื้อโค้ท (Blouse)" -> formal_info?.text =
                    "เป็นเสื้อที่มีลักษณะคล้ายเสื้อเชิ้ตแต่มักมีดีเทลและดีไซน์ที่สวยงามมากขึ้น เหมาะสำหรับงานสังสรรค์หรืองานที่ต้องการความอ่อนโยนและสวยงาม"

                "เสื้อเฟิร์ม (Formal blouse)" -> formal_info?.text =
                    "เป็นเสื้อที่มักจะมีดีไซน์ที่เรียบหรูและสมควร เหมาะสำหรับงานสำคัญ เช่น งานแต่งงานหรืองานเลี้ยงสำคัญ"

                else -> formal_info?.text = "ไม่มีข้อมูล"
            }
        }
    }

    fun getGenderFromFirebase() {
        val userId = mAuth?.currentUser?.uid
        if (userId != null) {
            val userGenderRef = usersRef.child(userId).child("gender")
            userGenderRef.get().addOnSuccessListener { dataSnapshot ->
                val userGender = dataSnapshot.getValue(String::class.java)
                userGender?.let {
                    selectedGender = it
                    // Do not call generateClothes() here, it will be called after animation ends
                }
            }.addOnFailureListener { e ->
                // Handle failure
            }
        }
    }
}





