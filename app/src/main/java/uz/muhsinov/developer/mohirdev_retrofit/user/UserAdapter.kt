package uz.muhsinov.developer.mohirdev_retrofit.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.muhsinov.developer.mohirdev_retrofit.databinding.ItemRvBinding
import uz.muhsinov.developer.mohirdev_retrofit.databinding.ItemUserBinding
import uz.muhsinov.developer.mohirdev_retrofit.todo.RvAdapter
import uz.muhsinov.developer.mohirdev_retrofit.todo.Todo

class UserDiff : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

class UserAdapter : PagingDataAdapter<User, UserAdapter.UserViewHolder>(UserDiff()) {

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User?) {
        binding.name.text = user?.getFullName()
            Glide.with(binding.root).load(user?.avatar).into(binding.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}