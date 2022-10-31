package com.example.beprof.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.beprof.databinding.DialogDeleteBinding

class DeleteNoteFragment(
   var onDelete:(View)->Unit
): DialogFragment() {
    lateinit var binding:DialogDeleteBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DialogDeleteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
             dialogCancel.setOnClickListener {
                 dismiss()
             }
            dialogYes.setOnClickListener {
                onDelete(it)
                dismiss()
                Toast.makeText(requireContext(), "Note Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

