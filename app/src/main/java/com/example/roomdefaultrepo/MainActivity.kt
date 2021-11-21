package com.example.roomdefaultrepo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.roomdefaultrepo.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var db : AppDatabase
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: RoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        adapter = RoomAdapter()

        binding.recyclerView.adapter = adapter
        db = AppDatabase.getInstance(this)!!

        loadRecycler()
        binding.saveDataButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                withContext(CoroutineScope(Dispatchers.IO).coroutineContext){
                    db.memoDao().insertData(Memo(binding.saveDateEditText.text.toString(), "subText"))
                }
                loadRecycler()
            }
        }

        binding.deleteDataButton.setOnClickListener {
            var list: MutableList<Memo>
            CoroutineScope(Dispatchers.Main).launch {
                withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                    list = db.memoDao().getAll() as MutableList<Memo>
                    db.memoDao().delete(list[binding.deleteDataEditText.text.toString().toInt()])
                }
                loadRecycler()
            }
        }
    }
    private fun loadRecycler(){
        var list: MutableList<Memo>
        CoroutineScope(Dispatchers.Main).launch {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                list = db.memoDao().getAll() as MutableList<Memo>
            }
            adapter.apply {
                this.item = list
                notifyDataSetChanged()
            }
        }
    }
}