package com.example.isa.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.isa.domain.entity.local.database.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User): Single<Long>

    @Query("SELECT * FROM user WHERE id =:userId")
    fun getUserById(userId: Int): Single<User>
}
