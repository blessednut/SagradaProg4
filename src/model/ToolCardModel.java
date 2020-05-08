package model;

import java.util.Random;

public class ToolCardModel {
	private String a;
	private String b;
	private String c;
	
	public ToolCardModel() {
		
	}
	
	public String getCard() {
//		TODO code veranderen naar een connectie met de database
//		dit is nu testcode
		a = "Driepuntstang";
		b = "Fluxborstel";
		c = "Fluxverwijderaar";
		Random rand = new Random();
		int max = 3;
		int min = 1;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		if(randomNum == 1) {
			return a;
		}
		else if(randomNum == 2) {
			return b;
		}
		else {
			return c;
		}
		
		
	

}
}
	
	
