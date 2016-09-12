package com.roxoft.hierarchy.randomFillers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.roxoft.hierarchy.data.Specialities;

public class RandomSpecialitiesFiller {

	public ArrayList<String> getSpecialities(int number){
		
		ArrayList<String> specialities = new ArrayList<String>();
		
		Specialities dataSpecialitiesNames = new Specialities();
		
		ArrayList<String> specialitiesNames = new ArrayList<String>(Arrays.asList(
				dataSpecialitiesNames.getSpecialities()));
		
		Random rand = new Random();
		int size = specialitiesNames.size();
				
		for (int i=0; i<number; i++)
			specialities.add(specialitiesNames.get(rand.nextInt(size)));
				
		return specialities;
	}
	
}
