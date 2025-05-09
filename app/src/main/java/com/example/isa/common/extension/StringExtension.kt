package com.example.isa.common.extension

import com.example.isa.common.constants.Constants

fun String.toId(): Int = try {
    Integer.parseInt(this)
} catch (e: NumberFormatException) {
    Constants.DEFAULT_INVALID_ID
}