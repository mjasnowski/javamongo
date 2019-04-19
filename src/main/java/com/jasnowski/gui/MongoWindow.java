package com.jasnowski.gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;

import com.jasnowski.utils.*;


public class MongoWindow extends JFrame implements TreeSelectionListener{

    private JTree t = null;
    //private JTextArea p = null;
    private JPanel documentsPanel = null;
    private JPanel treePanel = null;

    public MongoWindow(){


        // Layout main windows
        layoutPage();

    }

    private void layoutPage(){

        // Set intial size
        this.setPreferredSize(new Dimension(1000,750));
        this.setTitle("MongoDB Explorer");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);


        // Set Data Container
        //p = new JTextArea();
        //p.setLineWrap(true);
        //p.setBorder(new EmptyBorder(10,10,10,10));

        documentsPanel = new JPanel();
        //documentsPanel.setPreferredSize(new Dimension(1000,1000));
        documentsPanel.setBackground(Color.white);
        documentsPanel.setLayout(new BorderLayout());
        documentsPanel.setBorder(new EmptyBorder(10,10,10,10));

        t = this.buildTree();
        t.addTreeSelectionListener(this);
        t.setMinimumSize(new Dimension(200,50));
        t.setBorder(new EmptyBorder(10,10,10,10));

        treePanel = new JPanel();
        treePanel.setBackground(Color.white);
        t.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        treePanel.setBorder(new EmptyBorder(10,10,10,10));
        treePanel.setLayout(new BorderLayout());
        treePanel.add(t);


        JSplitPane splitPane =
                new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                        treePanel,
                        new JScrollPane(documentsPanel));
        this.add(splitPane,BorderLayout.CENTER);

        // Populate Window with Mongo DB Welcome Message
        String welcomeStr = MongoUtils.listDBInfo();
        documentsPanel.add(new DocumentPanel("MongoDB",welcomeStr, ""));
        documentsPanel.layout();

    }

    /**
     * Builds a tree of database names and collections
     * @return
     */
    private JTree buildTree(){
        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode("Mongo DB");
        // Add Database Names
        ArrayList<String> databaseNames = MongoUtils.getDatabaseNames();
        for (String databaseName : databaseNames){
             // Node which holds the database name
             DefaultMutableTreeNode databaseNode =
                     new DefaultMutableTreeNode(databaseName);
             ArrayList<String> collections =
                     MongoUtils.getCollections(databaseName);
             for (String collectionName : collections){
                 // Node which holds the collections of a database
                 DefaultMutableTreeNode collectionNode =
                         new DefaultMutableTreeNode(collectionName);
                 databaseNode.add(collectionNode);
             }
             // Add Database Node To Tree
             top.add(databaseNode);
        }
        JTree t = new JTree(top);
        t.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        return t;
    }

    /**
     * Handles selections of database collection names and lists the documents
     * in the collection to the text area
     * @param e
     */
    public void valueChanged(TreeSelectionEvent e) {

        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode)t.getLastSelectedPathComponent();
        if (node != null) {
            DefaultMutableTreeNode parentNode =
                    (DefaultMutableTreeNode) node.getParent();
            if (parentNode != null && !parentNode.isRoot()) {
                // Clear Text Area
                //p.setText("");
                // Add JSON Output to the TextArea
                //p.append(MongoUtils.getDocuments(parentNode.toString().trim(), node.toString().trim()));
                // Remove Children from Panel (Or maybe layer add to a tabbed view and refresh if necessary)
                documentsPanel.removeAll();
                // Add new panel with contents
//                String documentStr =
//                        MongoUtils.getDocuments(parentNode.toString().trim(), node.toString().trim());
//                documentsPanel.add(new DocumentPanel(node.toString(), documentStr));
//                MongoCollection<Document> documents =
//                        MongoUtils.getDocumentObjects(parentNode.toString().trim(), node.toString().trim());
                documentsPanel.add(new DocumentPanel(parentNode.toString(), node.toString()));
                documentsPanel.layout();
            }
        }
    }
}
