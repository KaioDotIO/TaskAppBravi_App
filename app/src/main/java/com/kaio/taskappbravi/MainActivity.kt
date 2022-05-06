package com.kaio.taskappbravi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.kaio.taskappbravi.databinding.ActivityMainBinding
import com.kaio.taskappbravi.db.TaskEntity
import com.kaio.taskappbravi.network.Endpoint
import com.kaio.taskappbravi.network.NetworkUtils
import com.kaio.taskappbravi.network.Tasks
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllTasksObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        binding.btnAcceptTask.setOnClickListener {
            val name = binding.tvNameTask.text.toString()
            val task = TaskEntity(
                0, name, "", 0, 0.1, "", "", 0.1
            )
            viewModel.insertTaskInfo(task)
            getData()
        }
    }

    override fun onDeleteUserClickListener(user: TaskEntity) {}

    override fun onItemClickListener(task: TaskEntity) {
        binding.tvNameTask.setText(task.activity)
        binding.tvNameTask.setTag(binding.tvNameTask.id, task.id)
        getData()
    }

    fun setup() {
        getData()
    }

    fun getData() {
        var types = arrayOf(
            "No Filter",
            "Busywork",
            "Charity",
            "Cooking",
            "Diy",
            "Education",
            "Music",
            "Recreational",
            "Relaxation",
            "Social"
        )

        spinnerType.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)

        binding.btnNewTask.setOnClickListener {

            val retrofitClient = NetworkUtils.getRetrofitInstance("https://www.boredapi.com/api/")
            val endpoint = retrofitClient.create(Endpoint::class.java)
            var callback =
                endpoint.getPostsByType(types[spinnerType.selectedItemPosition].lowercase())

            if (binding.spinnerType.selectedItemPosition == 0) {
                callback = endpoint.getPosts()
            }

            callback.enqueue(object : Callback<Tasks> {
                override fun onFailure(call: Call<Tasks>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                }

                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<Tasks>, response: Response<Tasks>) {
                    binding.tvNameTask.text = "${response.body()?.activity.toString()}"
                    binding.tvAccessibility.text =
                        "Accessibility: ${response.body()?.accessibility.toString()}"
                    binding.tvType.text = "Type: ${response.body()?.type.toString()}"
                    binding.tvParticipants.text =
                        "Participants: ${response.body()?.participants.toString()}"
                    binding.tvPrice.text = "Price: ${response.body()?.price.toString()}"
                    binding.tvLink.text = "Link: ${response.body()?.link.toString()}"
                }
            })
        }
    }
}



