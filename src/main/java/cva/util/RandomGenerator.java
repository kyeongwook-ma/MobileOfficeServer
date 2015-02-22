package cva.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGenerator {

	private List<Integer> randomNums;

	public RandomGenerator(int range) {
		randomNums = new ArrayList<Integer>();

		for(int i = 0; i < range; ++i) {
			randomNums.add(range);
		}

		Collections.shuffle(randomNums);
	}


	public int getRandom() {

		if(randomNums.size() != 0)
			return randomNums.remove(1);
		else
			return 0;
	}
	
	

}
