package com.example.testp1

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class language : AppCompatActivity() {

    private var btn_th: Button? = null
    private var btn_en: Button? = null
    private var btn_fr: Button? = null
    private var language_back_setting: ImageButton? = null
    private var layoutEng: ConstraintLayout? = null
    private var textEng: TextView? = null
    private var layoutTh: ConstraintLayout? = null
    private var textThai: TextView? = null
    private var layoutFr: ConstraintLayout? = null
    private var textFr: TextView? = null
    private val PREFS_NAME = "MyPrefsFile"
    private val SELECTED_LANGUAGE_KEY = "selectedLanguage"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        supportActionBar?.hide()
        init()

        // โหลดค่าภาษาที่ถูกเลือกล่าสุดจาก SharedPreferences
        val prefs = getSharedPreferences(PREFS_NAME, 0)
        val selectedLanguage = prefs.getString(SELECTED_LANGUAGE_KEY, "en") // "en" เป็นค่าเริ่มต้นหากไม่มีค่า

        // ตรวจสอบภาษาที่ถูกเลือกล่าสุดแล้วเรียก setLocale ตามนั้น
        when (selectedLanguage) {
            "en" -> setLocale("en")
            "th" -> setLocale("th")
            "fr" -> setLocale("fr")
            else -> setLocale("en") // หากไม่พบค่าที่ถูกต้อง ให้เปลี่ยนเป็น "en" (หรือค่าเริ่มต้นที่คุณต้องการ)
        }


        btn_th?.setOnClickListener {
            // ตรวจสอบเมื่อปุ่มถูกคลิก
            setLocale("th")
            saveSelectedLanguage("th")
        }

        btn_en?.setOnClickListener {
            // ตรวจสอบเมื่อปุ่มถูกคลิก
            setLocale("en")
            saveSelectedLanguage("en")
        }

        btn_fr?.setOnClickListener {
            // ตรวจสอบเมื่อปุ่มถูกคลิก
            setLocale("fr")
            saveSelectedLanguage("fr")
        }

        //this layout
        language_back_setting?.setOnClickListener {
            val intent = Intent(this, Setting::class.java)
            startActivity(intent)

        }
    }

    private fun saveSelectedLanguage(languageCode: String?) {
        // บันทึกค่าภาษาที่ถูกเลือกล่าสุดใน SharedPreferences
        val prefs = getSharedPreferences(PREFS_NAME, 0).edit()
        prefs.putString(SELECTED_LANGUAGE_KEY, languageCode.orEmpty()) // orEmpty() จะกำหนดค่าเป็น "" ถ้า languageCode เป็น null
        prefs.apply()
    }


    private fun setLocale(languageCode: String) {
        val locale = java.util.Locale(languageCode)
        java.util.Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.setLocale(locale)
        baseContext.resources.updateConfiguration(configuration, baseContext.resources.displayMetrics)

        // อัพเดทหน้าปัจจุบัน (เรียก recreate())
        // recreate()
        when (languageCode) {
            "en" -> {
                // เปลี่ยน background ของ ConstraintLayout
                layoutEng?.setBackgroundResource(R.drawable.btn_action)
                // เปลี่ยน text color ของ TextView
                textEng?.setTextColor(resources.getColor(android.R.color.white))

                // รีเซ็ต background และ text color ของปุ่มที่ไม่ถูกเลือก
                layoutTh?.setBackgroundResource(R.drawable.btn_language) /* รีเซ็ตเป็น background ตามที่ต้องการ */
                textThai?.setTextColor(Color.parseColor("#0CBFDE")) /* รีเซ็ตเป็น text color ตามที่ต้องการ */
                layoutFr?.setBackgroundResource(R.drawable.btn_language) /* รีเซ็ตเป็น background ตามที่ต้องการ */
                textFr?.setTextColor(Color.parseColor("#0CBFDE")) /* รีเซ็ตเป็น text color ตามที่ต้องการ */
            }

            "th" -> {
                // เปลี่ยน background ของ ConstraintLayout
                layoutTh?.setBackgroundResource(R.drawable.btn_action)
                // เปลี่ยน text color ของ TextView
                textThai?.setTextColor(resources.getColor(android.R.color.white))

                // รีเซ็ต background และ text color ของปุ่มที่ไม่ถูกเลือก
                layoutEng?.setBackgroundResource(R.drawable.btn_language) /* รีเซ็ตเป็น background ตามที่ต้องการ */
                textEng?.setTextColor(Color.parseColor("#0CBFDE")) /* รีเซ็ตเป็น text color ตามที่ต้องการ */
                layoutFr?.setBackgroundResource(R.drawable.btn_language) /* รีเซ็ตเป็น background ตามที่ต้องการ */
                textFr?.setTextColor(Color.parseColor("#0CBFDE")) /* รีเซ็ตเป็น text color ตามที่ต้องการ */
            }

            "fr" -> {
                // เปลี่ยน background ของ ConstraintLayout
                layoutFr?.setBackgroundResource(R.drawable.btn_action)
                // เปลี่ยน text color ของ TextView
                textFr?.setTextColor(resources.getColor(android.R.color.white))

                // รีเซ็ต background และ text color ของปุ่มที่ไม่ถูกเลือก
                layoutEng?.setBackgroundResource(R.drawable.btn_language) /* รีเซ็ตเป็น background ตามที่ต้องการ */
                textEng?.setTextColor(Color.parseColor("#0CBFDE")) /* รีเซ็ตเป็น text color ตามที่ต้องการ */
                layoutTh?.setBackgroundResource(R.drawable.btn_language) /* รีเซ็ตเป็น background ตามที่ต้องการ */
                textThai?.setTextColor(Color.parseColor("#0CBFDE")) /* รีเซ็ตเป็น text color ตามที่ต้องการ */
            }
        }
    }
    private fun init() {
        btn_th = findViewById(R.id.btn_th)
        btn_en = findViewById(R.id.btn_en)
        btn_fr = findViewById(R.id.btn_fr)
        language_back_setting = findViewById(R.id.language_back_setting)
        layoutEng = findViewById(R.id.layout_eng)
        textEng = findViewById(R.id.text_eng)
        layoutTh = findViewById(R.id.layout_th)
        textThai = findViewById(R.id.text_thai)
        layoutFr = findViewById(R.id.layout_fr)
        textFr = findViewById(R.id.text_fr)
    }
}
