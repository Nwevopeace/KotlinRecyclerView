package com.peacecodes.kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.textfield.TextInputEditText
import com.peacecodes.kotlinrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: ContactAdapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let { title = it.getStringExtra("NAME") }
        setUpData(binding)
    }

    private fun setUpData(binding: ActivityMainBinding) {
        binding.contactRv.adapter = adapter
        binding.contactRv.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val name = view.findViewById<TextInputEditText>(R.id.etName)
        val no = view.findViewById<TextInputEditText>(R.id.etNumber)
        val saveBtn = view.findViewById<Button>(R.id.saveButton)

        no.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                saveBtn.isEnabled = p0?.length == 11
            }

            override fun afterTextChanged(p0: Editable?) { }
        })

        val alertDialog = builder.create()

        saveBtn.setOnClickListener {
            val contact = Contact(name.text.toString(), no.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContacts(contacts)
            alertDialog.dismiss()
        }

        binding.fab.setOnClickListener {
            alertDialog.show()
        }
    }
}