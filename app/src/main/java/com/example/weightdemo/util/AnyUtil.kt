package com.example.weightdemo.util

fun String?.isEqual(b: String?): Boolean {
    if (this == null || b == null) {
        return false
    }
    return this == b
}
