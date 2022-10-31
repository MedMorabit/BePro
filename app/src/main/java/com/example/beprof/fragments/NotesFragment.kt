package com.example.beprof.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beprof.OnNoteClickListener
import com.example.beprof.R
import com.example.beprof.activities.HomeActivity
import com.example.beprof.adapter.NotesAdapter
import com.example.beprof.databinding.FragmentNotesBinding
import com.example.beprof.models.Note
import com.example.beprof.util.DeleteNoteFragment
import com.example.beprof.util.Resource
import com.example.beprof.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
@AndroidEntryPoint
class NotesFragment (): Fragment(R.layout.fragment_notes),OnNoteClickListener {

    private val viewModel:HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentNotesBinding.bind(view)
        val noteAdapter=NotesAdapter(this)
        binding.apply {
            notesRecycler.apply {
                adapter=noteAdapter
                layoutManager=GridLayoutManager(requireContext(),2)
                setHasFixedSize(true)
            }
            addNoteFAB.setOnClickListener {
                findNavController().navigate(R.id.action_notesFragment_to_addNoteFragment)
            }

        }
        viewModel.note.observe(viewLifecycleOwner){
            noteAdapter.submitList(it)
        }
    }


   override fun onClick(itemNote: Note) {
     val action=NotesFragmentDirections.actionNotesFragmentToUpdateFragment(itemNote)
      Navigation.findNavController(requireView()).navigate(action)
   }

    override fun onLongClick(note: Note) {
     createDialogFragment(note)
    }
    private fun createDialogFragment(note: Note){
       DeleteNoteFragment{
           viewModel.deleteNote(note)
       }.show(childFragmentManager,"delete")
    }
}