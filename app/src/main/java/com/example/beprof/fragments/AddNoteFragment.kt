package com.example.beprof.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beprof.Constant
import com.example.beprof.R
import com.example.beprof.databinding.AddNoteFragmentBinding
import com.example.beprof.models.Note
import com.example.beprof.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_note_fragment.*
import kotlinx.android.synthetic.main.note_items.*

@AndroidEntryPoint
class AddNoteFragment: Fragment(R.layout.add_note_fragment),OnClickListener {
    lateinit var binding:AddNoteFragmentBinding
    private var  noteColor:String=Constant.gray

    private val viewModel:HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding=AddNoteFragmentBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener(this)
        binding.apply {
            backNote.setOnClickListener(this@AddNoteFragment)
            red.setOnClickListener(this@AddNoteFragment)
             gray.setOnClickListener(this@AddNoteFragment)
            yellow.setOnClickListener(this@AddNoteFragment)
            btnAddNote.setOnClickListener(this@AddNoteFragment)

        }
    }



    private fun selectBackground(selected:ImageView,color:String) {
        selected.setImageResource(R.drawable.ic_baseline_done_24)
         noteColor=color
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.red -> {
                selectBackground(binding.red,Constant.red)
                binding.gray.setImageResource(0)
                binding.yellow.setImageResource(0)
            }
            R.id.gray -> {
                selectBackground(binding.gray,Constant.gray)
                binding.red.setImageResource(0)
                binding.yellow.setImageResource(0)
            }
            R.id.yellow -> {
                selectBackground(binding.yellow,Constant.yellow)
                binding.gray.setImageResource(0)
                binding.red.setImageResource(0)
            }
            R.id.backNote ->{
                findNavController().navigate(R.id.action_addNoteFragment_to_notesFragment)
            }
            R.id.btn_add_note ->{
                insertNote()
            }
        }
    }

    private fun insertNote() {
        if(validations()){
            val title=edt_titleNote.text.trim().toString()
            val notetxt=edt_bodyNote.text.trim().toString()
            Note(title,notetxt, noteColor)
                .let {
                    viewModel.insertNote(it)
                }
            findNavController().navigate(R.id.action_addNoteFragment_to_notesFragment)
        }else
            Toast.makeText(requireContext(), "please enter all", Toast.LENGTH_SHORT).show()

    }


    private fun validations(): Boolean {
        return !(edt_titleNote.text.isNullOrEmpty()
                && edt_bodyNote.text.isNullOrEmpty()
             )
    }


}