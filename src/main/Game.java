package main;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame{
	
	static final long serialVersionUID = 1L;
	
	JPanel fieldPanel;
	JPanel controlPanel;
	static int width = 20;
	static int height= 20;
	
	MineField mines;
	static ArrayList<MineButton> buttonList;
		
	public Game(){
		super("Minesweeper");
				
		MineField mines = new MineField(width,height);
		
		fieldPanel = new JPanel();
		fieldPanel.setSize(200,200);		
		fieldPanel.setBackground(new Color(255,255,255));
		fieldPanel.setOpaque(true);
		
		buttonList = new ArrayList<>();
		for(int i=0; i < width; i++){
			for(int j=0; j < height; j++){
				buttonList.add(new MineButton("", i, j, mines));
				buttonList.get(buttonList.size()-1).setMineSize();
				fieldPanel.add(buttonList.get(buttonList.size()-1));
			}
		}
		
		GridLayout fieldGL = new GridLayout(20,20);
		fieldPanel.setLayout(fieldGL);
		
		GridLayout superGL = new GridLayout(1,0);
		setLayout(superGL);

		add(fieldPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public static void disableCells(ArrayList<Integer[]> cellList, MineField mines){
		for(Integer[] coords : cellList){
			int buttonIndex = coords[0]*width+coords[1];
			//buttonList.get(buttonIndex).setEnabled(false);
			buttonList.get(buttonIndex).disableCell(coords[0], coords[1], mines);
		}
	}
}