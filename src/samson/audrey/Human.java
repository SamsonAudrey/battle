package samson.audrey;


import java.util.Scanner;

import samson.audrey.Coord;
import samson.audrey.Player;
import samson.audrey.Ship;

public class Human extends Player{


	public Human(int number, int length) {
		super(number, length);
		// TODO Auto-generated constructor stub
	}


	public  boolean positionShip2(samson.audrey.Game game,String name,int length){
		boolean res=false;
		Scanner sc1=new Scanner(System.in);
		Scanner sc2=new Scanner(System.in);
		System.out.println("Please, enter a position like 'A1','B2' ... ");
		System.out.println("The ship's length is : "+length);
		System.out.println("Start position of "+name+" : ?" );
		String startCoord= sc1.nextLine();
		System.out.println("End position of "+name+" : ?" );
		String endCoord= sc2.nextLine(); 
		if(this.getGrid().isOnGrid(startCoord,length)){
			if(this.getGrid().isOnGrid(endCoord,1)){
				if(startCoord.substring(0,1).equals(endCoord.substring(0,1))){
					String num="";
					//UTILITE DE NUMDOUBLE ? 
					if(startCoord.length()==3){
						String numdouble=startCoord.substring(1,2).concat(startCoord.substring(2,3));//=10
					}
					else{
					num=startCoord.substring(1,2);
					}
					
					char num2=Coord.getFirstChar(num);
					for(int curs=1;curs<length;curs++){
						num2++;
					}
					String numEnd;
					if(endCoord.length()==3){
						numEnd=endCoord.substring(1,2).concat(endCoord.substring(2,3));
						if(num2>'9'){
							res=true;
						}
					}
					else{
						numEnd=endCoord.substring(1,2);
					}
					if(Coord.getFirstChar(numEnd)==num2){
						res=true;
					}
				}
				else if(startCoord.substring(1,2).equals(endCoord.substring(1,2))){
					String lett=startCoord.substring(0,1);
					char lett2=Coord.getFirstChar(lett);
					for(int curs=1;curs<length;curs++){
						lett2++;
					}
					if(Coord.getFirstChar(endCoord.substring(0,1))==lett2){
						res=true;
					}
				}
			}
		}
		if(res){
			int ys=Coord.convertLetterToInt(Coord.getFirstChar(startCoord.substring(0,1)));
			int ye=Coord.convertLetterToInt(Coord.getFirstChar(endCoord.substring(0,1)));
			int xs;
			if(startCoord.length()==3){
				xs=Coord.convertIntCharToInt((startCoord.substring(1,2).concat(startCoord.substring(2,3))));
			}
			else{
				xs=Coord.convertIntCharToInt(startCoord.substring(1,2));
			}
			int xe;
			if(endCoord.length()==3){
				xe=Coord.convertIntCharToInt((endCoord.substring(1,2).concat(endCoord.substring(2,3))));
			}
			else{
				xe=Coord.convertIntCharToInt(endCoord.substring(1,2));
			}
			
			Coord[][] b=game.getCurrentPlayer().getGrid().getBoxes();
			if(!(b[xs-1][ys-1].isFree()) || !(b[xe-1][ye-1].isFree())){
				res=false;
				System.out.println("There is already a ship !");
			}
			else {
				b[xe-1][ye-1].setFree(false);
				b[xs-1][ys-1].setFree(false);
			}
			if(xe-1==xs-1){
				for(int bb=ys;bb<ye-1;bb++){
					if(!(b[xs-1][bb].isFree())){
						res=false;
						System.out.println("There is already a ship !");
					}
					else{
						b[xs-1][bb].setFree(false);
					}
				}
			}
			else{
				for(int bb=xs;bb<xe-1;bb++){
					if(!(b[bb][ys-1].isFree())){
						res=false;
						System.out.println("There is already a ship !");
					}
					else{
						b[bb][ys-1].setFree(false);
					}
				}
			}
			if(res){
				Ship s=new Ship(name,b[xs-1][ys-1],b[xe-1][ye-1]);
			game.getCurrentPlayer().getFleet().addShip(s);
			System.out.println("ship "+name+" added");
			System.out.println(" ");
			}	
		}
		return res;
	}





	@Override
	public void positionShip(samson.audrey.Game battleGame) {
		// TODO Auto-generated method stub
		//show grid
		this.getGrid().showGridStart();
		//creation of one ship
		boolean res=positionShip2(battleGame,"Carrier",5);
		while(!(res)){
			System.out.println("Not Possible, try again");
			res=positionShip2(battleGame,"Carrier",5);
		}
		this.getGrid().showGridStart();
		res=positionShip2(battleGame,"Battleship",4);
		while(!(res)){
			System.out.println("Not Possible, try again");
			res=positionShip2(battleGame,"Battleship",4);
		}
		this.getGrid().showGridStart();
		res=positionShip2(battleGame,"Cruiser",3);
		while(!(res)){
			System.out.println("Not Possible, try again");
			res=positionShip2(battleGame,"Cruiser",3);
		}
		this.getGrid().showGridStart();
		res=positionShip2(battleGame,"Submarine",3);
		while(!(res)){
			System.out.println("Not Possible, try again");
			res=positionShip2(battleGame,"Submarine",3);
		}
		this.getGrid().showGridStart();
		res=positionShip2(battleGame,"Destroyer",2);
		while(!(res)){
			System.out.println("Not Possible, try again");
			res=positionShip2(battleGame,"Destroyer",2);
		}
		this.getGrid().showGridStart();
		System.out.println("All your ship are on the grid ");	
	}
	



	@Override
	public String shoot(samson.audrey.Game battleGame) {
		// TODO Auto-generated method stub
		//System.out.println("Player "+ this.getNumber());
		//show grid
		this.getGrid().showGridPlay();
		System.out.println("Where do you want to shoot ? ");
		Scanner sc=new Scanner(System.in);
		System.out.println("Please, enter a position like 'A1','B2' ... ");
		System.out.println("Shoot's position ? : ");
		String coordShoot= sc.nextLine();
		while(!(this.getGrid().isOnGrid(coordShoot,1))){
			System.out.println("IT'S NOT POSIBLE");
			System.out.println("Please, enter a position like 'A1','B2' ... ");
			System.out.println("Shoot's position ? : ");
			coordShoot= sc.nextLine();
		}
		System.out.println("TIR OK ");
		return coordShoot;
	}
	
		public static void main(String[] args){
		
		Human h1=new Human(1,5);
		Human h2=new Human(2,5);
		samson.audrey.Game battleGame=new samson.audrey.Game(h1,h2);
		h1.positionShip(battleGame);
	}
}
