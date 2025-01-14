package com.example.testp1

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

import android.media.AudioManager
import android.widget.Switch



class Setting : AppCompatActivity() {

    private var backSetting: ImageButton? = null
    private var deleteButton: Button? = null
    private var set_btn_language: ImageButton? = null
    private var set_btn_faq: ImageButton? = null
    private var rate_app: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportActionBar?.hide()
        init()

        val soundSwitch = findViewById<Switch>(R.id.soundSwitch)

        soundSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // เปิดเสียงโทรศัพท์
                setPhoneSound(true)
            } else {
                // ปิดเสียงโทรศัพท์
                setPhoneSound(false)
            }
        }


        rate_app?.setOnClickListener {
            showRatingDialog()
        }


        backSetting?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        set_btn_language?.setOnClickListener {
            val intent = Intent(this, language::class.java)
            startActivity(intent)
        }

        set_btn_faq?.setOnClickListener {
            val intent = Intent(this, faq::class.java)
            startActivity(intent)
        }

        deleteButton?.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                // มีผู้ใช้ที่เข้าสู่ระบบอยู่
                // ทำการลบบัญชีผู้ใช้งาน
                user.delete()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // การลบบัญชีผู้ใช้สำเร็จ
                            Toast.makeText(this, "บัญชีผู้ใช้ถูกลบแล้ว", Toast.LENGTH_SHORT).show()
                            // ไปยังหน้าล็อกอินหลังจากออกจากระบบ
                            val intent = Intent(this, login1::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // การลบบัญชีผู้ใช้ไม่สำเร็จ
                            Toast.makeText(
                                this,
                                "เกิดข้อผิดพลาดในการลบบัญชีผู้ใช้",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                // ไม่มีผู้ใช้ที่เข้าสู่ระบบ
                Toast.makeText(this, "กรุณาเข้าสู่ระบบเพื่อทำการลบบัญชี", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showRatingDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.ratethisapp, null)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.dialog_rating_bar)
        val commentEditText = dialogView.findViewById<EditText>(R.id.dialog_comment_edit_text)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("บันทึก") { dialogInterface, _ ->
                val rating = ratingBar.rating.toInt()
                val comment = commentEditText.text.toString()

                saveRatingToDatabase(rating, comment)

                dialogInterface.dismiss()
                checkIfUserRated()
            }
            .setNegativeButton("ยกเลิก") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()


        dialogBuilder.show()
    }



    private fun saveRatingToDatabase(rating: Int, comment: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance().reference
        user?.uid?.let { uid ->
            val ratingData = HashMap<String, Any>()
            ratingData["rating"] = rating
            ratingData["comment"] = comment
            database.child("users").child(uid).setValue(ratingData)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "บันทึกข้อมูลเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "เกิดข้อผิดพลาดในการบันทึกข้อมูล", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }

    private fun checkIfUserRated() {
        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance().reference
        user?.uid?.let { uid ->
            database.child("users").child(uid).get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    // ตรวจสอบว่ามีข้อมูลคะแนนของผู้ใช้นี้ในฐานข้อมูลหรือไม่
                    val userData = dataSnapshot.value as HashMap<*, *>
                    if (userData.containsKey("rating")) {
                        // ถ้ามีข้อมูลคะแนนแล้ว ให้ปิดการให้ผู้ใช้ลงคะแนนอีก
                        rate_app?.isEnabled = false
                        Toast.makeText(this, "คุณได้ลงคะแนนแอปนี้ไปแล้ว", Toast.LENGTH_SHORT).show()
                    } else {
                        // ถ้ายังไม่มีข้อมูลคะแนน ให้เปิดให้ผู้ใช้ลงคะแนนได้
                        rate_app?.isEnabled = true
                    }
                } else {
                    // ถ้ายังไม่มีข้อมูลของผู้ใช้ในฐานข้อมูล ให้เปิดให้ผู้ใช้ลงคะแนนได้
                    rate_app?.isEnabled = true
                }
            }.addOnFailureListener { exception ->
                // หากเกิดข้อผิดพลาดในการอ่านข้อมูลจากฐานข้อมูล
                Log.e(TAG, "เกิดข้อผิดพลาดในการอ่านข้อมูล: ${exception.message}")
                rate_app?.isEnabled = true // เปิดให้ผู้ใช้ลงคะแนนได้ในกรณีที่ไม่สามารถตรวจสอบได้
            }
        }
    }
    private fun setPhoneSound(isSoundOn: Boolean) {
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        if (isSoundOn) {
            // เปิดเสียงโทรศัพท์
            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
        } else {
            // ปิดเสียงโทรศัพท์
            audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
        }
    }




    private fun init() {
        backSetting = findViewById(R.id.back_setting)
        deleteButton = findViewById(R.id.Setting_Delete)
        set_btn_language = findViewById(R.id.set_btn_language)
        rate_app = findViewById(R.id.rate_app)
        set_btn_faq = findViewById(R.id.set_btn_faq)

    }
}


