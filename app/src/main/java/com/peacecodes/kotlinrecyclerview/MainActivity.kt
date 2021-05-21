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

    fun setUpData(binding: ActivityMainBinding){
        binding.contactRv.adapter = adapter
        binding.contactRv.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val name = view.findViewById<TextView>(R.id.etName)
        val no = view.findViewById<TextView>(R.id.etNumber)
        val saveBtn = view.findViewById<Button>(R.id.saveButton)
        no.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                   saveBtn.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        val alertDialog = builder.create()
        saveBtn.setOnClickListener{
            val contact = Contact(name.text.toString(), no.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setUpContacts(contacts)
            alertDialog.dismiss()
        }
        binding.fab.setOnClickListener{
            alertDialog.show()
        }
    }
}