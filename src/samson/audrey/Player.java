package samson.audrey;
import samson.audrey.Fleet;

public abstract class Player implements IPlayAlone{
	private int number;
	private Fleet fleet;
	private Grid grid;

	//number 1 or 2 -> player1 and player2
	//length of the grids
public Player(int number,int length){
	this.number=number;
	Fleet f=new Fleet();
	fleet=f;
	Grid g=new Grid(length);
	grid=g;
}

public int getNumber() {
	return number;
}

public void setNumber(int number) {
	this.number = number;
}

public Fleet getFleet() {
	return fleet;
}

public Grid getGrid() {
	return grid;
}


}
