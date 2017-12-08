package com.vis;

import org.apache.http.client.fluent.Content;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Iterator;

public class ImportExportForm {
    public JPanel TestPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JList<Crime> list1;
    private JButton exportButton;
    private JButton importButton;
    private JFileChooser fileChooser;
    private SendRequest sendRequest = SendRequest.getInstance();


    public ImportExportForm() {
        exportButton.addActionListener(e -> {

            JSONObject selectedCrime = list1.getSelectedValue().getCrime();
            Content content = sendRequest.sendGetRequest("/profile/export/" + selectedCrime.getLong("id"));

            fileChooser = new JFileChooser();

            int option = fileChooser.showSaveDialog(null);
                if(option == JFileChooser.APPROVE_OPTION) {
                try(FileWriter fileWriter = new FileWriter(fileChooser.getSelectedFile() + ".xml")) {
                    fileWriter.write(content.asString());
                }catch (Exception er) {
                    er.printStackTrace();
                }
            }

            System.out.print("");
        });


        searchButton.addActionListener((ActionEvent e) -> {


            JSONObject searchText = new JSONObject();
            searchText.put("search", searchField.getText());

            JSONObject result = sendRequest.sendPostRequest("/profile/crime/search", searchField.getText());

            JSONArray resultArray = result.getJSONArray("object");

            Iterator iterator = resultArray.iterator();

            DefaultListModel<Crime> defaultListModel = new DefaultListModel<>();
            if(iterator.hasNext()) {
                Crime crime = new Crime((JSONObject) iterator.next());
                defaultListModel.addElement(crime);
            }
            list1.setModel(defaultListModel);
        });


        importButton.addActionListener(e -> {
            fileChooser = new JFileChooser();

            int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION) {
                try{
                    String contents = new String(Files.readAllBytes(fileChooser.getSelectedFile().toPath()));
                    sendRequest.sendPostRequest("/profile/import",contents);
                }catch (Exception er) {
                    er.printStackTrace();
                }
            }
        });
    }
}
