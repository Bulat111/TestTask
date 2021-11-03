package com.example.usapopulation.data.remote.models

import com.google.gson.annotations.SerializedName

data class SourceRespObj(
    @SerializedName("measures")
    val measures: List<String>,
    @SerializedName("annotations")
    val annotations: AnnotationsRespObj,
    @SerializedName("name")
    val name: String,
    @SerializedName("substitutions")
    val substitutions: List<String>
)