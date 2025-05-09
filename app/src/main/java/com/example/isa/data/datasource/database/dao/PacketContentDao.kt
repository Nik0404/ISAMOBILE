package com.example.isa.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.isa.domain.entity.local.database.PacketContent
import com.example.isa.domain.entity.local.display.DisplayPacketcontent
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PacketContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePacketContent(packetContent: List<PacketContent>): Completable

    @Query("DELETE FROM packet_content")
    fun clearAllPack(): Completable

    @Query("""
    SELECT 
        id AS id,
        pack_id AS packId,
        name AS name,
        title AS title,
        component_type AS componentType,
        version AS version,
        subsystem AS subsystem,
        developer AS developer,
        status AS status,
        dt_public AS dtPublic
    FROM packet_content
""")
    fun getPacketsContent(): Single<List<DisplayPacketcontent>>

    @Query("SELECT * FROM packet_content WHERE pack_id = :packId")
    fun getByPackId(packId: Int): Single<List<PacketContent>>
}