package com.example.roomdefaultrepo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemoDao {

    @Query("Select * from Memo")
    fun getAll() : List<Memo>

    @Query("Select mainText from Memo")
    fun getAllContents() : LiveData<List<String>>

    @Insert
    fun insertData(vararg memo: Memo)

    @Delete
    fun delete(memo : Memo)

    @Update
    fun updateData(memo: Memo)
}