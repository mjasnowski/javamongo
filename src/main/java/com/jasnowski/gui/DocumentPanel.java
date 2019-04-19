package com.jasnowski.gui;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.*;
import org.bson.json.*;

import javax.swing.*;
import java.awt.*;

import com.jasnowski.utils.*;

import net.miginfocom.swing.*;


public class DocumentPanel extends JPanel {

    private JTextArea documentText = new JTextArea(100,100);

    private JPanel documentPanel = new JPanel();

    /**
     * Constructs a Documents Panel displaying the name of the collection
     * and all of its documents in object format
     * @param collectionName
     * @param documents
     */
    public DocumentPanel(String databaseName,
                         String collectionName) {

        //setLayout(new MigLayout());


        StringBuffer documentBuffer = new StringBuffer();
        JsonWriterSettings settings = new JsonWriterSettings(true);

        documentPanel.setLayout(new MigLayout());
        documentPanel.setBackground(Color.white);

        MongoCollection<Document> documents =
                MongoUtils.getDocumentObjects(databaseName, collectionName);

        for (Document doc : documents.find()) {

             documentBuffer.append(doc.toJson(settings) + "\r\n");
             //documentPanel.add(new DocumentContentPanel(doc.toJson(settings)), "grow, wrap");
        }

        // By default layout page as if before
        this.layout(collectionName, documentBuffer.toString());

        //this.add(documentPanel, "growx, grow y");
        //layout();
        //documentPanel.layout();

    }

    /**
     * Constructs a Documents Panel displaying the name of the collection
     * and all of its documents in String format
     * @param collectionName
     * @param jsonStr
     * @deprecated
     */
    @Deprecated
    public DocumentPanel(String collectionName,
                         String jsonStr,
                         String userName){

        this.layout(collectionName, jsonStr);


    }

    /**
     * Layout the document panel with a title panel and TextArea with the documents as string
     * @param collectionName
     * @param documenStr
     */
    private void layout(String collectionName,
                        String jsonStr){

        documentPanel.setLayout(new BorderLayout());
        documentPanel.setBackground(Color.white);

        /**
         * Main Header Label Area
         */
        documentPanel.add(new DocumentPanelTitle(collectionName), BorderLayout.NORTH);

        /**
         * Main Document Text Area
         */

        this.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));

        this.setBackground(Color.white);

        this.documentText.setLineWrap(true);

        this.documentText.setText(jsonStr);

        documentPanel.add(documentText, BorderLayout.CENTER);

        this.documentText.setCaretPosition(0);

        this.add(documentPanel);

        layout();

        documentPanel.layout();

    }

}