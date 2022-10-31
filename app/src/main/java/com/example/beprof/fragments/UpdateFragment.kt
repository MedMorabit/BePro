package com.example.beprof.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beprof.Constant
import com.example.beprof.R
import com.example.beprof.databinding.FragmentUpdateBinding
import com.example.beprof.models.Note
import com.example.beprof.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_note_fragment.*
import kotlinx.android.synthetic.main.fragment_update.*
@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private val args:UpdateFragmentArgs by navArgs()
    lateinit var binding:FragmentUpdateBinding
    lateinit var updateColor:String
    private val viewModelUpdate:HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentUpdateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent=args.currentNote
        updateColor= intent!!.color
        setIntentData(intent)
        binding.apply {
            grayUpdate.setOnClickListener {
                graySelected()
            }
            yellowUpdate.setOnClickListener {
                yellowSelected()
            }
            redUpdate.setOnClickListener {
                redSelected()
            }
            btn_update_note.setOnClickListener {
                updateNote()
            }
            backNoteUpdate.setOnClickListener {
               navigateToHomeFragment()
            }

        }



    }

    private fun updateNote() {
        if(validation()){
            val title=update_titleNote.text.trim().toString()
            val note=update_bodyNote.text.trim().toString()

            viewModelUpdate.updateNote(Note(title,note,updateColor, args.currentNote!!.id))
            navigateToHomeFragment()
        }else{
            Toast.makeText(requireContext(), "enter all data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validation(): Boolean {
       return !(update_bodyNote.text.isNullOrEmpty() || update_titleNote.text.isNullOrEmpty())
    }


    private fun setIntentData(intent: Note?) {
        binding.apply {
            updateBodyNote.setText(intent?.note)
            updateTitleNote.setText(intent?.title)
            setColor(intent?.color)
        }
    }

    private fun setColor(color: String?) {
        when(color){
            Constant.gray ->graySelected()
            Constant.yellow -> yellowSelected()
            Constant.red-> redSelected()
        }
    }
    private fun graySelected(){
        binding.grayUpdate.setImageResource(R.drawable.ic_baseline_done_24)
        binding.redUpdate.setImageResource(0)
        binding.yellowUpdate.setImageResource(0)
        updateColor=Constant.gray
    }
    private fun yellowSelected(){
        binding.yellowUpdate.setImageResource(R.drawable.ic_baseline_done_24)
        binding.redUpdate.setImageResource(0)
        binding.grayUpdate.setImageResource(0)
        updateColor=Constant.yellow
    }
    private fun redSelected(){
        binding.redUpdate.setImageResource(R.drawable.ic_baseline_done_24)
        binding.yellowUpdate.setImageResource(0)
        binding.grayUpdate.setImageResource(0)
        updateColor=Constant.red
    }
  private fun navigateToHomeFragment(){
      findNavController().navigate(R.id.action_updateFragment_to_notesFragment)
  }


}