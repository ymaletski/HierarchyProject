package com.roxoft.hierarchy.randomFillers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.roxoft.hierarchy.data.NamesFemale;
import com.roxoft.hierarchy.data.NamesMale;
import com.roxoft.hierarchy.data.SurnamesFemale;
import com.roxoft.hierarchy.data.SurnamesMale;

public class RandomNameAndSurnameFiller {

	public ArrayList<String[]> getNamesAndSurnames (int number){
		ArrayList<String[]> namesAndSurnames = new ArrayList<String[]>();
		NamesMale dataNamesMale = new NamesMale();
		NamesFemale dataNamesFemale = new NamesFemale();
		SurnamesMale dataSurnamesMale = new SurnamesMale();
		SurnamesFemale dataSurnamesFemale = new SurnamesFemale();
		ArrayList<String> nm = new ArrayList<String>(Arrays.asList(
				dataNamesMale.getNames()));
		ArrayList<String> nf = new ArrayList<String>(Arrays.asList(
				dataNamesFemale.getNames()));
		ArrayList<String> sm = new ArrayList<String>(Arrays.asList(
				dataSurnamesMale.getSurnames()));
		ArrayList<String> sf = new ArrayList<String>(Arrays.asList(
				dataSurnamesFemale.getSurnames()));
		Random rand = new Random();	
		for (int i=0; i<number; i++){
			String[] str = new String[2];
			switch (rand.nextInt(2)){
				case 0: {
					str[0] = nm.get(rand.nextInt(nm.size()));
					str[1] = sm.get(rand.nextInt(sm.size()));
					namesAndSurnames.add(str);
					break;
				}
				case 1:	{
					str[0] = nf.get(rand.nextInt(nf.size()));
					str[1] = sf.get(rand.nextInt(sf.size()));
					namesAndSurnames.add(str);
					break;
				}
			}
		}
		return namesAndSurnames;
	}
	
}
