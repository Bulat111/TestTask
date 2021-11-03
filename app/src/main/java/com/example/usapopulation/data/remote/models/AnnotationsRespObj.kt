package com.example.usapopulation.data.remote.models

import com.google.gson.annotations.SerializedName

data class AnnotationsRespObj(
    @SerializedName("source_name")
    val sourceName: String,
    @SerializedName("source_description")
    val sourceDescription: String,
    @SerializedName("dataset_name")
    val datasetName: String,
    @SerializedName("dataset_link")
    val datasetLink: String,
    @SerializedName("table_id")
    val tableId: String,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("subtopic")
    val subtopic: String
)