package com.example.mypasswordwithcoroutineandroomdatabase.roomDB

import androidx.room.*
import com.example.mypasswordwithcoroutineandroomdatabase.model.Accounts


@Dao
interface AccountDao {

    @Query("SELECT * FROM Accounts")
    suspend fun getAll() : List<Accounts>

    @Insert
        //(onConflict == OnConflictStrategy.REPLACE)
    suspend fun insert(account : Accounts)

    @Delete
    suspend fun delete(account: Accounts)
}