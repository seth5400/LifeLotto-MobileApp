package com.example.testp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.ImageButton

class privacy_policy : AppCompatActivity() {
    var privacy_back: ImageButton? = null

    private lateinit var expandableListView: ExpandableListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        supportActionBar?.hide()

        expandableListView = findViewById(R.id.expandableListView)
        privacy_back = findViewById(R.id.privacy_back)
        val listDataHeader = listOf(
            "Information Collection and Use",
            "Log Data",
            "Cookies",
            "Service Providers",
            "Security",
            "Links to Other Sites",
            "Changes to This Privacy Policy",
            "Contact Us"
        )
        val listDataChild = hashMapOf(
            "Information Collection and Use" to listOf(
                PPItem(
                    "Information Collection and Use",
                    "For a better experience, while using our Service, I may require you to provide us with certain personally identifiable information, including but not limited to email,name. The information that I request will be retained on your device and is not collected by me in any way.\n"
                            + "\n" + "The app does use third-party services that may collect information used to identify you.\n" +
                            "\n" +
                            "Link to the privacy policy of third-party service providers used by the app\n" + "\n" + "Google Play Services\n" +
                            "Google Analytics for Firebase\n" +
                            "Firebase Crashlytics"
                )
            ),
            "Log Data" to listOf(
                PPItem(
                    "Log Data",
                    "I want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third-party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics."
                )
            ),
            "Cookies" to listOf(
                PPItem(
                    "Cookies",
                    "Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory.\n" + "\n" +
                            "This Service does not use these “cookies” explicitly. However, the app may use third-party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service."
                )
            ),

            "Service Providers" to listOf(
                PPItem(
                    "Service Providers",
                    "I may employ third-party companies and individuals due to the following reasons:\n" +
                            "\n" + "To facilitate our Service;\n" + "To provide the Service on our behalf;\n" + "To perform Service-related services; or\n" + "To assist us in analyzing how our Service is used.\n" + "\n" + "I want to inform users of this Service that these third parties have access to their Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose."
                )
            ),
            "Security" to listOf(
                PPItem(
                    "Security",
                    "I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security."
                )
            ),
            "Links to Other Sites" to listOf(
                PPItem(
                    "Links to Other Sites",
                    "This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me.\n" + "Therefore, I strongly advise you to review the Privacy Policy of these websites. I have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services."
                )
            ),
            "Changes to This Privacy Policy" to listOf(
                PPItem(
                    "Changes to This Privacy Policy",
                    "I may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Privacy Policy on this page.\n"+"This policy is effective as of 2024-03-06"
                )
            ),
            "Contact Us" to listOf(
                PPItem(
                    "Contact Us",
                    "If you have any questions or suggestions about my Privacy Policy, do not hesitate to contact me at Jonathan.doillon11@gmail.com."
                )
            ),

            )

        val listAdapter = PPAdapter(this, listDataHeader, listDataChild)
        expandableListView.setAdapter(listAdapter)

        // Set group click listener to toggle the expansion/collapse of items
        expandableListView.setOnGroupClickListener { _, _, _, _ -> false }

        privacy_back?.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }

    }

}