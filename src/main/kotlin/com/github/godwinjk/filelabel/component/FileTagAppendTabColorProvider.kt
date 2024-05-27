package com.github.godwinjk.filelabel.component

import com.github.godwinjk.filelabel.settings.FileTagSettings
import com.github.godwinjk.filelabel.util.mapToFileTags
import com.intellij.openapi.fileEditor.impl.EditorTabColorProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.awt.Color

class FileTagAppendTabColorProvider : EditorTabColorProvider {
    override fun getEditorTabColor(project: Project, file: VirtualFile): Color? {
        val settings = FileTagSettings.getInstance()
        return isInSpecificFolder(settings, file)

    }

    private fun isInSpecificFolder(settings: FileTagSettings, file: VirtualFile): Color? {
        var listOfTags = settings.listOfFileTag.mapToFileTags()
//        var listOfTags = mapToFileTags(settings.listOfFileTag)
        listOfTags = listOfTags.sortedByDescending { it.path.length }

        if (listOfTags.isEmpty()) return null
        for (tag in listOfTags) {
            if (file.path.contains(tag.path)) {
                if (tag.colorSet == 1) {
                    return Color(tag.color)
                }
            }
        }
        return null
    }
}