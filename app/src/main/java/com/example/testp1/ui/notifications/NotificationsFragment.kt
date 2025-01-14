package com.example.testp1.ui.notifications

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.testp1.R
import com.example.testp1.Setting
import com.example.testp1.contact_us
import com.example.testp1.databinding.FragmentNotificationsBinding
import com.example.testp1.login1
import com.example.testp1.privacy_policy
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class NotificationsFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    private lateinit var userEmail: TextView
    private lateinit var Userchange: TextView
    private lateinit var Passchange: TextView
    private lateinit var Usershow: TextView
    private lateinit var singout: Button
    private lateinit var Settingbtn: ImageButton
    private lateinit var Contactbtn: ImageButton


    private lateinit var PPbtn: ImageButton
    private var profileImage: ImageView? = null
    private var selectedImageUri: Uri? = null
    private val REQUEST_IMAGE = 2
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser



        userEmail = binding.ProfileEmail
        Userchange = binding.changeUser
        Passchange = binding.changePassword
        profileImage = binding.imageProfile
        Usershow = binding.ProfileUser
        singout = binding.ProfileLogout
        Settingbtn = binding.ProfileSetting
        Contactbtn = binding.ContactSetting
        PPbtn = binding.PrivacySetting

        userEmail.text = user!!.email
        loadProfileImage()
        // ดึงข้อมูลชื่อผู้ใช้จาก Firebase Realtime Database
        val userId: String = user.uid
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")
        usersRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val username: String = dataSnapshot.child("username").value.toString()
                    Usershow.text = username // กำหนดค่าชื่อผู้ใช้ใน TextView "Profile_user"
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // กรณีเกิดข้อผิดพลาดในการดึงข้อมูล
            }
        })

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users = firebaseAuth.currentUser
            if (users == null) {
                startActivity(Intent(requireActivity(), login1::class.java))
                requireActivity().finish()
            }
        }

        singout.setOnClickListener {
            mAuth.signOut()
            Toast.makeText(requireContext(), "Signed out!", Toast.LENGTH_LONG).show()
            Log.d(TAG, "Signed out!")
            startActivity(Intent(requireActivity(), login1::class.java))
            requireActivity().finish()
        }
        profileImage?.setOnClickListener {
            // เมื่อคลิกที่รูปโปรไฟล์
            selectImage()
        }

        Settingbtn.setOnClickListener {
            val intent = Intent(requireContext(), Setting::class.java)
            startActivity(intent)
        }

        Contactbtn.setOnClickListener {
            val intent = Intent(requireContext(), contact_us::class.java)
            startActivity(intent)
        }

        PPbtn.setOnClickListener {
            val intent = Intent(requireContext(), privacy_policy::class.java)
            startActivity(intent)
        }

        Userchange.setOnClickListener {
            openUsernameEditor()
        }

        Passchange.setOnClickListener {
            openPassEditor()
        }



        return root
    }
    private fun loadProfileImage() {
        val userId: String = mAuth.currentUser?.uid ?: return
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")
        usersRef.child(userId).child("profileImageUrl").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val profileImageUrl = dataSnapshot.value.toString()
                // Load the profile image into ImageView using Glide or Picasso
                Picasso.get().load(profileImageUrl)
                    .resize(profileImage!!.width, profileImage!!.height)
                    .centerCrop()
                    .into(profileImage!!)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                Log.e(TAG, "Error loading profile image", databaseError.toException())
            }
        })
    }


    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri: Uri = data.data!!
            // Upload the selected image to Firebase Storage
            uploadImageToFirebaseStorage(imageUri)
        }
    }

    private fun uploadImageToFirebaseStorage(imageUri: Uri) {
        val userId: String = mAuth.currentUser?.uid ?: return
        val storageRef = FirebaseStorage.getInstance().reference.child("profile_images") // Set the folder name to store images
        val imageFileName = "profile_image_$userId" // Set the image file name
        val imageRef = storageRef.child(imageFileName)

        imageRef.putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                // Image upload successful
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    // Generate URI of the uploaded image
                    val downloadUrl = uri.toString()
                    // Save the image URL in Firebase Realtime Database
                    saveImageUrlToDatabase(userId, downloadUrl)
                }
            }
            .addOnFailureListener { exception ->
                // Image upload failed
                Toast.makeText(requireContext(), "Failed to upload profile image: ${exception.message}", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Failed to upload profile image", exception)
            }
    }

    private fun saveImageUrlToDatabase(userId: String, imageUrl: String) {
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")
        usersRef.child(userId).child("profileImageUrl").setValue(imageUrl)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Profile image uploaded successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to upload profile image URL to database!", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Failed to upload profile image URL to database", task.exception)
                }
            }
    }



    private fun openUsernameEditor() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.changeprofiletest, null)
        val editTextUsername = dialogView.findViewById<EditText>(R.id.editTextUsername)

        // สร้าง AlertDialog
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle("แก้ไขชื่อผู้ใช้")
            .setView(dialogView)
            .setPositiveButton("บันทึก") { dialog, which ->
                // เมื่อคลิกปุ่มบันทึก
                val newUsername = editTextUsername.text.toString()
                updateUsername(newUsername) // อัปเดตชื่อผู้ใช้ใน Firebase Realtime Database
            }
            .setNegativeButton("ยกเลิก") { dialog, which ->
                // เมื่อคลิกปุ่มยกเลิก
                dialog.dismiss() // ปิด AlertDialog
            }
            .create()

        // แสดง AlertDialog
        alertDialogBuilder.show()
    }


    private fun updateUsername(newUsername: String) {
        val userId: String = mAuth.currentUser?.uid ?: return
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")

        // อัปเดตข้อมูล username ใน Firebase Realtime Database
        usersRef.child(userId).child("username").setValue(newUsername)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "บันทึกข้อมูลเรียบร้อยแล้ว",
                        Toast.LENGTH_SHORT
                    ).show()

                    // อัปเดต textview Usershow ด้วยค่าใหม่ที่ได้จาก Firebase Realtime Database
                    Usershow.text = newUsername
                } else {
                    Toast.makeText(
                        requireContext(),
                        "เกิดข้อผิดพลาดในการบันทึกข้อมูล",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e(TAG, "เกิดข้อผิดพลาดในการบันทึกข้อมูล", task.exception)
                }
            }
    }
    private fun openPassEditor() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.changepasswordlayout, null)
        val editTextCurrentPass = dialogView.findViewById<EditText>(R.id.editTextCurrentPassword)
        val editTextNewPass = dialogView.findViewById<EditText>(R.id.editTextNewPassword)
        val editTextConfirmPass = dialogView.findViewById<EditText>(R.id.editTextConfirmNewPassword)

        // สร้าง AlertDialog
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle("Change Password")
            .setView(dialogView)
            .setPositiveButton("Save", null) // กำหนด null เพื่อยกเลิกการปิด dialog โดยอัตโนมัติ
            .setNegativeButton("Cancel") { dialog, which ->
                // เมื่อคลิกปุ่มยกเลิก
                dialog.dismiss() // ปิด AlertDialog
            }

        val alertDialog = alertDialogBuilder.create() // สร้าง AlertDialog ก่อน
        alertDialog.setOnShowListener {
            val button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                val currentPassword = editTextCurrentPass.text.toString()
                val newPassword = editTextNewPass.text.toString()
                val confirmPassword = editTextConfirmPass.text.toString()

                if (currentPassword.isEmpty()) {
                    editTextCurrentPass.error = "Please enter your current password"
                    return@setOnClickListener
                }

                if (newPassword != confirmPassword) {
                    editTextConfirmPass.error = "Passwords do not match!"
                    return@setOnClickListener
                }

                // ตรวจสอบรหัสผ่านปัจจุบันก่อนที่จะอัปเดตรหัสผ่านใน Firebase Authentication
                val user = mAuth.currentUser
                val credential = EmailAuthProvider.getCredential(user!!.email!!, currentPassword)
                user.reauthenticate(credential)
                    .addOnCompleteListener { reAuthTask ->
                        if (reAuthTask.isSuccessful) {
                            // หลังจากการยืนยันรหัสผ่านปัจจุบันสำเร็จ
                            // ทำการอัปเดตรหัสผ่านใน Firebase Authentication
                            user.updatePassword(newPassword)
                                .addOnCompleteListener { updatePassTask ->
                                    if (updatePassTask.isSuccessful) {
                                        Toast.makeText(requireContext(), "Password changed successfully!", Toast.LENGTH_SHORT).show()
                                        alertDialog.dismiss() // ปิดหน้าต่าง dialog เมื่อเปลี่ยนรหัสผ่านเรียบร้อยแล้ว
                                    } else {
                                        Toast.makeText(requireContext(), "Failed to change password! Please try again.", Toast.LENGTH_SHORT).show()
                                        Log.e(TAG, "Failed to change password", updatePassTask.exception)
                                    }
                                }
                        } else {
                            // รหัสผ่านปัจจุบันไม่ถูกต้อง
                            editTextCurrentPass.error = "Incorrect current password"
                            Log.e(TAG, "Incorrect current password", reAuthTask.exception)
                        }
                    }
            }
        }

        // แสดง AlertDialog
        alertDialog.show()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadProfileImage()
    }


    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onStop() {
        super.onStop()
        mAuth.removeAuthStateListener(mAuthListener)
    }

    override fun onDestroyView() {
        selectedImageUri = null
        super.onDestroyView()
        _binding = null
    }
}
