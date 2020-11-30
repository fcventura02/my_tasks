package br.com.mytasks.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.mytasks.R
import br.com.mytasks.model.Task
import br.com.mytasks.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class addFragment : Fragment() {

    private lateinit var mtaskViewModel: TaskViewModel

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mtaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)


          view.btm_add.setOnClickListener {
              insertDataToDatabase()
          }

            return  view
    }

    private fun insertDataToDatabase() {
        val task = addTask.text.toString()
        val important = cb_important.isChecked

        if(inputCheck(task)){
            //Create task
            val new_task = Task(0, task, important, false)
            //Add Data
            mtaskViewModel.addTask(new_task)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            //navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(task: String):Boolean{
        return !(TextUtils.isEmpty(task))
    }


}