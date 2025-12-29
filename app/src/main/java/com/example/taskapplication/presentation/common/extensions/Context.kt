package com.example.taskapplication.presentation.common.extensions

import android.content.Context
import android.view.LayoutInflater

@Suppress("UNCHECKED_CAST")
fun<T> Context.getSystemServiceAs(serviceName: String) = getSystemService(serviceName) as T

val Context.layoutInflater: LayoutInflater
    get() = getSystemServiceAs(Context.LAYOUT_INFLATER_SERVICE)