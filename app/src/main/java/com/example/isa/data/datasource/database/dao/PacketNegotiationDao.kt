package com.example.isa.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PacketNegotiationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePacketNegotiation(packetNegotiation: List<PacketNegotiation>): Completable

    @Query("""
    SELECT 
        id,
        pack_id AS packId,
        task_list AS taskList,
        blocking_task_list AS blockingTaskName,
        pack_description AS packDescription,
        pack_name AS packName,
        ts_create AS tsCreate,
        ts_send AS tsSend,
        urc_dev_fnm AS urcDev,
        urc_lead_dev_fnm AS urcLeadDev,
        urc_sign_exec AS urcSignExec,
        urc_sign_time AS urcSignTime,
        is_signed AS isSigned,
        main_urc_curator AS manUrcCurator,
        urc_curator AS urcCurator,
        max_pack_change as maxPackChange,
        max_pack_change_snm as maxPackChangeSnm
    FROM packet_negotiation
""")
    fun getPacketNegotiationAll(): Single<List<DisplayPacketNegotiation>>

    @Query("SELECT * FROM packet_negotiation WHERE id = :packetId")
    fun getPacketById(packetId: Int): Single<PacketNegotiation>

}