package com.example.usapopulation.data.remote.models

import com.google.gson.annotations.SerializedName

data class PopulationRespObj(
    @SerializedName("data")
    val data: List<DataRespObj>,
    @SerializedName("source")
    val source: List<SourceRespObj>
)