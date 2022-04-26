package com.example.mandatorymobileapp.UI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mandatorymobileapp.R
import com.example.mandatorymobileapp.databinding.ListFragmentBinding
import com.example.mandatorymobileapp.model.MyAdapter
import dk.easj.anbo.bookstoremvvm.models.ItemsViewModel


class List_Fragment : Fragment(R.layout.list_fragment) {
    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    private val itemsViewModel: ItemsViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsViewModel.itemsLiveData.observe(viewLifecycleOwner) { items ->
            binding.progressbar.visibility = View.GONE
            binding.recyclerView.visibility = if (items == null) View.GONE else View.VISIBLE
            if (items != null) {
                val adapter = MyAdapter(items) { position ->
                    val action = List_FragmentDirections.actionListFragmentToItemFragment(position)
                    findNavController().navigate(action)
                }
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                binding.recyclerView.adapter = adapter
            }
        }

        itemsViewModel.errorMessageLiveData.observe(viewLifecycleOwner) { errorMessage ->
            binding.textviewMessage.text = errorMessage
        }

        itemsViewModel.reload()

        binding.swiperefresh.setOnRefreshListener {
            itemsViewModel.reload()
            binding.swiperefresh.isRefreshing = false
        }

        binding.button.setOnClickListener { displayAddFragment() }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayAddFragment(){
        findNavController().navigate(R.id.action_list_Fragment_to_addItemFragment)
    }

}



