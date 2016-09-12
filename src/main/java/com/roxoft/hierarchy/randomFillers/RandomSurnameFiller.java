package com.roxoft.hierarchy.randomFillers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.roxoft.hierarchy.data.SurnamesFemale;
import com.roxoft.hierarchy.data.SurnamesMale;

public class RandomSurnameFiller {
	
	public ArrayList<String> getSurnames (int number){
		
		ArrayList<String> surnames = new ArrayList<String>();
		
		SurnamesMale dataSurnamesMale = new SurnamesMale();
		SurnamesFemale dataSurnamesFemale = new SurnamesFemale();
		
		ArrayList<String> sm = new ArrayList<String>(Arrays.asList(
				dataSurnamesMale.getSurnames()));
		ArrayList<String> sf = new ArrayList<String>(
				Arrays.asList(dataSurnamesFemale.getSurnames()));
		
		Random rand = new Random();
		
		for (int i=0; i<number; i++){
			switch (i%2){
			case 0: surnames.add(sm.get(rand.nextInt(sm.size()))); break;
			case 1:	surnames.add(sf.get(rand.nextInt(sf.size()))); break;
			}
		}
		
		return surnames;
		
	}
}
