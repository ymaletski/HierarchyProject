package com.roxoft.hierarchy.randomFillers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.roxoft.hierarchy.data.NamesFemale;
import com.roxoft.hierarchy.data.NamesMale;

public class RandomNameFiller {
		
	public ArrayList<String> getNames (int number){
		ArrayList<String> names = new ArrayList<String>();
		NamesMale dataNamesMale = new NamesMale();
		NamesFemale dataNamesFemale = new NamesFemale();
		ArrayList<String> nm = new ArrayList<String>(Arrays.asList(
				dataNamesMale.getNames()));
		ArrayList<String> nf = new ArrayList<String>(Arrays.asList(
				dataNamesFemale.getNames()));
		Random rand = new Random();		
		for (int i=0; i<number; i++){
			switch (i%2){
			case 0: names.add(nm.get(rand.nextInt(nm.size()))); break;
			case 1:	names.add(nf.get(rand.nextInt(nf.size()))); break;
			}
		}
		return names;
	}

}
