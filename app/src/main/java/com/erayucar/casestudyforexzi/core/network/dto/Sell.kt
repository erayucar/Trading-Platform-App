package com.erayucar.casestudyforexzi.core.network.dto

data class Sell(
    val count: Int,
    val price: Int,
    val rate: Long,
    val rate_f: String,
    val volume: Long,
    val volume_f: String
)