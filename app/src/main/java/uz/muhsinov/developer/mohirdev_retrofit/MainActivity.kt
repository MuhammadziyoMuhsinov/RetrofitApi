package uz.muhsinov.developer.mohirdev_retrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.muhsinov.developer.mohirdev_retrofit.databinding.ActivityMainBinding
import uz.muhsinov.developer.mohirdev_retrofit.todo.RvAdapter
import uz.muhsinov.developer.mohirdev_retrofit.user.User
import uz.muhsinov.developer.mohirdev_retrofit.user.UserAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var rvAdapter : RvAdapter
    private lateinit var userAdapter:UserAdapter
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvAdapter = RvAdapter()
        userAdapter = UserAdapter()

        val retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/")
            .build()
        val api = retrofit.create(ApiInterface::class.java)

        val manager = LinearLayoutManager(this@MainActivity)
        binding.rv.apply {
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(this@MainActivity,manager.orientation))
            adapter = userAdapter
        }

        val flow : Flow<PagingData<User>> = Pager(
            config = PagingConfig(pageSize = 3, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(api) }
        ).flow

        lifecycleScope.launch {
         flow.collect{
             userAdapter.submitData(it)
         }
        }

        lifecycleScope.launch {
            userAdapter.loadStateFlow.collect{
                binding.proggress.isVisible = it.source.append is LoadState.Loading
            }
        }

//
//        lifecycleScope.launch {
//          val response =   api.getUserById(1)
//            Log.d("TAG", "onCreate: ${response.code()}")
//            if (response.isSuccessful){
//                Log.d("TAG", "onCreate: ${response.body()}")
//            }else{
//                Log.d("TAG", "onCreate: somsing wrong")
//            }
//        }
//
//        lifecycleScope.launch {
//            val response = api.createUser(UserCreate("","","Mobile developer","Muhammadziyo"))
//            if (response.isSuccessful){
//                Log.d("TAG", "onCreate: ${response.body()}")
//            }
//        }
//
//
//
//
//        lifecycleScope.launch {
//            val response = api.deleteUser(2)
//            if (response.isSuccessful){
//                Log.d("TAG", "onCreate: ${response.body()}")
//            }else{
//                Log.d("TAG", "onCreate: Something went wrong!")
//            }
//        }


//        lifecycleScope.launch {
//           val response = api.getTodoList().body()
//            response?.apply {
//                rvAdapter.submitList(this)
//            }
//
//        }


    }
}