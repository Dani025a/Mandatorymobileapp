package com.example.mandatorymobileapp.UI.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mandatorymobileapp.databinding.ItemFragmentBinding
import com.example.mandatorymobileapp.model.Item
import dk.easj.anbo.bookstoremvvm.models.ItemsViewModel

class ItemFragment : Fragment() {
    private var itemFragmentBinding: ItemFragmentBinding? = null
    private val binding get() = itemFragmentBinding!!
    private val itemsViewModel: ItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        itemFragmentBinding = ItemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = requireArguments()
        val itemFragmentArgs: ItemFragmentArgs = ItemFragmentArgs.fromBundle(bundle)
        val position = itemFragmentArgs.position
        val item = itemsViewModel[position]
        if (item == null) {
            binding.textviewMessage.text = "No item"
            return
        }
        binding.inputTitle.setText(item.title)
        binding.inputPrice.setText(item.price.toString())
        binding.inputSeller.setText(item.seller)
        binding.inputPictureURL.setText(item.pictureUrl)
        binding.inputDescription.setText(item.description)



        binding.deleteBtn.setOnClickListener {
            itemsViewModel.delete(item.id)
            findNavController().popBackStack()
        }

        binding.editBtn.setOnClickListener {
            val title = binding.inputTitle.text.toString().trim()
            val price = binding.inputPrice.text.toString().trim().toDouble()
            val seller = binding.inputSeller.text.toString().trim()
            val pictureURL = binding.inputPictureURL.text.toString().trim()
            val description = binding.inputDescription.text.toString().trim()
            val date = 0
            val updatedItem = Item(item.id, title,  price, description, date, seller, pictureURL)
            Log.d("UPDATE", "update $updatedItem")
            itemsViewModel.update(updatedItem)
            findNavController().popBackStack()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        itemFragmentBinding = null
    }


}