package com.example.testp1

import android.content.Intent
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class faq : AppCompatActivity() {
    var faq_back: ImageButton? = null

    private lateinit var expandableListView: ExpandableListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        supportActionBar?.hide()

        expandableListView = findViewById(R.id.expandableListView)
        faq_back = findViewById(R.id.faq_back)
        val listDataHeader =
            listOf(
                "What is Life Lotto??",
                "What should I do if I encounter issues with the app?",
                "Is there any cost associated with using this app?",
                "Which operating systems does this app support?",
                "Can users share the results of their random selections on social media?"
            )
        val listDataChild = hashMapOf(
            "What is Life Lotto??" to listOf(
                FAQItem(
                    "What is Life Lotto??",
                    "Life Lotto is an application that facilitates users in randomizing various aspects of their daily lives, such as selecting meals, clothing, activities, or songs, to help them experience new things in their daily routines."
                )
            ),
            "What should I do if I encounter issues with the app?" to listOf(
                FAQItem(
                    "What should I do if I encounter issues with the app?",
                    "You can contact our support team through various channels specified on the app's contact page or call us at Tel 080-385-2628."
                )
            ),
            "Is there any cost associated with using this app?" to listOf(
                FAQItem(
                    "Is there any cost associated with using this app?",
                    "No, our app offers free services to all users without any additional charges for basic usage. You can access all features and menus immediately without any costs."
                )
            ),
            "Which operating systems does this app support?" to listOf(
                FAQItem(
                    "Which operating systems does this app support?",
                    "Our app supports both iOS and Android operating systems, accessible through smartphones and tablets."
                )
            ),
            "Can users share the results of their random selections on social media?" to listOf(
                FAQItem(
                    "Can users share the results of their random selections on social media?",
                    "No, they cannot."
                )
            )

        )

        val listAdapter = FAQAdapter(this, listDataHeader, listDataChild)
        expandableListView.setAdapter(listAdapter)

        // Set group click listener to toggle the expansion/collapse of items
        expandableListView.setOnGroupClickListener { _, _, _, _ -> false }

        faq_back?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }

}