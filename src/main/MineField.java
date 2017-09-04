package main;

import java.util.*;


public class MineField {

	char[][] field;
	Random ran = new Random();

	public MineField(int x, int y){
		field = new char[x][y];
		int totalMines = ((x)*(y))/10;
		Game.totalMines = totalMines;

		int i = 0;
		while(i<totalMines){
			int[] mineCoord = new int[2];
			mineCoord[0] = ran.nextInt(x);
			mineCoord[1] = ran.nextInt(y);
			System.out.println(mineCoord[0] + " " + mineCoord[1]);

			if(field[mineCoord[0]][mineCoord[1]]!='x'){
				field[mineCoord[0]][mineCoord[1]] = 'x';
				i++;
				for(int xMod=-1; xMod<2; xMod++){
					for(int yMod=-1; yMod<2; yMod++){
						try{
							char tempMine = field[mineCoord[0]+xMod][mineCoord[1]+yMod];
							tempMine = tempMine != 'x' ? tempMine +=1 : tempMine;
							field[mineCoord[0]+xMod][mineCoord[1]+yMod] = tempMine;
						} catch(ArrayIndexOutOfBoundsException e){
						}
					}
				}
			}
		}

	}

	void printMineCoords(){
	}

	ArrayList<Integer[]> getEmptyField(int x, int y){
		//Creating fields, emptyField for return, todoFields for iterating cells
		ArrayList<Integer[]> emptyField = new ArrayList<>();
		ArrayList<Integer[]> todoFieldOne = new ArrayList<>();
		ArrayList<Integer[]> todoFieldTwo = new ArrayList<>();

		//Initializing on the clicked button coordinates and adding it to an iterator
		Integer[] coord = {x,y};
		todoFieldOne.add(coord);
		System.out.println("added coord to list one on position : " + todoFieldOne.size());

		while(todoFieldOne.isEmpty()==false){
			System.out.println("inside while");
			//TODO make into a method

			//Iterating todoFieldOne
			for(Integer[] tempCoord : todoFieldOne){
				if(field[tempCoord[0]][tempCoord[1]] != 'c'){
					System.out.print("inside iteration list one\n");
					emptyField.add(tempCoord);
					int tempX = tempCoord[0];
					int tempY = tempCoord[1];
					if(field[tempX][tempY] == 0){
						field[tempX][tempY] = 'c';
						System.out.println("x:" + tempCoord[0] + " y:" + tempCoord[1]);
						for(int xMod=-1; xMod<2; xMod++){
							for(int yMod=-1; yMod<2; yMod++){
								Integer[] newCoord = new Integer[2];
								newCoord[0] = tempX+xMod;
								newCoord[1] = tempY+yMod;
								try{
									if(field[newCoord[0]][newCoord[1]] != 'c'){
										todoFieldTwo.add(newCoord);
										System.out.println("added coord to list two on position : " + todoFieldTwo.size());
										System.out.println("x:" + newCoord[0] + " y:" + newCoord[1]);
									}
								} catch(Exception e){
									System.out.println(e);
								}
							}
						}
					}
				}
			}
			todoFieldOne.clear();
			System.out.println(todoFieldOne.isEmpty());

			//Iterating todoFieldTwo
			for(Integer[] tempCoord : todoFieldTwo){
				//if current cell value is not 'c'
				if(field[tempCoord[0]][tempCoord[1]] != 'c'){
					System.out.print("inside iteration list two\n");
					//adding current coordinate to return list
					emptyField.add(tempCoord);
					//setting up tempX/Y for readability
					int tempX = tempCoord[0];
					int tempY = tempCoord[1];
					if(field[tempX][tempY] == 0){
						field[tempX][tempY] = 'c';
						//checking all neighborcells
						for(int xMod=-1; xMod<2; xMod++){
							for(int yMod=-1; yMod<2; yMod++){
								Integer[] newCoord = new Integer[2];
								newCoord[0] = tempX+xMod;
								newCoord[1] = tempY+yMod;
								try{
									if(field[newCoord[0]][newCoord[1]] != 'c'){
										todoFieldOne.add(newCoord);
										System.out.println("added coord to list one on position : " + todoFieldOne.size());
										System.out.println("x:" + newCoord[0] + " y:" + newCoord[1]);
									}
								} catch(Exception e){
									System.out.println(e);
								}
							}
						}
					}
				}
			}
			todoFieldTwo.clear();
			System.out.println(todoFieldTwo.isEmpty());
		}

		return emptyField;
	}

	void printField(){
		System.out.println(field.length);
		for(int i = 0; i<field.length; i++){
			for(int j = 0; j<field.length; j++){
				System.out.print(field[i][j]);
			}
			System.out.println("\n");
		}
	}
}
