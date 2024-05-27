package ui;

import com.github.godwinjk.filelabel.data.FileTag;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class FileLabelSettingsUI implements OnDeleteClickListener {
    public JPanel panel;
    private JTextField pathTextField;
    private JButton btnAdd;
    private JTextField tagTextField;
    private JScrollPane pathScrollPane;
    private JList pathList;
    private JLabel erroText;
    private JButton chooseColorButton;
    private JPanel colorPane;

    List<FileTag> fileTagList = new ArrayList<>();
    DefaultListModel<FileTag> fileTagModel;
    int colorSet = 0;

    public FileLabelSettingsUI() {
        btnAdd.addActionListener(e -> {
            if (validateInputs()) {
                //add to list
                addToList();
                populateList();
            } else {
                //show error
                erroText.setVisible(true);
            }
            colorSet = 0;
        });
        chooseColorButton.addActionListener(e -> {
            Color color = colorPane.getBackground();

            Color selectedColor = JColorChooser.showDialog(panel, "Choose Color", color, true);
            colorPane.setBackground(selectedColor);
            colorSet = 1;
        });
        colorPane.setPreferredSize(new Dimension(20, 20));

        tagTextField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                erroText.setVisible(false);
            }
        });
        pathTextField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                erroText.setVisible(false);
            }
        });
        Font font = erroText.getFont();
        erroText.setFont(font.deriveFont(20f)); // Change the font size to 20
        erroText.setForeground(JBColor.RED);
        erroText.setVisible(false);
        erroText.setText("Please enter the tag and path");

        //List configuration
        pathList.setCellRenderer(new FileTagListCellRenderer(this));
        pathList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = pathList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    FileTagPanel panel = (FileTagPanel) pathList.getCellRenderer()
                            .getListCellRendererComponent(pathList, fileTagModel.get(index), index, false, false);
                    Rectangle cellBounds = pathList.getCellBounds(index, index);
                    if (cellBounds != null && cellBounds.contains(e.getPoint())) {
                        Point relativePoint = new Point(e.getX() - cellBounds.x, e.getY() - cellBounds.y);
                        if (relativePoint.getX() > (cellBounds.width - 100)) {
                            // Handle delete button click
                            fileTagList.remove(index);
                            fileTagModel.remove(index);
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private boolean validateInputs() {
        String pathText = pathTextField.getText();
        String tagText = tagTextField.getText();

        return (!pathText.isBlank() && !tagText.isBlank());
    }

    public void setFileTagList(List<FileTag> fileTagList) {
        this.fileTagList = fileTagList;

        populateList();
    }

    public List<FileTag> getFileTagList() {
        return this.fileTagList;
    }

    private void addToList() {
        String pathText = pathTextField.getText();
        String tagText = tagTextField.getText();

        Color color = colorPane.getBackground();
        FileTag tag = new FileTag(tagText, pathText, color.getRGB(), 1);

        fileTagList.add(tag);
        populateList();
        //Clean Up
        pathTextField.setText("");
        tagTextField.setText("");
    }

    private void populateList() {
        this.fileTagModel = new DefaultListModel<>();
        for (FileTag tag : fileTagList) {
            fileTagModel.addElement(tag);
        }

        pathList.setModel(fileTagModel);
    }

    @Override
    public void onDeleteClick(FileTag fileTag, FileTagPanel panel) {
        fileTagList.remove(fileTag);
        populateList();
    }
}
