package com.peacecodes.kotlinrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peacecodes.kotlinrecyclerview.databinding.ContactListItemBinding

class ContactAdapter:RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private val contacts = mutableListOf<Contact>()

    inner class ContactViewHolder(private val binding: ContactListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bindItem(contact: Contact){
            binding.nameTv.text = contact.name
            binding.numberTv.text = contact.number
        }
    }

    fun setUpContacts(contact: List<Contact>){
        this.contacts.addAll(contacts)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ContactListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
      val contact = contacts[position]
        holder.bindItem(contact)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}