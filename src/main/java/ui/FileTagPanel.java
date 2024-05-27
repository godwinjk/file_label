package ui;

import com.github.godwinjk.filelabel.data.FileTag;

import javax.swing.*;
import java.awt.*;

import static com.intellij.vcs.log.ui.filter.VcsStructureChooser.BORDER;


public class FileTagPanel extends JPanel {

    public FileTagPanel(FileTag fileTag, OnDeleteClickListener deleteClickListener) {
        JLabel label = new JLabel(fileTag.getTag() + ":\t\t" + fileTag.getPath());

        JButton closeButton = new JButton("Delete");
        closeButton.setVisible(true);
        closeButton.setEnabled(true);
        closeButton.setPreferredSize(new Dimension(100, 35));
        closeButton.addActionListener(e -> {
            // Remove the corresponding item from the parent JList
            deleteClickListener.onDeleteClick(fileTag, this);
        });

        JPanel colorPanel = new JPanel();
        colorPanel.setPreferredSize(new Dimension(20, 20));
        colorPanel.setBorder(BorderFactory.createCompoundBorder(colorPanel.getBorder(), BORDER));
        if (fileTag.getColorSet() == 1) {
            colorPanel.setBackground(new Color(fileTag.getColor()));
        }

        JPanel endPanel = new JPanel(new FlowLayout());
        endPanel.add(colorPanel);
        endPanel.add(closeButton);

        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        borderLayout.setVgap(10);
        setLayout(borderLayout);
        add(label, BorderLayout.CENTER);
        add(endPanel, BorderLayout.LINE_END);
    }
}