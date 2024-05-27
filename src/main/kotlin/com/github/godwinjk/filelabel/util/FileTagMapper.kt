package com.github.godwinjk.filelabel.util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.godwinjk.filelabel.data.FileTag

/*fun mapToFileTags(string: String): List<FileTag> {
    if (string.isBlank()) return arrayListOf()
    val list = jacksonObjectMapper().readValue(string, object : TypeReference<List<FileTag>>() {})
    return list
}*/

fun String.mapToFileTags(): List<FileTag> {
    if (this.isBlank()) return arrayListOf()
    val list = jacksonObjectMapper().readValue(this, object : TypeReference<List<FileTag>>() {})
    return list
}


fun getStringFromFileTags(fileTags: List<FileTag>): String {
    return jacksonObjectMapper().writeValueAsString(fileTags)
}