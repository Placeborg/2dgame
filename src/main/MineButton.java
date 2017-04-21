package main;

import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MineButton extends JButton{
	
	static final long serialVersionUID = 1L;
	
	String name;
	final int x;
	final int y;
	MineField mines;
	
	MineButton(String s, int x, int y, MineField mines){
		super(s);
		name = s;
		this.x = x;
		this.y = y;
		
		//set mouse event
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent me)	{
				if(mines.field[x][y]=='x'){
					try{
						Image img = ImageIO.read(getClass().getResource("x.png"));
						ImageIcon imgIcon = new ImageIcon(img);
						setIcon(imgIcon);
						setDisabledIcon(imgIcon);
						setEnabled(false);
					} catch (Exception e){
					System.out.println(e);
					}
				} else if(mines.field[x][y] == 0){
					Game.disableCells(mines.getEmptyField(x,y), mines);
				} else if(mines.field[x][y] != 0){
					setCorrectDisabledIcon(x,y,mines);
				}
			}
		});
	}
	
	String text() {
		return this.name;
	}

	void setMineSize(){
		this.setPreferredSize(new Dimension(15,15));
	}
	
	void disableCell(int x, int y, MineField mines){
		if(mines.field[x][y] == 0){
			setEnabled(false);
		} else if(mines.field[x][y] != 0){
			setCorrectDisabledIcon(x,y,mines);
		}
	}
	
	void setCorrectDisabledIcon(int x, int y, MineField mines){
		try{
			int mineNeighbors = (int)mines.field[x][y];
			Image img = ImageIO.read(getClass().getResource(mineNeighbors +".png"));
			ImageIcon imgIcon = new ImageIcon(img);
			setIcon(imgIcon);
			setDisabledIcon(imgIcon);
			setEnabled(false);
		} catch (Exception e){
		System.out.println(e);
		}
	}
}