package lojeprojekt;

import java.util.Random;

public class Dice {

	private int currentface;

public Dice() {
	currentface=0;
}
public int getFace()
{
	return currentface;
}
public void roll() 
{
	Random r=new Random();
	currentface=r.nextInt(6)+1;
}


}
