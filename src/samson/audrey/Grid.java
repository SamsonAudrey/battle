package samson.audrey;
import samson.audrey.Coord;
//import java.lang.*;

public class Grid {
	private Coord[][] boxes;
	private char letterEnd;
	
	public Grid(int length){
		
		Coord[][] bo=new Coord[length][length];
		
		//creation of Coord
		char letterEnd='A';
		for(int alpha=1;alpha<length;alpha++){
			letterEnd++;
		}
		this.letterEnd=letterEnd;
		int num=-1;
		for(char letter='A';letter<=letterEnd;letter++){
			num++;
			for(int count=0;count<length;count++){
				Coord c=new Coord(this.convertToString(letter, count+1));
				bo[count][num]=c;
			}
		}
		boxes=bo;
	}
	
	public Coord[][] getBoxes() {
		return boxes;
	}

	public void setBoxes(Coord[][] boxes) {
		this.boxes = boxes;
	}

	public char getLetterEnd() {
		return letterEnd;
	}

	public void setLetterEnd(char letterEnd) {
		this.letterEnd = letterEnd;
	}

	public Coord getCoordBoxes(int x,int y) {
		return this.boxes[x][y];
	}
	
	

	public String convertToString(char letter, int number){
	String let=String.valueOf(letter);
	String numb=String.valueOf(number);
	String res=let.concat(numb);
	return res;	
}

	public void showGridStart(){
		System.out.print("   ");
		for(char letter='A';letter<=letterEnd;letter++){
			System.out.print("|");
			System.out.print(letter);
		}
		System.out.println("|");
		for(int curs=0;curs<this.boxes.length;curs++){
			if(curs>8){
				System.out.print(curs+1+" |");
			}
			else{
				System.out.print(curs+1 +"  |");
			}
			for(int curs2=0;curs2<this.boxes.length;curs2++){
				if(this.boxes[curs][curs2].isFree()){
					System.out.print(" ");
					System.out.print("|");
				}
				else{
					System.out.print("X");
					System.out.print("|");
				}
			}
			System.out.println(" ");
		}
	}

	public void showGridPlay(){
		System.out.print("   ");
		for(char letter='A';letter<=letterEnd;letter++){
			System.out.print("|");
			System.out.print(letter);
		}
		System.out.println("|");
		for(int curs=0;curs<this.boxes.length;curs++){
			if(curs>8){
				System.out.print(curs+1+" |");
			}
			else{
				System.out.print(curs+1 +"  |");
			}
			for(int curs2=0;curs2<this.boxes.length;curs2++){
				if(this.boxes[curs][curs2].isChosenToShoot()){
					if(this.boxes[curs][curs2].hasOpponentShip()){
						System.out.print("X");
						System.out.print("|");
					}
					else{
						System.out.print("O");
					System.out.print("|");
					}
				}
				else{
					System.out.print(" ");
					System.out.print("|");
				}
			}
			System.out.println(" ");
		}
	}
	
	public boolean isOnGrid(String letterAndNumber,int length){
	if(letterAndNumber.length()>3 || letterAndNumber.length()==0 || letterAndNumber.length()==1){
		return false;
	}
	String letter=letterAndNumber.substring(0,1);
	String number;
	if(letterAndNumber.length()==3){
		number=(letterAndNumber.substring(1,2).concat(letterAndNumber.substring(2,3)));
	}
	else{
		number=letterAndNumber.substring(1,2);
	}
	if (!(number.equals("10")) && letterAndNumber.length()==3){
		return false;
	}
	if (Coord.getFirstChar(letter)>letterEnd){
		return false;
	}
	if (number.equals("10")){
		return true;
	}
	else if(Character.getNumericValue(Coord.getFirstChar(number))>this.boxes.length){
		return false;
	}
	else {
		char a=Coord.getFirstChar(letter);
		for(int i=0;i<length;i++){
			a++;
		}
		if(Character.getNumericValue(Coord.getFirstChar(number))+length-1 >this.boxes.length && (a>=letterEnd)){
			return false;
		}
		else return true;		
	}
}

}






