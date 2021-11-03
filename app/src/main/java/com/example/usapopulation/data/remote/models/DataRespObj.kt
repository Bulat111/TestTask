package com.example.usapopulation.data.remote.models

import com.google.gson.annotations.SerializedName

data class DataRespObj(
    @SerializedName("ID Nation")
    val idNation: String,
    @SerializedName("Nation")
    val nation: String,
    @SerializedName("ID Year")
    val idYear: Int,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Population")
    val population: Int,
    @SerializedName("Slug Nation")
    val slugNation: String
)