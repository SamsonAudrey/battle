package samson.audrey;

public class IAmedium extends Player {

	public IAmedium(int number, int length) {
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
			Coord[][] b=this.getGrid().getBoxes();
			Ship s1=new Ship("Carrier",b[0][9],b[4][9]);
			this.getFleet().addShip(s1);
			b[0][9].setFree(false);
			b[4][9].setFree(false);
			for(int bb=1;bb<4;bb++){
				b[bb][9].setFree(false);
			}
			//System.out.println("ship Carrier added");
			Ship s2=new Ship("BattleShip",b[5][0],b[5][3]);
			this.getFleet().addShip(s2);
			b[5][0].setFree(false);
			b[5][3].setFree(false);
			for(int bb=1;bb<3;bb++){
				b[5][bb].setFree(false);
			}
			//System.out.println("ship BattleShip added");
			Ship s3=new Ship("Cruiser",b[9][7],b[9][9]);
			this.getFleet().addShip(s3);
			b[9][7].setFree(false);
			b[9][9].setFree(false);
			for(int bb=7;bb<9;bb++){
				b[9][bb].setFree(false);
			}
			//System.out.println("ship Cruiser added");
			Ship s4=new Ship("Submarine",b[1][0],b[3][0]);
			this.getFleet().addShip(s4);
			b[1][0].setFree(false);
			b[3][0].setFree(false);
			for(int bb=2;bb<3;bb++){
				b[bb][0].setFree(false);
			}
			//System.out.println("ship Submarine added");
			Ship s5=new Ship("Destroyer",b[0][1],b[1][1]);
			this.getFleet().addShip(s5);
			b[0][1].setFree(false);
			b[1][1].setFree(false);
			for(int bb=1;bb<1;bb++){
				b[bb][1].setFree(false);
			}
			//System.out.println("ship Destroyer added");
	}

	@Override
	public String shoot(Game battleGame) {
		// TODO Auto-generated method stub
		int numberRandom = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
		int letterRandom = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
		while(!(this.choseShoot(battleGame,numberRandom,letterRandom))){
			numberRandom = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
			letterRandom = 1 + (int)(Math.random() * ((this.getGrid().getBoxes().length - 1) + 1));
		}
		
		char letter='A';
		for(int alpha=1;alpha<letterRandom;alpha++){
			letter++;
		}
		String shoot=Integer.toString(numberRandom);
		String shoot2=Character.toString(letter);
		String res=shoot2.concat(shoot);
		//System.out.println(res);
	
		return res;
				
	}
	
	public boolean choseShoot(Game battleGame,int x,int y){
		Coord [][] c=this.getGrid().getBoxes();
		if(c[x-1][y-1].isChosenToShoot()){
			//System.out.println("deja choisi");
			return false;
		}
		else{
			c[x-1][y-1].setChosenToShoot(true);
			return true;}
	}
		

}
