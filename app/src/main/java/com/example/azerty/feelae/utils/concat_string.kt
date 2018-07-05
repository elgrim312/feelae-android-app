package com.example.azerty.feelae.utils

import java.util.ArrayList

fun concat(datas : ArrayList<String>) : StringBuilder {
    val stringBuilder = StringBuilder()

    datas.forEach{
        stringBuilder.append("- ${it}\n")
    }

    return stringBuilder
}