import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;



public class TableModel {
	private static String[][] CSV;
	private static String[] columnName;
	
	@SuppressWarnings("resource")
	public static JTable makeTable(String[][] rows) throws Exception{


	String[][] CSV = rows;		
		
        columnName = CSV[0];
        
        String[][] temp = new String[CSV.length - 1][];
        for (int i = 1; i < CSV.length; i++){
        	temp[i-1]=CSV[i];
        }
        
        CSV = temp;
        
        JTable table = new JTable(CSV, columnName);
        
        return table;
    
    }
	

}
