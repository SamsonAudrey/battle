package samson.audrey;
import samson.audrey.Ship;

public class Fleet {
	private Ship[] ships;
	private int nbAlive;
	
public Fleet(){
	nbAlive=0;
	Ship[] ships=new Ship[5];
	this.ships=ships;
}
	

public void addShip(Ship ship){
	if(nbAlive==5){
		System.out.println("WARNING: the fleet is full ! ");
	}
	else {
		ships[nbAlive]=ship;
		this.setNbAlive(nbAlive+1);
	}	
}


public int getNbAlive() {
	return nbAlive;
}


public void setNbAlive(int nbAlive) {
	this.nbAlive = nbAlive;
}

public Ship[] getShips(){
	return this.ships;
}
	
	
}
