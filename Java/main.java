package com.ahsan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	JFrame obj;
	JPanel TitleNamePanel , StartButtonPanel;
	Container con;
	JLabel TitleNameLabel;
	Font TitleFont = new Font("Algerian", Font.PLAIN,50);
	Font ButtonFont = new Font("Algerian", Font.PLAIN,25);


	public static void main(String[] args) {
		 Main tostart = new Main();
		 tostart.Mainmethod();
	}

	public void Mainmethod() {
		//Frame work
		JFrame obj = new JFrame();
		obj.setSize( 700, 600);
		obj.getContentPane().setBackground(Color.BLACK);
		obj.setLayout(null);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con = obj.getContentPane();

//title work
		TitleNamePanel = new JPanel();
		TitleNamePanel.setBounds(100, 100, 500, 100);
		TitleNamePanel.setBackground(Color.BLACK);
		TitleNameLabel = new JLabel("BRICK BREAKERS.");
		TitleNameLabel.setForeground(Color.WHITE);
		TitleNamePanel.add(TitleNameLabel);
		TitleNameLabel.setFont(TitleFont);
//Button work
		JButton StartButtonPanel = new JButton("START GAME");
		StartButtonPanel.setBounds(240,400,200,50);
		StartButtonPanel.setBackground(Color.ORANGE);
		StartButtonPanel.setFont(ButtonFont);
		StartButtonPanel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				obj.setDefaultCloseOperation(1);
				GamePlay gamePlay = new GamePlay();
				JFrame obj2 = new JFrame();
				obj2.setBounds(10,10,710,600);
				obj2.setTitle("BRICK BREAKERS");
				obj2.setResizable(false);
				obj2.setVisible(true);
				obj2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				obj2.add(gamePlay);
			}
		});
		con.add(StartButtonPanel);
		con.add(TitleNamePanel);
		obj.setVisible(true);

	}