package com.picpay.desafio.android.extensions

import android.content.Context

val Context.shortDelayAnimTime: Long
    get() = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()