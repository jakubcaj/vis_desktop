package com.vis;

import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginForm {
    private JPanel LoginPanel;
    private JTextField usernameTxt;
    private JPasswordField passwordTxt;
    private JButton logInButton;
    private static JFrame frame;

    public LoginForm() {
        logInButton.addActionListener(e -> {

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", usernameTxt.getText());
                jsonObject.put("password", String.valueOf(passwordTxt.getPassword()));

                Content content = Request.Post("http://localhost:8080/customlogin")
                        .bodyString(jsonObject.toString(), ContentType.APPLICATION_JSON)
                        .execute().returnContent();
                System.out.print("");

                JSONObject result = new JSONObject(content.toString());

                if(result.getBoolean("success")) {
                    frame.setContentPane(new ImportExportForm().TestPanel);
                    frame.pack();
                    frame.setVisible(true);
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
    }

    public static void main(String[] args) {
        frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().LoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
