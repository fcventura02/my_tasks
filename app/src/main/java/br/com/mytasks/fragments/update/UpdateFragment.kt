package br.com.mytasks.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.mytasks.R
import br.com.mytasks.model.Task
import br.com.mytasks.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private  val args by navArgs<UpdateFragmentArgs>()

    private lateinit var  mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.updateTask.setText(args.currentTask.task)
        view.cb_update_important.isChecked = args.currentTask.important
        view.cb_update_completed.isChecked = args.currentTask.complet

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

       view.btm_update.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val task = updateTask.text.toString()
        val complet = cb_update_completed.isChecked
        val important = cb_update_important.isChecked

        if (inputCheck(task)){
            //Create Task Object
            val updatedTask = Task(args.currentTask.id, task,important,complet)
            //Update Current Task
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(),"Updated Successfully!", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(),"Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(task: String):Boolean{
        return !(TextUtils.isEmpty(task))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteTask()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim"){_,_->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentTask.task}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Não"){_,_->

        }
        builder.setTitle("Deletar ${args.currentTask.task}?")
        builder. setMessage("Você tem certeza que quer deletar ${args.currentTask.task}")
        builder.create().show()
    }

}
