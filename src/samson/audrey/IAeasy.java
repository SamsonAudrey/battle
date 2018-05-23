package samson.audrey;

import samson.audrey.Player;


public class IAeasy extends Player {

	public IAeasy(int number, int length) {
		super(number, length);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void positionShip(Game battleGame) {
		// TODO Auto-generated method stub
		//creation of one ship
		//Carrier 5
		//BattleShip 4
		//Cruiser 3
		//Submarine 3
		//Destroyer 2
		//*******It goes wrong 
//		Coord[][] b=this.getGrid().getBoxes();
//		this.createPosition(b,"Carrier", 5);
//		this.createPosition(b,"BattleShip", 4);
//		this.createPosition(b,"Cruiser", 3);
//		this.createPosition(b,"Submarine", 3);
//		this.createPosition(b,"Destroyer", 2);
		//********
				
		//********Simple method
		int h = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
		if(h>5){
			Coord[][] b=this.getGrid().getBoxes();
			Ship s1=new Ship("Carrier",b[0][9],b[4][9]);
			this.getFleet().addShip(s1);
			b[0][9].setFree(false);
			b[4][9].setFree(false);
			for(int bb=1;bb<4;bb++){
				b[bb][9].setFree(false);
			}
			Ship s2=new Ship("BattleShip",b[5][0],b[5][3]);
			this.getFleet().addShip(s2);
			b[5][0].setFree(false);
			b[5][3].setFree(false);
			for(int bb=1;bb<3;bb++){
				b[5][bb].setFree(false);
			}
			Ship s3=new Ship("Cruiser",b[9][7],b[9][9]);
			this.getFleet().addShip(s3);
			b[9][7].setFree(false);
			b[9][9].setFree(false);
			for(int bb=7;bb<9;bb++){
				b[9][bb].setFree(false);
			}
			Ship s4=new Ship("Submarine",b[1][0],b[3][0]);
			this.getFleet().addShip(s4);
			b[1][0].setFree(false);
			b[3][0].setFree(false);
			for(int bb=2;bb<3;bb++){
				b[bb][0].setFree(false);
			}
			Ship s5=new Ship("Destroyer",b[0][1],b[1][1]);
			this.getFleet().addShip(s5);
			b[0][1].setFree(false);
			b[1][1].setFree(false);
			for(int bb=1;bb<1;bb++){
				b[bb][1].setFree(false);
				}
		}
		else{
			Coord[][] b=this.getGrid().getBoxes();
			Ship s1=new Ship("Carrier",b[1][8],b[5][8]);
			this.getFleet().addShip(s1);
			b[1][8].setFree(false);
			b[5][8].setFree(false);
			for(int bb=2;bb<5;bb++){
				b[bb][8].setFree(false);
			}
			Ship s2=new Ship("BattleShip",b[6][0],b[6][3]);
			this.getFleet().addShip(s2);
			b[6][0].setFree(false);
			b[6][3].setFree(false);
			for(int bb=1;bb<3;bb++){
				b[6][bb].setFree(false);
			}
			Ship s3=new Ship("Cruiser",b[9][5],b[9][7]);
			this.getFleet().addShip(s3);
			b[9][5].setFree(false);
			b[9][7].setFree(false);
			for(int bb=6;bb<7;bb++){
				b[9][bb].setFree(false);
			}
			Ship s4=new Ship("Submarine",b[1][3],b[3][3]);
			this.getFleet().addShip(s4);
			b[1][3].setFree(false);
			b[3][3].setFree(false);
			for(int bb=2;bb<3;bb++){
				b[bb][3].setFree(false);
			}
			Ship s5=new Ship("Destroyer",b[8][1],b[9][1]);
			this.getFleet().addShip(s5);
			b[8][1].setFree(false);
			b[9][1].setFree(false);
			for(int bb=9;bb<9;bb++){
				b[bb][1].setFree(false);
			}
		}	
	}
			
	//********Method which goes wrong and I did'nt find why
//	public void createPosition(Coord[][] b,String name,int length){
//		boolean horizontal;
//		int h = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
//		if(h>5){
//			horizontal=true;
//		}
//		else{
//			horizontal=false;
//		}
//		//CoordStart Random
//		int xStart = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
//		int yStart = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
//		char letter='A';
//		for(int alpha=1;alpha<yStart;alpha++){
//			letter++;
//		}
//		String shoot=Integer.toString(xStart);
//		String shoot2=Character.toString(letter);
//		String coordStart=shoot2.concat(shoot);
//		//CoordEnd Calculated
//		String coordEnd;
//		if(horizontal){
//			coordEnd=this.calculEndPos(xStart, yStart, length, horizontal);
//		}
//		else{
//			coordEnd=this.calculEndPos(xStart, yStart, length, horizontal);
//		}
//		while(!(this.getGrid().isOnGrid(coordStart, length)) || !(this.getGrid().isOnGrid(coordEnd, 1)) || !(this.isOk(coordStart, length, horizontal))){
//			xStart = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
//			yStart = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
//			letter='A';
//			for(int alpha=1;alpha<yStart;alpha++){
//				letter++;
//			}
//			shoot=Integer.toString(xStart);
//			shoot2=Character.toString(letter);
//			coordStart=shoot2.concat(shoot);
//			//CoordEnd Calculated
//			if(horizontal){
//				coordEnd=this.calculEndPos(xStart, yStart, length, horizontal);
//			}
//			else{
//				coordEnd=this.calculEndPos(xStart, yStart, length, horizontal);
//			}
//		}
//		int yEnd=Coord.convertLetterToInt(Coord.getFirstChar(coordEnd.substring(0,1)));
//		int xEnd;
//		if(coordEnd.length()==3){
//			xEnd=Integer.parseInt(coordEnd.substring(1,2).concat((coordEnd).substring(2, 3)));
//		}
//		else{
//			xEnd=Integer.parseInt(coordEnd.substring(1,2));
//		}
//		
//		System.out.println(xStart +" "+ yStart +" "+xEnd+" "+yEnd);
//		Ship s1=new Ship(name,b[xStart-1][yStart-1],b[xEnd-1][yEnd-1]);
//		this.getFleet().addShip(s1);
		//
//				
//				
//		if(horizontal){
//			for(int bb=yStart;bb<=yEnd;bb++){
//				b[xStart-1][bb-1].setFree(false);
//			}
//		}
//		else{
//			for(int bb=xStart;bb<=xEnd;bb++){
//				b[bb-1][yStart-1].setFree(false);
//			}
//		}
//			
		//
//	}
		//
//	public String calculEndPos(int xstart,int ystart,int length,boolean horizontal){
//		String res;
//		String xend,yend;
//		if(horizontal){
//			for(int i=1;i<length;i++){
//				ystart++;
//			}
//			char letterEnd='A';
//			for(int alpha=1;alpha<ystart;alpha++){
//				letterEnd++;
//			}
//			xend=Integer.toString(xstart);
//			yend=Character.toString(letterEnd);
//			res=yend.concat(xend);
//		}
//		else{
//			for(int i=1;i<length;i++){
//				xstart++;
//			}
//			char letterEnd='A';
//			for(int alpha=1;alpha<ystart;alpha++){
//				letterEnd++;
//			}
//			xend=Integer.toString(xstart);
//			yend=Character.toString(letterEnd);
//			res=yend.concat(xend);
//		}
//		
//		return res;
//	}
		//	
		//	
//	public boolean isOk(String coord,int length,boolean horizontal){
//		boolean res=true;
//		int letter=Coord.convertLetterToInt(Coord.getFirstChar(coord.substring(0,1)));
//		int number;
//		if(coord.length()>2){
//			number=Integer.parseInt(coord.substring(1,2).concat(coord.substring(2, 3)));
//		}
//		else{
//			number=Integer.parseInt(coord.substring(1,2));
//		}
//		if(horizontal){
//			for(int i=0;i<length;i++){
//				if(!(this.getGrid().getBoxes()[number-1][letter-1+i].isFree())){
//					res=false;
//				}
//			}
//		}
//		else{
//			for(int i=0;i<length;i++){
//				if(!(this.getGrid().getBoxes()[number-1+i][letter-1].isFree())){
//					res=false;
//				}
//			}
//		}
//		return res;
//	}


	@Override
	public String shoot(Game battleGame) {
		// TODO Auto-generated method stub
		int numberRandom = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
		int letterRandom = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
		char letter='A';
		for(int alpha=1;alpha<letterRandom;alpha++){
			letter++;
		}
		String shoot=Integer.toString(numberRandom);
		String shoot2=Character.toString(letter);
		String res=shoot2.concat(shoot);
		return res;
		
	}

}
