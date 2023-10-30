package uz.muhsinov.developer.mohirdev_retrofit.todo

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)