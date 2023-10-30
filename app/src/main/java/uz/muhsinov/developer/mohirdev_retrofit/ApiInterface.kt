package uz.muhsinov.developer.mohirdev_retrofit


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import uz.muhsinov.developer.mohirdev_retrofit.todo.Todo
import uz.muhsinov.developer.mohirdev_retrofit.user.UserCreate
import uz.muhsinov.developer.mohirdev_retrofit.user.UserListResponce
import uz.muhsinov.developer.mohirdev_retrofit.user.UserResponce

interface ApiInterface {
    @GET("/todos")
    suspend fun getTodoList():Response<ArrayList<Todo>>

    @GET("/api/users")
    suspend fun getAllUsersByPage(@Query("page")page:Int,@Query("per_page")perPage:Int):Response<UserListResponce>

    @GET("api/users/{id}")
    suspend fun getUserById(@Path("id")  id:Int):Response<UserResponce>

    @POST("/api/users")
    suspend fun createUser(@Body userCreate: UserCreate):Response<UserCreate>

    @DELETE("/api/users/{id}")
    suspend fun deleteUser(@Path("id")userId:Int):Response<Unit>

}