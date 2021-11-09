package com.example.mypasswordwithcoroutineandroomdatabase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.mypasswordwithcoroutineandroomdatabase.databinding.ActivityPasswordBinding
import com.example.mypasswordwithcoroutineandroomdatabase.model.Accounts
import com.example.mypasswordwithcoroutineandroomdatabase.roomDB.AccountDao
import com.example.mypasswordwithcoroutineandroomdatabase.roomDB.AccountDatabase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PasswordActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPasswordBinding
    private lateinit var job :Job
    private lateinit var accountDao: AccountDao

    val coroutineContext : CoroutineContext
    get() = Dispatchers.Main + job

    lateinit var db : AccountDatabase

    var accountList : Accounts? = null

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        job = Job()

        db = Room.databaseBuilder(applicationContext, AccountDatabase::class.java, "Accounts").build()

    }
     fun clickedButton(view : View){
        val account = Accounts(binding.textTitle.text.toString(), binding.textUserName.text.toString(), binding.textPassword.text.toString(), binding.textComment.text.toString() )

        job = CoroutineScope(Dispatchers.IO).launch {
            db.accountDao.insert(account)

            withContext(Dispatchers.Main){
                val intent = Intent(this@PasswordActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }

}