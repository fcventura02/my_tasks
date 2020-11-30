package br.com.mytasks.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mytasks.R
import br.com.mytasks.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class listFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { task->
            adapter.setData(task)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //add Menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim"){_,_->
            mTaskViewModel.deleteAllTask()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("Não"){_,_->

        }
        builder.setTitle("Deletar tudo?")
        builder. setMessage("Você tem certeza que quer deletar tudo")
        builder.create().show()
    }
}