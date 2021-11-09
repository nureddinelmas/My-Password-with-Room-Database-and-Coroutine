package com.example.mypasswordwithcoroutineandroomdatabase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mypasswordwithcoroutineandroomdatabase.R
import com.example.mypasswordwithcoroutineandroomdatabase.adapter.AccountAdapter
import com.example.mypasswordwithcoroutineandroomdatabase.databinding.ActivityMainBinding
import com.example.mypasswordwithcoroutineandroomdatabase.databinding.ActivityPasswordBinding
import com.example.mypasswordwithcoroutineandroomdatabase.model.Accounts
import com.example.mypasswordwithcoroutineandroomdatabase.roomDB.AccountDao
import com.example.mypasswordwithcoroutineandroomdatabase.roomDB.AccountDatabase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

   // val coroutineContext : CoroutineContext
    // get() = Dispatchers.Main + job

    lateinit var db : AccountDatabase

    private lateinit var job : Job
   var accountDao: AccountDao? = null

    private var accountList : List<Accounts>? = null

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        job = Job()

        db = Room.databaseBuilder(applicationContext, AccountDatabase::class.java, "Accounts")
            .build()



        accountList = ArrayList<Accounts>()

        GlobalScope.launch {
            displayAccounts()
        }



       binding.addCard.setOnClickListener {
           val intent = Intent(this, PasswordActivity::class.java)
           startActivity(intent)
       }
    }



    private suspend fun displayAccounts(){
        accountList = db.accountDao.getAll()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AccountAdapter(accountList!!)
        binding.recyclerView.adapter = adapter
    }
}