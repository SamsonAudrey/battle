package fr.battleship;
import samson.audrey.*;

import java.util.Scanner;


public class TestIA {

	public static void main(String[] args) {
		
	//length of the grid
	int str=10; //default
	
	//only IA
	boolean human=false;
	int X1=0;
	int X2=0;
	int X3=0;
	int Y1=0;
	int Y2=0;
	int Y3=0;
	
	//creation of the two players 
	Player player1;
	Player player2;
	int IAplay_1=1;
	player1=new IAeasy(1,str);
	player2=new IAmedium(2,str);
	int IAplay_2=2;
	
	//creation of the game
	Game battleGame=new Game(player1,player2);
	int beginner=1;
	
	
	//creation of the ships + positions of them
	for(int count=0;count<2;count++){
		//player
		battleGame.getCurrentPlayer().positionShip(battleGame);
		battleGame.changeCurrentPlayer();
		}
	

	boolean stop=false;
	int winplayer1=0;
	int winplayer2=0;
	int game=0;
	
	while(!stop){
	//LET'S GO
	
	boolean dead=false;
	//*******************************************
	Grid gridPlayer=battleGame.getCurrentPlayer().getGrid();
	Fleet fleetPlayerAdv=battleGame.getOpponentPlayer().getFleet();
	Coord[][] boxesPlayer=gridPlayer.getBoxes();
	Coord[][] boxesPlayerAdv=battleGame.getOpponentPlayer().getGrid().getBoxes();
	Ship[] sh=fleetPlayerAdv.getShips();
	String coordShoot;
	
	int tour=0;
	while(tour <1000 && !(dead)){
		gridPlayer=battleGame.getCurrentPlayer().getGrid();
		fleetPlayerAdv=battleGame.getOpponentPlayer().getFleet();
		boxesPlayer=gridPlayer.getBoxes();
		boxesPlayerAdv=battleGame.getOpponentPlayer().getGrid().getBoxes();
		
		//SHOOT
		coordShoot=battleGame.getCurrentPlayer().shoot(battleGame);
		battleGame.toShoot(human,coordShoot); //result of the shoot
		//dead?
		if(battleGame.getOpponentPlayer().getFleet().getNbAlive()==0){
		dead=true;
		}
		
		battleGame.changeCurrentPlayer();
		tour ++;
		}

	if(battleGame.getOpponentPlayer().getNumber()==1){
		winplayer1++;
	}
	else{
		winplayer2++;
	}
	game++;
	
	if(game==100){
		if(IAplay_1==1 && IAplay_2==3){
			X2=winplayer1;
			Y2=winplayer2;
			player1=new IAmedium(1,str);
			player2=new IAhard(2,str);
			IAplay_1=2;
			game=0;
			winplayer1=0;
			winplayer2=0;
			
		}
		else if(IAplay_1==1 && IAplay_2==2){
				X1=winplayer1;
				Y1=winplayer2;
				player1=new IAeasy(1,str);
				player2=new IAhard(2,str);
				IAplay_2=3;
				game=0;
				winplayer1=0;
				winplayer2=0;
				}
		else {
			X3=winplayer1;
			Y3=winplayer2;
			winplayer1=0;
			winplayer2=0;
			stop=true;
		}
		}
	else{
		if(IAplay_1==1 && IAplay_2==3){
			player1=new IAeasy(1,str);
			player2=new IAhard(2,str);
		}
		else if(IAplay_1==1 && IAplay_2==2){
				player1=new IAeasy(1,str);
				player2=new IAmedium(2,str);
				}
		else {
			player1=new IAmedium(1,str);
			player2=new IAhard(2,str);
		}
	}
	
	if(!stop){
		//creation of the game
	battleGame=new Game(player1,player2);
	if(beginner==1){
		battleGame.changeCurrentPlayer();
		beginner=2;
	}
	else{
		beginner=1;
	}
	
	//creation of the ships + positions of them
	for(int count=0;count<2;count++){
		//player
		battleGame.getCurrentPlayer().positionShip(battleGame);
		battleGame.changeCurrentPlayer();
	}
	}
	}
	//fichier csv
	String message="AI Name; score; AI Name2; score2 \nAI Level Beginner; "+X1+"; Level Medium; "+Y1+"\nAI Level Beginner; "+X2+";Level Hard; "+Y2+"\nAI Medium; "+X3+" ;Level Hard; "+Y3;
	FichierTxt.write(message);
	}
}

	
