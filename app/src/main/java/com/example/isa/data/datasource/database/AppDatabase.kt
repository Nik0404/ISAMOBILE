package com.example.isa.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.isa.data.datasource.database.dao.FunctionDao
import com.example.isa.data.datasource.database.dao.PacketContentDao
import com.example.isa.data.datasource.database.dao.PacketNegotiationDao
import com.example.isa.data.datasource.database.dao.PacketTaskDao
import com.example.isa.data.datasource.database.dao.UserDao
import com.example.isa.domain.entity.local.database.PacketContent
import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.domain.entity.local.database.PacketTask
import com.example.isa.domain.entity.local.database.User
import com.example.isa.domain.entity.local.database.UserFunction

@Database(
    entities = [User::class, UserFunction::class, PacketNegotiation::class, PacketContent::class, PacketTask::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun userFunctionDao(): FunctionDao

    abstract fun packetNegotiationDao(): PacketNegotiationDao

    abstract fun packetContentDao(): PacketContentDao

    abstract fun packetTaskDao(): PacketTaskDao
}
