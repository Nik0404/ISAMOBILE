package com.example.isa.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.isa.domain.entity.local.database.UserFunction
import io.reactivex.Single

@Dao
interface FunctionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFunctions(functions: List<UserFunction>): Single<List<Long>>

    @Query("SELECT * FROM function")
    fun getFunctions(): Single<List<UserFunction>>


}