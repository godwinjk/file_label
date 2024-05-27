package com.github.godwinjk.filelabel.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "com.github.godwinjk.filelabel.settings.FileTagSettings", storages = [Storage(value = "file_tag.xml")])
class FileTagSettings : PersistentStateComponent<FileTagSettings> {
    companion object {
        fun getInstance(): FileTagSettings {
            return ApplicationManager.getApplication().getService(FileTagSettings::class.java)
        }
    }

    var listOfFileTag: String = ""

    override fun getState(): FileTagSettings {
        return this
    }

    override fun loadState(state: FileTagSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }
}