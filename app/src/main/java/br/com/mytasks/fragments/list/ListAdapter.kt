package br.com.mytasks.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.mytasks.R
import br.com.mytasks.model.Task
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>(){
    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.itemView.tv_task.text = currentItem.task

        if (currentItem.important)
        holder.itemView.img_icon_important.visibility = View.VISIBLE
        else  holder.itemView.img_icon_important.visibility = View.INVISIBLE
        holder.itemView.rowLayout.setOnClickListener { v ->
            val action =   listFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(task: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }

}