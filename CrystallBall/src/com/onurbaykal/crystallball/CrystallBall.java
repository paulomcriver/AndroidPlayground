package com.onurbaykal.crystallball;

import java.util.Random;

public class CrystallBall {

	private static String mAnswer[] = 
		{
			"It is certain",
			"It is decidedly so",
			"All signs say yes",
			"The stars are not aligned",
			"My reply is no",
			"It is doubtful",
			"Better not tell you now",
			"Concentrate and try again",
			"Unable to answer now"
		};
	
	public static String getAnAnswer()
	{	
		Random r = new Random();
		return mAnswer[r.nextInt(mAnswer.length)];
	}
}
