package com.example.assignment2_contacts

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewContactActivity : AppCompatActivity() {

    private lateinit var editNameView : EditText
    private lateinit var editPhoneNumView : EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)
        editNameView = findViewById(R.id.edit_name)
        editPhoneNumView = findViewById(R.id.edit_phone_num)

        val buttonCancel = findViewById<Button>(R.id.button_cancel)
        val buttonSave = findViewById<Button>(R.id.button_save)

        buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNameView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val contactName = editNameView.text.toString()
                val contactPhoneNum = editPhoneNumView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY1, contactName)
                replyIntent.putExtra(EXTRA_REPLY2, contactPhoneNum)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
            }

        buttonCancel.setOnClickListener {

        }

        }

    companion object {
        const val EXTRA_REPLY1 = "com.example.android.contactlistsql.REPLY"
        const val EXTRA_REPLY2 = "com.example.android.contactlistsql.REPLY"
    }

}