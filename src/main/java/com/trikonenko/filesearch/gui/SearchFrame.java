package com.trikonenko.filesearch.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.trikonenko.filesearch.persistence.domain.FileEntity;
import com.trikonenko.filesearch.service.FileSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchFrame extends JFrame {

    @Autowired
    private FileSearchService fileSearchService;

    private JTextField searchTextField;
    private JTable searchResultsTable;
    private DefaultTableModel searchTableModel;

    public SearchFrame() {
        initUI();
    }

    private void initUI() {
        JPanel indexPanel = createIndexPanel();
        JPanel searchPanel = getSearchPanel();
        createResultsTable();

        JPanel content = new JPanel(new BorderLayout(8,8));
        content.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        content.add(new JScrollPane(searchResultsTable));
        content.add(indexPanel, BorderLayout.PAGE_START);
        content.add(searchPanel, BorderLayout.PAGE_END);

        setLayout(new BorderLayout());
        add(content);
        setTitle("File search app");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    private void createResultsTable() {
        String[][] data = { };
        String[] columns = { "ID", "NAME", "PATH" };
        searchTableModel = new DefaultTableModel(data, columns);
        searchResultsTable = new JTable(searchTableModel);
    }

    private JPanel getSearchPanel() {
        JButton searchButton = new JButton("Search");
        searchButton.setAction(searchFileAction);
        searchTextField = new JTextField(30);
        searchTextField.setBounds(130, 20, 200, 20);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchButton);
        searchPanel.add(searchTextField);
        return searchPanel;
    }

    private JPanel createIndexPanel() {
        JFileChooser directoryToBeIndexedChooser = new JFileChooser();
        directoryToBeIndexedChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        directoryToBeIndexedChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        JButton indexDirButton = new JButton("Index documents");
        indexDirButton.setAction(new AbstractAction("Index documents") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = directoryToBeIndexedChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileSearchService.indexDirectory(directoryToBeIndexedChooser.getSelectedFile());
                }
            }
        });
        JPanel indexPanel = new JPanel();
        indexPanel.add(indexDirButton, BorderLayout.LINE_START);
        return indexPanel;
    }

    Action searchFileAction = new AbstractAction("Search file") {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchTableModel.setRowCount(0); // clear the model
            try {
                String keyword = searchTextField.getText();
                List<FileEntity> files = fileSearchService.searchFile(keyword);
                if (files.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No results...");
                } else {
                    System.out.println(files);
                    for (FileEntity file : files) {
                        searchTableModel.addRow(new Object[] {
                                file.getId(),
                                file.getName(),
                                file.getPath()
                        });
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    };
}
