package com.github.godwinjk.filelabel.settings

import com.fasterxml.jackson.jr.ob.JSON
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.godwinjk.filelabel.MyBundle
import com.github.godwinjk.filelabel.util.getStringFromFileTags
import com.github.godwinjk.filelabel.util.mapToFileTags
import com.intellij.openapi.options.Configurable
import ui.FileLabelSettingsUI
import javax.swing.JComponent

class FileTagConfigurable : Configurable {
    private lateinit var componet: FileLabelSettingsUI

    override fun createComponent(): JComponent? {
        val settings: FileTagSettings = FileTagSettings.getInstance()
        componet = FileLabelSettingsUI()
        componet.fileTagList = settings.listOfFileTag.mapToFileTags()
        return componet.panel
    }

    override fun isModified(): Boolean {
        val settings: FileTagSettings = FileTagSettings.getInstance()
        val listOfTags = settings.listOfFileTag.mapToFileTags()
        return componet.fileTagList != listOfTags
    }

    override fun reset() {
        super.reset()
        val settings: FileTagSettings = FileTagSettings.getInstance()
        componet.fileTagList = settings.listOfFileTag.mapToFileTags()
    }

    override fun apply() {
        val settings: FileTagSettings = FileTagSettings.getInstance()
        settings.listOfFileTag = getStringFromFileTags(componet.fileTagList)
    }

    override fun getDisplayName(): String {
        return MyBundle.getMessage("fileTagSettings")
    }
}