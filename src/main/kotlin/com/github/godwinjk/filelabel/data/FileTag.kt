package com.github.godwinjk.filelabel.data

import ai.grazie.utils.Color
import com.intellij.ui.Colors

data class FileTag(val tag: String, val path: String, val color: Int = -1, val colorSet: Int = 0)