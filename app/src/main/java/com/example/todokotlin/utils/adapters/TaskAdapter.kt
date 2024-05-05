package com.example.todokotlin.utils.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todokotlin.databinding.EachTodoItemBinding
import com.example.todokotlin.utils.ToDoData

class TaskAdapter(private val list: MutableList<ToDoData>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private  val TAG = "TaskAdapter"
    private var listener:TaskAdapterInterface? = null
    fun setListener(listener:TaskAdapterInterface){
        this.listener = listener
    }
    class TaskViewHolder(val binding: EachTodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.todoTask.text = this.task

                Log.d(TAG, "onBindViewHolder: "+this)
                binding.editTask.setOnClickListener {
                    listener?.onEditItemClicked(this , position)
                }

                binding.deleteTask.setOnClickListener {
                    listener?.onDeleteItemClicked(this , position)
                }
                binding.checkBox.setOnClickListener {view: View ->
                    val checkBox = view as CheckBox
                    if (checkBox.isChecked) {
                        // Display toast message
                        Toast.makeText(view.context, "Task Completed!!", Toast.LENGTH_SHORT).show()
                    } else {
                        // If you want to do something when the CheckBox is unchecked
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface TaskAdapterInterface{
        fun onDeleteItemClicked(toDoData: ToDoData , position : Int)
        fun onEditItemClicked(toDoData: ToDoData , position: Int)
    }

}