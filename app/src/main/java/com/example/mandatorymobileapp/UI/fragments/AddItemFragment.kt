package com.example.mandatorymobileapp.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mandatorymobileapp.databinding.AddItemFragmentBinding
import com.example.mandatorymobileapp.model.Item
import dk.easj.anbo.bookstoremvvm.models.ItemsViewModel


class AddItemFragment : Fragment() {
    private var _binding: AddItemFragmentBinding? = null
    private val binding get() = _binding!!

    private val itemsViewModel: ItemsViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddItemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addBtn.setOnClickListener {
        val title = binding.inputTitle.text.toString().trim()
        val price = binding.inputPrice.text.toString().trim().toDouble()
        val seller = binding.inputSeller.text.toString().trim()
        val pictureURL = binding.inputPictureURL.text.toString().trim()
        val description = binding.inputDescription.text.toString().trim()
        val date = 0
            val addItem = Item(title,  price, description, date, seller, pictureURL)
        Log.d("ADD", "add $addItem")
            itemsViewModel.add(addItem)
            findNavController().popBackStack()}

        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

