package main;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame{
	
	static final long serialVersionUID = 1L;
	
	JPanel fieldPanel;
	JPanel controlPanel;
	static int width = 40;
	static int height= 40;
	
	MineField mines;
	static ArrayList<MineButton> buttonList;
		
	public Game(){
		super("Minesweeper");
				
		MineField mines = new MineField(width,height);
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fieldPanel = new JPanel();
//		fieldPanel.setSize(400,400);		
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
		
		GridLayout fieldGL = new GridLayout(width,height);
		fieldPanel.setLayout(fieldGL);
		
		GridLayout superGL = new GridLayout(1,0);
		setLayout(superGL);

		add(fieldPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width*20, height*20);
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