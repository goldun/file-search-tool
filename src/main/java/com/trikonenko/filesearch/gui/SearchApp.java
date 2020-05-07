package com.trikonenko.filesearch.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.trikonenko.filesearch.persistence.domain.FileEntity;
import com.trikonenko.filesearch.service.FileSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchApp extends JFrame {

    @Autowired
    private FileSearchService fileSearchService;

    private JTextField searchTextField;
    private JTable searchResultsTable;
    private DefaultTableModel searchTableModel;


    public SearchApp() {
        initUI();
    }

    private void initUI() {

        JButton searchButton = new JButton("Search");
        searchButton.setAction(searchFileAction);
        searchTextField = new JTextField(30);
        searchTextField.setBounds(130, 20, 200, 20);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchButton);
        searchPanel.add(searchTextField);

        String[][] data ={};
        String[] columns ={"ID","NAME","PATH"};
        searchTableModel = new DefaultTableModel(data, columns);
        searchResultsTable = new JTable(searchTableModel);

        JPanel content = new JPanel(new BorderLayout(8,8));
        content.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        content.add(new JScrollPane(searchResultsTable));
        content.add(searchPanel, BorderLayout.PAGE_END);

        createLayout(content);

        setTitle("File search app");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }


    private void createLayout(JComponent... arg) {
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addComponent(arg[0]));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(arg[0]));
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
