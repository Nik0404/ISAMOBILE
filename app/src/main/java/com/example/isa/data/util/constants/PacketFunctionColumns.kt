package com.example.isa.data.util.constants

object PacketFunctionColumns {

    const val ID_TASK_COLUMN_NAME = "ID_TASK"
    const val ID_TASK_COLUMN_POSITION = 0

    const val FUNCTION_ID_COLUMN_NAME = "FUNCTION_ID"
    const val FUNCTION_ID_COLUMN_POSITION = 1

    const val FUNCTION_ID_FNM_COLUMN_NAME = "FUNCTION_ID_FNM"
    const val FUNCTION_ID_FNM_COLUMN_POSITION = 2

    const val WRITE_ACCESS_COLUMN_NAME = "WRITE_ACCESS"
    const val WRITE_ACCESS_COLUMN_POSITION = 3

    const val READY_COLUMN_NAME = "READY"
    const val READY_COLUMN_POSITION = 4

    const val SCOPE_COLUMN_NAME = "SCOPE"
    const val SCOPE_COLUMN_POSITION = 5

    const val SCOPE_SNM_COLUMN_NAME = "SCOPE_SNM"
    const val SCOPE_SNM_COLUMN_POSITION = 6

    const val SUBSYSTEM_COLUMN_NAME = "SUBSYSTEM"
    const val SUBSYSTEM_COLUMN_POSITION = 7

    const val SUBSYSTEM_FNM_COLUMN_NAME = "SUBSYSTEM_FNM"
    const val SUBSYSTEM_FNM_COLUMN_POSITION = 8

    const val SUBSYSTEM_SNM_COLUMN_NAME = "SUBSYSTEM_SNM"
    const val SUBSYSTEM_SNM_COLUMN_POSITION = 9

    const val DB_OBJ_PREFIX_COLUMN_NAME = "DB_OBJ_PREFIX"
    const val DB_OBJ_PREFIX_COLUMN_POSITION = 10

    const val IS_ISA_2005_COLUMN_NAME = "IS_ISA_2005"
    const val IS_ISA_2005_COLUMN_POSITION = 11

    const val IS_ISA_2010_COLUMN_NAME = "IS_ISA_2010"
    const val IS_ISA_2010_COLUMN_POSITION = 12

    const val IS_ISA_WEB_COLUMN_NAME = "IS_ISA_WEB"
    const val IS_ISA_WEB_COLUMN_POSITION = 13

    const val IS_START_ISA_WEB_COLUMN_NAME = "IS_START_ISA_WEB"
    const val IS_START_ISA_WEB_COLUMN_POSITION = 14

    fun getColumns(): List<String> {
        return listOf(
            ID_TASK_COLUMN_NAME,
            FUNCTION_ID_COLUMN_NAME,
            FUNCTION_ID_FNM_COLUMN_NAME,
            WRITE_ACCESS_COLUMN_NAME,
            READY_COLUMN_NAME,
            SCOPE_COLUMN_NAME,
            SCOPE_SNM_COLUMN_NAME,
            SUBSYSTEM_COLUMN_NAME,
            SUBSYSTEM_FNM_COLUMN_NAME,
            SUBSYSTEM_SNM_COLUMN_NAME,
            DB_OBJ_PREFIX_COLUMN_NAME,
            IS_ISA_2005_COLUMN_NAME,
            IS_ISA_2010_COLUMN_NAME,
            IS_ISA_WEB_COLUMN_NAME,
            IS_START_ISA_WEB_COLUMN_NAME
        )
    }
}