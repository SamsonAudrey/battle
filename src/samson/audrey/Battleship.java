package samson.audrey;

//************* HUMAN VS HUMAN 
//************* HUMAN VS IA (3 LEVELS)
import java.util.Scanner;
import samson.audrey.Player;
import samson.audrey.Game;

public class Battleship {

	public static void main(String[] args) {
		
	//length of the grid
	/*Scanner sclength = new Scanner(System.in);
	System.out.println("What is the length of your grid ? :");
	int str = sclength.nextInt();
	System.out.println("Your grid's length is : " + str);*/
	int str=10; //default
	
	boolean human=true;
	Player player1=new Human(1,str);//default
	Player player2;
	int IAplay_2=0;
	//creation of the second player 
	Scanner play2 = new Scanner(System.in);
	System.out.println("Player 2 : enter '0' for a Human / enter '1' for an IA ");
	int play_2 = play2.nextInt();
	while(play_2!=0 && play_2!=1){
		System.out.println("Not Possible, try again");
		System.out.println("Player 2 : enter '0' for a Human / enter '1' for an IA ");
		play_2 = play2.nextInt();
	}
	if(play_2==0){
		player2=new Human(2,str);
	}
	else{
		Scanner IAplay2 = new Scanner(System.in);
		System.out.println("Which level of IA do you want ? '1' '2' '3' ");
		IAplay_2 = IAplay2.nextInt();
		while(IAplay_2!=1 && IAplay_2!=2 && IAplay_2!=3){
			System.out.println("Not Possible, try again");
			System.out.println("Which level of IA do you want ? '1' '2' '3' ");
			IAplay_2 = IAplay2.nextInt();
		}
		if(IAplay_2==1){
			player2=new IAeasy(2,str);
		}
		else if(IAplay_2==2){
			player2=new IAmedium(2,str);
		}
		else {
			player2=new IAhard(2,str);
		}
	}
	
	int game=0;
	int winplayer1=0;
	int winplayer2=0;
	
	
	//creation of the game
	Game battleGame=new Game(player1,player2);
	int beginner=1;
	
	
	//creation of the ships + positions of them
	System.out.println("POSITIONING OF SHIPS");
	for(int count=0;count<2;count++){
		//player
		System.out.println("Player "+ battleGame.getCurrentPlayer().getNumber());
		battleGame.getCurrentPlayer().positionShip(battleGame);
		battleGame.changeCurrentPlayer();
		}
	
	boolean replay=true;
	
	while(replay){
	//DEBUT DE LA PARTIE 
	boolean dead=false;
	//*******************************************
	System.out.println(" ");
	System.out.println(" ");
	System.out.println(" ");
	System.out.println("LET'S PLAY !!!!");
	Grid gridPlayer=battleGame.getCurrentPlayer().getGrid();
	Fleet fleetPlayerAdv=battleGame.getOpponentPlayer().getFleet();
	Coord[][] boxesPlayer=gridPlayer.getBoxes();
	Coord[][] boxesPlayerAdv=battleGame.getOpponentPlayer().getGrid().getBoxes();
	Ship[] sh=fleetPlayerAdv.getShips();
	String coordShoot;
	
	int tour=0;
	while(tour <1000 && !(dead)){
		System.out.println(" ");
		System.out.println("Player "+battleGame.getCurrentPlayer().getNumber());
		System.out.println(" ");
		gridPlayer=battleGame.getCurrentPlayer().getGrid();
		fleetPlayerAdv=battleGame.getOpponentPlayer().getFleet();
		boxesPlayer=gridPlayer.getBoxes();
		boxesPlayerAdv=battleGame.getOpponentPlayer().getGrid().getBoxes();
		
		//SHOOT
		coordShoot=battleGame.getCurrentPlayer().shoot(battleGame);
		if(human){
			System.out.println(coordShoot);
		}
		battleGame.toShoot(human,coordShoot); //result of the shoot
		battleGame.getCurrentPlayer().getGrid().showGridPlay();
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
	
	
	System.out.println("AND........");
	System.out.println("...........");
	System.out.println("AFER "+tour+" turns");
	System.out.println("...........");
	System.out.println("THE WINNER IS PLAYER "+battleGame.getOpponentPlayer().getNumber());
	System.out.println(" ");
	System.out.println("LOSER ");
	battleGame.getCurrentPlayer().getGrid().showGridPlay();
	battleGame.changeCurrentPlayer();
	System.out.println(" ");
	System.out.println("WINNER ");
	battleGame.getCurrentPlayer().getGrid().showGridPlay();
	battleGame.changeCurrentPlayer();
	Scanner repeat = new Scanner(System.in);
	System.out.println("Do you want to play one more time ? : '0' for no / '1' for yes ");
	int rep = repeat.nextInt();
	while(rep!=0 && rep!=1){
		System.out.println("Not Possible, try again");
		System.out.println("Do you want to play one more time ? : '0' for no / '1' for yes ");
		rep = repeat.nextInt();
	}
	if(rep==1){
		player1=new Human(1,str);
		if(play_2==0){
			player2=new Human(2,str);
		}
	}
	else{
		replay=false;
	}	
	
	if(IAplay_2!=0){
		if(IAplay_2==1){
		player2=new IAeasy(2,str);
	}
	else if(IAplay_2==2){
		player2=new IAmedium(2,str);
	}
	else {
		player2=new IAhard(2,str);
	}
	}
	if(replay){
		
	
	//creation of the game
	battleGame=new Game(player1,player2);
	if(beginner==1){
		battleGame.changeCurrentPlayer();
		beginner=2;
	}
	else{
		beginner=1;
	}
	
	System.out.println("NOUVELLE PARTIE ");
	System.out.println(" ");
	System.out.println(" ");
	System.out.println("POSITIONING OF SHIPS");
	//creation of the ships + positions of them
	for(int count=0;count<2;count++){
		//player
		System.out.println("Player "+ battleGame.getCurrentPlayer().getNumber());
		battleGame.getCurrentPlayer().positionShip(battleGame);
		battleGame.changeCurrentPlayer();
		}
	}
	
	}

	System.out.println("THANK YOU !! SEE YOU NEXT TIME ");

	}
}

	
