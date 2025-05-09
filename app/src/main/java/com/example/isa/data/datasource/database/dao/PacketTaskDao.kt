package com.example.isa.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.domain.entity.local.database.PacketTask
import com.example.isa.domain.entity.local.display.DisplayPacketTask
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PacketTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePacketTask(packetTasks: List<PacketTask>): Completable

    @Query("DELETE FROM packet_task")
    fun clearAllTask(): Completable

    @Query(
        """
        SELECT 
            id AS id,
            task_id AS taskId,
            doc_incoming AS docIncoming,
            name_task AS nameTask,
            date_task_tr AS dateTaskTr,
            client_devap_name AS clientDevap,
            doc_registry_name AS docRegistryName,
            curator_name AS curator,
            dev_name AS dev,
            tst_name AS tst,
            name_state_task AS nameStateTask,
            doc_sender_committer AS committer,
            memo_task AS memoTask
        FROM packet_task
        """
    )
    fun getTask(): Single<List<DisplayPacketTask>>

    @Query("SELECT * FROM packet_task WHERE id = :taskId")
    fun getTaskById(taskId: Int): Single<PacketTask>
}