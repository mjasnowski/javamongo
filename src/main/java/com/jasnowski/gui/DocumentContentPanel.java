package com.jasnowski.gui;


import javax.swing.*;
import java.awt.*;



public class DocumentContentPanel extends JPanel {

    private JTextArea textArea = new JTextArea();


    public DocumentContentPanel(String jsonStr){

        /**
         * General panel setup
         */

        this.setLayout(new BorderLayout());

        this.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));

        this.setBackground(Color.white);

        /**
         * Text Area with document text
         */

        this.add(textArea, BorderLayout.CENTER);

        this.textArea.setLineWrap(true);

        this.textArea.setText(jsonStr);

        this.textArea.setCaretPosition(0);

        layout();


    }

}