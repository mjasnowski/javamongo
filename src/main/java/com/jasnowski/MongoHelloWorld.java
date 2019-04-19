package com.jasnowski;

import javax.swing.*;
import javax.swing.UIManager.*;

import com.jasnowski.gui.*;
import com.jasnowski.utils.*;


public class MongoHelloWorld {

       public static void main(String args[]){

           //setLookAndFeel();

           SwingUtilities.invokeLater(new Runnable() {

               public void run() {

                   MongoWindow window = new MongoWindow();

               }

           });

       }

       private static void setLookAndFeel(){
           try {
               for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                   if ("Nimbus".equals(info.getName())){
                       UIManager.setLookAndFeel(info.getClassName());
                       break;
                   }
               }
           }catch(Exception e){
               // Do nothing as we'll just default
           }
       }


}
