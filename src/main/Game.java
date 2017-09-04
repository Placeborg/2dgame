package main;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Game extends JFrame{
	
	static final long serialVersionUID = 1L;
	
	static JPanel mainPanel;
	static JPanel fieldPanel;
	static JPanel controlPanel;
	static int width = 20;
	static int height= 20;
	
	static int flags;
	static int minesFound;
	static int totalMines;
	
	static JPanel winPanel;
	static JPanel losePanel;
	
	static JTextArea flagIndicator;
	
	MineField mines;
	static ArrayList<MineButton> buttonList;
		
	public Game(){
		super("Minesweeper");
		
		/*
		//Experimenting with menubars in rootPane
		JMenuBar menuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("main");
		JMenuItem restart = new JMenuItem("restart");
		menuBar.add(mainMenu);
		mainMenu.add(restart);
		getRootPane().setJMenuBar(menuBar);
		*/
		
		//Setting look and feel to metal
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
		
		//Setting up fieldPanel (contains the mine cells in a grid)
		createField();

		//Setting up controlPanel (contains the restart button, amount of bombs found, amount of seconds played)	
		createControlPanel();
		
		//Creating mainPanel and setting it up with layout
		mainPanel = new JPanel();	
		mainPanel.setBackground(new Color(255,255,255));
		mainPanel.setOpaque(true);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		//Adding panels to mainpanel
		mainPanel.add(fieldPanel);
		mainPanel.add(controlPanel);
		
		//Adding  to frame
		this.add(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(mainPanel.getPreferredSize());
		System.out.println("Width: " + fieldPanel.getWidth());
		System.out.println("Height: " + controlPanel.getHeight());
		setSize(width*20, (int)((height*20)+(controlPanel.getPreferredSize().getHeight())));
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
	
	public void createField(){
		//Creating Minefield
		MineField mines = new MineField(width,height);

		//Create MineCells in fieldPanel
		fieldPanel = new JPanel();
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

		GridLayout fieldGL = new GridLayout(width, height);
		fieldPanel.setLayout(fieldGL);
		
		fieldPanel.setSize(fieldPanel.getPreferredSize());
	}
	
	public void createControlPanel(){
		//Setting up controlPanel (contains the restart button, amount of bombs found, amount of seconds played)		
		controlPanel = new JPanel();	
		controlPanel.setBackground(new Color(255,255,255));
		controlPanel.setOpaque(true);
		
		//InstantWin Button
		JButton instantWin = new JButton("Win");
		controlPanel.add(instantWin);
		instantWin.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent me){
				Game.showWinScreen();
			}
		});
		
		//Setting up restart button
		JButton restartButton = new JButton("Restart");
		controlPanel.add(restartButton);
		restartButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent me){
				mainPanel.removeAll();
				resetFlags();
				minesFound = 0;
				createField();
				mainPanel.add(fieldPanel);
				mainPanel.add(controlPanel);
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		});
		
		//TODO: change to textbox (JEditorPane - ??)
		flagIndicator = new JTextArea(1, 3);
		flagIndicator.setEditable(false);
		flagIndicator.append("Mines found: " + flags);
		flagIndicator.setEditable(false);
		//JButton amountOfBombs = new JButton("Bombs found:");
		controlPanel.add(flagIndicator);
		

		//TODO: change to textbox
		JButton secondsPlayed = new JButton("Seconds played:");
		controlPanel.add(secondsPlayed);
		
		controlPanel.setSize(controlPanel.getPreferredSize());
	}
	
	public static void addFlag(){
		flags++;
	}
	
	public static void subtractFlag(){
		flags--;
	}
	
	public static void resetFlags(){
		flags = 0;
		flagIndicator.setText("Mines found: " + Game.flags);
	}
	
	public static void mineFound(){
		minesFound++;
		if(minesFound >= totalMines){
			//TODO Win statement
			System.out.println("You have won");	
			showWinScreen();
		}
	}
	
	public static void explodeMine(){
		//TODO Lose statement
		 System.out.print("Game over");
		 showLoseScreen();
	}
	
	public static void showWinScreen(){
		mainPanel.removeAll();
		
		winPanel = new JPanel();
		winPanel.setPreferredSize(fieldPanel.getPreferredSize());
		winPanel.setBackground(new Color(0, 255, 0));
		winPanel.add(new JTextArea("You have won!"));
		winPanel.setVisible(true);
					
		mainPanel.add(winPanel);
		mainPanel.add(controlPanel);
		mainPanel.revalidate();
		mainPanel.repaint();
		
	}
	
	public static void showLoseScreen(){
		mainPanel.removeAll();
		
		winPanel = new JPanel();
		winPanel.setPreferredSize(fieldPanel.getPreferredSize());
		winPanel.setBackground(new Color(255, 0, 0));
		winPanel.add(new JTextArea("GAME OVER!"));
		winPanel.setVisible(true);
					
		mainPanel.add(winPanel);
		mainPanel.add(controlPanel);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
}