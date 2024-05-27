package com.github.godwinjk.filelabel.component

import com.github.godwinjk.filelabel.settings.FileTagSettings
import com.github.godwinjk.filelabel.util.mapToFileTags
import com.intellij.openapi.fileEditor.impl.EditorTabTitleProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class FileTagAppendTabTileProvider : EditorTabTitleProvider {
    override fun getEditorTabTitle(project: Project, file: VirtualFile): String {

        val settings = FileTagSettings.getInstance()
        val appendName = isInSpecificFolder(settings, file)

        return "$appendName ${file.name}"

    }

    private fun isInSpecificFolder(settings: FileTagSettings, file: VirtualFile): String {
        var listOfTags = settings.listOfFileTag.mapToFileTags()
//        var listOfTags = mapToFileTags(settings.listOfFileTag)
        listOfTags = listOfTags.sortedByDescending { it.path.length }

        if (listOfTags.isEmpty()) return ""
        for (tag in listOfTags) {
            if (file.path.contains(tag.path)) {
                return tag.tag
            }
        }
        return ""
    }
}
