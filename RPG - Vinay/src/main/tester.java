package main;

// Imports
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class tester extends JFrame {
	// Instance attributes used in this example
	private JPanel topPanel;
	private JList listbox;

	// Constructor of main frame
	public tester() {
		// Set the frame characteristics
		setTitle("Simple ListBox Application");
		setSize(300, 100);
		setBackground(Color.gray);
		Character p = new Character("Kirito");

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);

		/*// Create some items to add to the list
		String listData[] = { "Item 1", "Item 2", "Item 3", "Item 4" };

		// Create a new listbox control
		listbox = new JList(listData);
		topPanel.add(listbox, BorderLayout.CENTER);*/
		
		DefaultListModel dlm = new DefaultListModel();
		dlm.addElement("Item1");
		dlm.addElement("Item2");
		listbox = new JList(dlm);
		topPanel.add(listbox, BorderLayout.CENTER);

	}

	// Main entry point for this example
	public static void main(String args[]) {
		// Create an instance of the test application
		tester mainFrame = new tester();
		mainFrame.setVisible(true);
	}
}