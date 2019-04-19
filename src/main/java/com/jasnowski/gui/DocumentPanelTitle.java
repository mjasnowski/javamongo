package com.jasnowski.gui;


import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;


public class DocumentPanelTitle extends JPanel {

    private String title = "";
    private JLabel titleLabel = null;

    public DocumentPanelTitle(String titleStr){

        this.title = titleStr;

        this.setLayout(new BorderLayout());

        titleLabel = new JLabel(this.title, JLabel.LEFT);

        this.add(titleLabel, BorderLayout.CENTER);

        this.setBorder(new EmptyBorder(10,10,10,10));

        layout();

    }


}