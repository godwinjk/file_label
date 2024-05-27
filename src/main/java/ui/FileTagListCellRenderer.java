package ui;

import com.github.godwinjk.filelabel.data.FileTag;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.intellij.vcs.log.ui.filter.VcsStructureChooser.BORDER;

public class FileTagListCellRenderer extends JLabel implements ListCellRenderer<FileTag> {
    private final OnDeleteClickListener deleteClickListener;

    public FileTagListCellRenderer(OnDeleteClickListener deleteClickListener) {
        this.deleteClickListener = deleteClickListener;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends FileTag> list, FileTag value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            FileTagPanel panel = new FileTagPanel(value, deleteClickListener);

            panel.setBorder(BorderFactory.createCompoundBorder(panel.getBorder(), BORDER)); // Add divider
            return panel;
        }
        return this;
    }
}