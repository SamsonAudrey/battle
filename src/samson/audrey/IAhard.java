package samson.audrey;
//BONNE
public class IAhard extends Player {

	private int[][] coordTouch;
	private int nbShipDestroyed;
	
	public IAhard(int number, int length) {
		super(number, length);
		// TODO Auto-generated constructor stub
		this.nbShipDestroyed=0;
		this.coordTouch=new int[8][2];
		for(int i=0;i<8;i++){
			for(int j=0;j<2;j++){
				this.coordTouch[i][j]=-1;
			}
		}
	}

	public int getNbShipDestroyed() {
		return nbShipDestroyed;
	}

	public void setNbShipDestroyed(int nbShipDestroyed) {
		this.nbShipDestroyed = nbShipDestroyed;
	}

	public int[][] getCoordTouch() {
		return coordTouch;
	}
	

	public void setCoordTouch(int x,int y) {
		this.coordTouch[0][0] = x;
		this.coordTouch[0][1] =y;
		for(int i=1;i<8;i++){
			for(int j=0;j<2;j++){
				this.coordTouch[i][j]=-1;
			}
		}
	}
	
	public int endShoot(){
		int i=0;
		while(i<8 && this.coordTouch[i][0]!=-1){
			i++;
		}
		return i-1;
	}
	
	public void addCoordTouch(int x,int y){
		int i=0;
		while(i<8 && this.coordTouch[i][0]!=-1){
			i++;
		}
		if(i>8){
			System.out.println("EREUUUUR");
		}
		this.coordTouch[i][0]=x;
		this.coordTouch[i][1]=y;
	}
	
	public int minYShoot(){
		int res=-1;
		int min=this.getGrid().getBoxes().length;
		for(int i=0;i<8;i++){
			if(this.coordTouch[i][1]!=-1){
				if(this.coordTouch[i][1]<min){
					res=i;
					min=this.coordTouch[i][1];
				}
			}
		}
		return res;
	}

	public int minXShoot(){
		int res=-1;
		int min=this.getGrid().getBoxes().length;
		for(int i=0;i<8;i++){
			if(this.coordTouch[i][0]!=-1){
				if(this.coordTouch[i][0]<min){
					res=i;
					min=this.coordTouch[i][0];
				}
			}
		}
		return res;
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

	@Override
	public String shoot(Game battleGame) {
		// TODO Auto-generated method stub
		String shoot;
		String shoot2;
		String res;
		if(battleGame.getOpponentPlayer().getFleet().getNbAlive()+this.nbShipDestroyed<5){
			//one more ship destroyed
			this.setNbShipDestroyed(this.nbShipDestroyed+1);
			this.setCoordTouch(-1, -1);
		}
		if(this.coordTouch[0][0]==-1){//no ship
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
			shoot=Integer.toString(numberRandom);
			shoot2=Character.toString(letter);
			res=shoot2.concat(shoot);
			//System.out.println(res);
			if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[numberRandom-1][letterRandom-1].isFree())){
				this.setCoordTouch(numberRandom-1, letterRandom-1);
			}
		}
		else{//ship targeted
			int l=this.getGrid().getBoxes().length;
			if(this.coordTouch[1][0]==-1){//only one shot
				int xbase=this.coordTouch[0][0];
				int ybase=this.coordTouch[0][1];
				
					if(xbase+1<l && !(this.getGrid().getBoxes()[xbase+1][ybase].isChosenToShoot())){
						shoot=Integer.toString(xbase+2);
						char letter='A';
						for(int alpha=1;alpha<ybase+1;alpha++){
							letter++;
						}
						shoot2=Character.toString(letter);
						res=shoot2.concat(shoot);
						if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xbase+1][ybase].isFree())){
							this.addCoordTouch(xbase+1, ybase);
								}
					}
					else if(xbase-1>=0 && !(this.getGrid().getBoxes()[xbase-1][ybase].isChosenToShoot())){
						shoot=Integer.toString(xbase);
						char letter='A';
						for(int alpha=1;alpha<ybase+1;alpha++){
							letter++;
						}
						shoot2=Character.toString(letter);
						res=shoot2.concat(shoot);
						if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xbase-1][ybase].isFree())){
							this.addCoordTouch(xbase-1, ybase);
						}
						
					}
					else if(ybase+1<l && !(this.getGrid().getBoxes()[xbase][ybase+1].isChosenToShoot())){
						shoot=Integer.toString(xbase+1);
						char letter='A';
						for(int alpha=1;alpha<ybase+2;alpha++){
							letter++;
						}
						shoot2=Character.toString(letter);
						res=shoot2.concat(shoot);
						if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xbase][ybase+1].isFree())){
							this.addCoordTouch(xbase, ybase+1);
						}
					}
					else {//if(!(this.getGrid().getBoxes()[xbase][ybase-1].isChosenToShoot())){
						shoot=Integer.toString(xbase+1);
						char letter='A';
						for(int alpha=1;alpha<ybase;alpha++){
							letter++;
						}
						shoot2=Character.toString(letter);
						res=shoot2.concat(shoot);
						;
						if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xbase][ybase-1].isFree())){
							this.addCoordTouch(xbase, ybase-1);
						}
					}
					
			}
			else{//more than one shoot
				if(this.coordTouch[0][0]==this.coordTouch[1][0]){
					//the ship is horizontal
					if(this.coordTouch[2][0]==-1){//only two shoot
						int xseconde=this.coordTouch[1][0];
						int yseconde=this.coordTouch[1][1];
						if(yseconde+1<l && !(this.getGrid().getBoxes()[xseconde][yseconde+1].isChosenToShoot())){
							shoot=Integer.toString(xseconde+1);
							char letter='A';
							for(int alpha=1;alpha<yseconde+2;alpha++){
								letter++;
							}
							shoot2=Character.toString(letter);
							res=shoot2.concat(shoot);
							if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xseconde][yseconde+1].isFree())){
								this.addCoordTouch(xseconde, yseconde+1);
							}
							
						}
						else{//xseconde yseconde-1
							shoot=Integer.toString(xseconde+1);
							char letter='A';
							for(int alpha=1;alpha<yseconde;alpha++){
								letter++;
							}
							shoot2=Character.toString(letter);
							res=shoot2.concat(shoot);
							if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xseconde][yseconde-1].isFree())){
								this.addCoordTouch(xseconde, yseconde-1);
							}
						}
					}
					else{//more than two shoot
						int last=this.endShoot();
						int xlast=this.coordTouch[last][0];
						int ylast=this.coordTouch[last][1];
						if(ylast+1<l && !(this.getGrid().getBoxes()[xlast][ylast+1].isChosenToShoot())){
							shoot=Integer.toString(xlast+1);
							char letter='A';
							for(int alpha=1;alpha<ylast+2;alpha++){
								letter++;
							}
							shoot2=Character.toString(letter);
							res=shoot2.concat(shoot);
							if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xlast][ylast+1].isFree())){
								this.addCoordTouch(xlast, ylast+1);
							}
						}
						else{
							int min=this.minYShoot();
							int ymin=this.coordTouch[min][1];
							int xmin=this.coordTouch[min][0];
							if(ymin-1>=0 && !(this.getGrid().getBoxes()[xmin][ymin-1].isChosenToShoot())){
								shoot=Integer.toString(xmin+1);
								char letter='A';
								for(int alpha=1;alpha<ymin;alpha++){
									letter++;
								}
								shoot2=Character.toString(letter);
								res=shoot2.concat(shoot);
								if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xmin][ymin-1].isFree())){
									this.addCoordTouch(xmin, ymin-1);
								}
							}
							else{
								res="A1";
								System.out.println("PROBLEMMEEEE");
							}
							}
						}
					}
				else{//the ship is vertical
					if(this.coordTouch[2][0]==-1){//only two shoot
						int xseconde=this.coordTouch[1][0];
						int yseconde=this.coordTouch[1][1];
						if(xseconde+1<l && !(this.getGrid().getBoxes()[xseconde+1][yseconde].isChosenToShoot())){
							shoot=Integer.toString(xseconde+2);
							char letter='A';
							for(int alpha=1;alpha<yseconde+1;alpha++){
								letter++;
							}
							shoot2=Character.toString(letter);
							res=shoot2.concat(shoot);
							this.addCoordTouch(xseconde+1, yseconde);
						}
						else{//xseconde-1 yseconde
							shoot=Integer.toString(xseconde);
							char letter='A';
							for(int alpha=1;alpha<yseconde+1;alpha++){
								letter++;
							}
							shoot2=Character.toString(letter);
							res=shoot2.concat(shoot);
							this.addCoordTouch(xseconde-1, yseconde);
						}
					}
					else{//more than two shoot
						int last=this.endShoot();
						int xlast=this.coordTouch[last][0];
						int ylast=this.coordTouch[last][1];
						if(xlast+1<l && !(this.getGrid().getBoxes()[xlast+1][ylast].isChosenToShoot())){
							shoot=Integer.toString(xlast+2);
							char letter='A';
							for(int alpha=1;alpha<ylast+1;alpha++){
								letter++;
							}
							shoot2=Character.toString(letter);
							res=shoot2.concat(shoot);
							if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xlast+1][ylast].isFree())){
								this.addCoordTouch(xlast+1, ylast);
							}
						}
						else{
							int min=this.minXShoot();
							int ymin=this.coordTouch[min][1];
							int xmin=this.coordTouch[min][0];
							if(xmin-1>=0 && !(this.getGrid().getBoxes()[xmin-1][ymin].isChosenToShoot())){
								shoot=Integer.toString(xmin);
								char letter='A';
								for(int alpha=1;alpha<ymin+1;alpha++){
									letter++;
								}
								shoot2=Character.toString(letter);
								res=shoot2.concat(shoot);
								if(!(battleGame.getOpponentPlayer().getGrid().getBoxes()[xmin-1][ymin].isFree())){
									this.addCoordTouch(xmin-1, ymin);
								}
							}
							else{
								res="A1";
								System.out.println("PROBLEMMEEEE");
							}
							}
					}
					
				}

				}
			}
		//System.out.println("je tire hard");
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
