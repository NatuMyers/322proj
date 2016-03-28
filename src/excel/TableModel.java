package excel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;



public class TableModel extends JPanel {
	private static String[][] CSV;
	private static String[] columnName;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
		/*Scanner keyboard = new Scanner(System.in);
		System.out.println("Please Enter csv file name: ");
		String file = keyboard.next();*/
		
		CSV = ParseCsvByLine.read("../../movie_data.csv");
		
		//Create and set up the window.
        JFrame frame = new JFrame("Table Model");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
      //Create and set up the content pane.
        Container content = frame.getContentPane();
        columnName = CSV[0];
        
        JTable table = new JTable(CSV, columnName);
        JScrollPane scrollPane = new JScrollPane(table);
        content.add(scrollPane, BorderLayout.CENTER);
        
        
        //TableModel newContentPane = new TableModel();
        //newContentPane.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    
    }
	

}

