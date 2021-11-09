package com.example.mypasswordwithcoroutineandroomdatabase.roomDB


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mypasswordwithcoroutineandroomdatabase.model.Accounts
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = arrayOf(Accounts::class), version = 1)
abstract class AccountDatabase : RoomDatabase() {
    abstract val accountDao: AccountDao

    companion object{

        @Volatile
        private var INSTANCE : AccountDatabase? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context): AccountDatabase{
            synchronized(this){
                var instance  = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AccountDatabase::class.java,
                        "account_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

            return instance
            }
        }
    }

}