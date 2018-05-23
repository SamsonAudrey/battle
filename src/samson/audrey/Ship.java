package samson.audrey;
import samson.audrey.Coord;

public class Ship {
	//Coord => 'A1','A2'...
	private String name;
	private Coord startCoord;
	private Coord endCoord;
	private int numberTouch;
	//if the ship is hit, numberTouch++. 
	//if numberTouch=ship's lenght, the ship is destroyed
	
	public Ship(String name,Coord startCoord, Coord endCoord){
		this.name=name;
		this.startCoord=startCoord;
	    this.endCoord=endCoord;
	    numberTouch=0;
	}
	
	//method isHorizontal returns TRUE if the ship is posed 'horizontaly', else returns FALSE ('verticaly')
	public boolean isHorizontal(){
		if(startCoord.getNumber().equals(endCoord.getNumber())){
			return true;
		}
		else return false;
	}
	
	//method isHit returns TRUE if the ship is hit by the missile, and FALSE if it's not
	public boolean isHit(String missileCoord){
		boolean res=false;
		String missileCol=missileCoord.substring(0,1);//missile's col 'A'		
		String missileLine;//misile's line '1' 
		if(missileCoord.length()==3){
			missileLine=(missileCoord.substring(1,2).concat(missileCoord.substring(2,3)));
		}
		else{
			missileLine=missileCoord.substring(1,2);
		}		
		if (this.isHorizontal() && missileLine.equals(this.startCoord.getNumber())){ 
			//same line (int)
			char letterMissi=Coord.getFirstChar(missileCol);
			int letterMissile=Coord.convertLetterToInt(letterMissi);
			String letterSh=this.startCoord.getLetter();
			char letterShi=Coord.getFirstChar(letterSh);
			int letterShip=Coord.convertLetterToInt(letterShi);
			String curse=this.endCoord.getLetter();
			char cursen=Coord.getFirstChar(curse);
			int cursend=Coord.convertLetterToInt(cursen);
			
			while(letterMissile!=letterShip && letterShip!=cursend){
				letterShip++;
			}
			if(letterMissile==letterShip){
				return true;
			}
			else{
				return false;}
		}
		else if(missileCol.equals(this.startCoord.getLetter())){
			//same col (letter)
			int numbMissile=Coord.convertIntCharToInt(missileLine);
			int numbShip=Coord.convertIntCharToInt(this.startCoord.getNumber());
			int cursend=Coord.convertIntCharToInt(this.endCoord.getNumber());
			while(numbMissile!=numbShip && numbShip<=cursend){
				numbShip++;
			}
			if(numbShip==numbMissile){
				return true;
			}
			else{return false;}
		}
		else{return false;}
		}

	public int length(){
		int res=0;
		if (this.isHorizontal()){
			char start=Coord.getFirstChar(startCoord.getLetter());
			char end=Coord.getFirstChar(endCoord.getLetter());
			for (char cursor=start;cursor<=end;cursor++){
				res+=1;
			}
		}
		else {
			char start=Coord.getFirstChar(startCoord.getNumber());
			char end;
			if(endCoord.getNumber().length()==2){
				end='9';
				res+=1;
			}
			else{
				end=Coord.getFirstChar(endCoord.getNumber());
			}
			for (char cursor=start;cursor<=end;cursor++){
				res+=1;
			}
		}
		return res;
	}
	
	//method isDestroyed returns TRUE if the ship has sunk, FALSE if it hasn't
	public boolean isDestroyed(){
	    if(this.numberTouch==this.length()){
	    	return true;
	    }
	    else{
	    	return false;
	    }
	}

	public String getName() {
		return name;
	}

	public Coord getStartCoord(){
		return this.startCoord;
	}
	public void addNumberTouch(){
		this.numberTouch=this.numberTouch+1;
	}
	
	public int getNumberTouch(){
		return this.numberTouch;
	}
}
