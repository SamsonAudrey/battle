package samson.audrey;
import samson.audrey.Player;
import samson.audrey.Ship;


public class Game {
	private Player currentPlayer;
	private Player opponentPlayer;

public Game(Player first,Player second){
	currentPlayer=first;
	opponentPlayer=second;
}

public Player getCurrentPlayer() {
	return currentPlayer;
}

public void setCurrentPlayer(Player currentPlayer) {
	this.currentPlayer = currentPlayer;
}

public Player getOpponentPlayer() {
	return opponentPlayer;
}

public void setOpponentPlayer(Player opponentPlayer) {
	this.opponentPlayer = opponentPlayer;
}

public void changeCurrentPlayer(){
	Player a=this.currentPlayer;
	setCurrentPlayer(this.opponentPlayer);
	setOpponentPlayer(a);
}

public void toShoot(boolean human,String coordShoot){
	Grid gridPlayer=this.getCurrentPlayer().getGrid();
	Fleet fleetPlayerAdv=this.getOpponentPlayer().getFleet();
	Coord[][] boxesPlayer=gridPlayer.getBoxes();
	Coord[][] boxesPlayerAdv=this.getOpponentPlayer().getGrid().getBoxes();
	Ship[] sh=fleetPlayerAdv.getShips();
	int ship=0;
	int hit=0;//0=missed 1=hit 2=destroyed 3=already hit
	String l=coordShoot.substring(0,1);
	char ll=Coord.getFirstChar(l);
	int lll=Coord.convertLetterToInt(ll);
	int n;
	if(coordShoot.length()==3){
		n=Coord.convertIntCharToInt((coordShoot.substring(1,2).concat(coordShoot.substring(2,3))));
	}
	else{
		n=Coord.convertIntCharToInt(coordShoot.substring(1,2));
	}
	boxesPlayer[n-1][lll-1].setChosenToShoot(true);
	while(ship<5 && hit==0){
		if(!(boxesPlayerAdv[n-1][lll-1].isFree())){
			if(sh[ship].isHit(coordShoot)){
				hit=1;
				if(boxesPlayer[n-1][lll-1].hasOpponentShip()){
					hit=3;
				}
				else{
					boxesPlayer[n-1][lll-1].setOpponentShip(true);
					sh[ship].addNumberTouch();
				}
				if(hit!=3 && sh[ship].isDestroyed()){ 
					fleetPlayerAdv.setNbAlive(fleetPlayerAdv.getNbAlive()-1);
					hit=2;
					

				}
			}
		}
		ship++;
	}
	if(human){
	if(hit==2){
		System.out.println("----> DESTROYED !!!! <----");
	}
	else if(hit==1){
		System.out.println("----> HIT !!!! <----");
	}
	else if(hit==3){
		System.out.println("----> ALREADY TOUCH !!!! <----");
	}
	else{ 
		System.out.println("----> MISSED !!!! <----");
	}
	}
	

}
}
