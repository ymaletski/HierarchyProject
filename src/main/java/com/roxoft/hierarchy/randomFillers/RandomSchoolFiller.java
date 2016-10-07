package com.roxoft.hierarchy.randomFillers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.roxoft.hierarchy.data.Schools;

public class RandomSchoolFiller {

	public Set<String> getNames (int number){
		Set<String> names = new TreeSet<String>();
		Schools dataSchoolsNames = new Schools();
		Set<String> sn = new HashSet<String>(Arrays.asList
				(dataSchoolsNames.getSchools()));								
		if(number <= sn.size()){
			Random rand = new Random();
			ArrayList<String> ar = new ArrayList<String>();
			ar.addAll(sn);
			while (names.size() < number)
				names.add(ar.get(rand.nextInt(sn.size())));
		}
		else
			names.addAll(sn);
		return names;
	}
	
}
