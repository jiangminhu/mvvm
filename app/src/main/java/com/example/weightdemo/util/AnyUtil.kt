package com.example.weightdemo.util

fun String?.isEqual(b: String?): Boolean {
    if (this == null) {
        return false
    }
    if (b == null) {
        return false
    }

    return this == b
}