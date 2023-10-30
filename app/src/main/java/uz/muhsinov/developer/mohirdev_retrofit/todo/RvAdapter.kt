package uz.muhsinov.developer.mohirdev_retrofit.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.muhsinov.developer.mohirdev_retrofit.databinding.ItemRvBinding


class TodoDiff: DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem===newItem
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem==newItem
    }
}

class RvAdapter: ListAdapter<Todo, RvAdapter.TodoViewHolder>(TodoDiff()) {

    inner class TodoViewHolder(val binding: ItemRvBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo){
         binding.title.text = todo.title
            binding.checkbox.isChecked = todo.completed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}