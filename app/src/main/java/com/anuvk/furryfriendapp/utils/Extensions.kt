package com.anuvk.furryfriendapp.utils

fun String.convertFirstCharToCapital(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecaseChar() else it
    }
}