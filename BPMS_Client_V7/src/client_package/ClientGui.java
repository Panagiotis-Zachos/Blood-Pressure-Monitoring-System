package client_package;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class ClientGui {

	private JFrame frame;
	private Client client;
	private JTextPane textArea;

	public ClientGui(Client client) {
		this.client = client;
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame("Blood Pressure Monitoring");
		frame.setResizable(false);
		frame.setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 597, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton PrintButton = new JButton("Print Book");
		PrintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.setText(client.printBook());
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		PrintButton.setBounds(10, 11, 144, 23);
		frame.getContentPane().add(PrintButton);
		
		JButton StoreButton = new JButton("Store book locally");
		StoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.storeBookLocally();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		StoreButton.setBounds(10, 45, 144, 23);
		frame.getContentPane().add(StoreButton);
		
		JButton button_2 = new JButton("Send file to server");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.sendFileToServer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(10, 79, 144, 23);
		frame.getContentPane().add(button_2);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(null);
			}
		});
		btnClear.setBounds(485, 412, 89, 23);
		frame.getContentPane().add(btnClear);
		
		textArea = new JTextPane();
		textArea.setEditable(false);
		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(164, 11, 410, 402);
		frame.getContentPane().add(scrollBar);
		frame.setVisible(true);
		
	}
}
