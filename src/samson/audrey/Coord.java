package samson.audrey;

public class Coord {
	
	private String letter;
	private String number;
	private boolean free;//there is a ship or not 
	private boolean chosenToShoot;//already chosen to shoot or not 
	private boolean opponentShip;//Chosen to shoot and there is a ship hit
	
	
	public Coord(String letterAndNumber){
		this.letter=letterAndNumber.substring(0,1);
		if(letterAndNumber.length()==3){
			this.number=letterAndNumber.substring(1,2).concat(letterAndNumber.substring(2,3));
		}
		else{
			this.number=letterAndNumber.substring(1,2);
		}
		free=true;
		chosenToShoot=false;
		opponentShip=false;
		
	}
	
	public String getLetter() {
		return letter;
	}

	public String getNumber() {
		return number;
	}
	
	public static char[] convertToArrayChar(String letterOrNumber){
		char[] res=letterOrNumber.toCharArray();
		return res;
	}
	
	public static char getFirstChar(String letterOrNumber){
		
		
		if(letterOrNumber.length()==2){
			/*r=letterOrNumber.substring(1,2).concat(letterOrNumber.substring(2,3));
			char re=convertToArrayChar(letterOrNumber)[0];
			char ress=convertToArrayChar(letterOrNumber)[1];
			res=re+ress;*/
			//System.out.println("NOT POSIBLE");
			char erreur='e';
			return erreur;
		}
		else{
			char res=convertToArrayChar(letterOrNumber)[0];
			return res;
		}
		
	}

	public boolean isFree() {
		return this.free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public boolean isChosenToShoot() {
		return chosenToShoot;
	}

	public void setChosenToShoot(boolean chosenToShoot) {
		this.chosenToShoot = chosenToShoot;
	}

	public boolean hasOpponentShip() {
		return opponentShip;
	}

	public void setOpponentShip(boolean opponentShip) {
		this.opponentShip = opponentShip;
	}

	public static int convertLetterToInt(char letter){
		int res=1;
		char a='A';
		while(a!=letter){
			a++;
			res++;
		}
		return res;
	}
	public static int convertIntCharToInt(String number){
		return Integer.parseInt(number);
	}
	
	public static void main(String[] args) {
		System.out.println(Coord.convertIntCharToInt("8"));
	}
}
