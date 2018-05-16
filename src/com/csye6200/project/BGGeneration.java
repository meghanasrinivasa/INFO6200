
package com.csye6200.project;

import java.util.ArrayList;

public class BGGeneration {

	private BGRule rule = null;
	private BGStem rootStem = null;
	
	protected ArrayList<BGStem> stemList = new ArrayList<BGStem>();
	
	ArrayList<BGRule> rulesList= new ArrayList<>();
	private int ruleNum;
	
	
	/**
	 * Standard Constructor
	 */
	public BGGeneration() {
		ruleNum=Assignment5.getRuleNum();
		rulesList.add(0,new BGRule(1.2, 2, 60.0));
		rulesList.add(1,new BGRule(1.2, 3, 10.0));
		rulesList.add(2,new BGRule(1.2, 4, 30.0));
		rule = rulesList.get(ruleNum); // Let's create a stock rule
		
		Position start = new Position(0.0, 0.0);
		double stLength = 60.0;
		double stAngle = 0.0 * Math.PI / 180.0;
		
		rootStem = add(new BGStem(start, stLength, stAngle)); // Add the first stem to our generation
	}
	
	/**
	 * Copy constructor - build a copy of an existing generation
	 * @param cpyGen
	 */
	public BGGeneration(BGGeneration cpyGen) {
		rule = cpyGen.rule; // Let's just re-use the same rule instance
		for (BGStem stm : cpyGen.stemList) {
			add(stm.clone());
		}		
		rootStem = stemList.get(0); // Force the root stem to be the first cloned copy in our list of stems
	}
	
	/**
	 * Get the root stem, which is usually the fist stem in our arrayList
	 * @return
	 */
	public BGStem getRootStem() {
		return rootStem;
	}

	/**
	 * Allow external classes to get access to our list of BGStems
	 * @return the stem array list
	 */
	public ArrayList<BGStem> getStemList() {
		return stemList;
	}

	/**
	 * Add a new stem by recording its index position and adding it to our array list
	 * @param stem
	 * @return
	 */
	public BGStem add(BGStem stem) {
		stem.myIndex= stemList.size(); // Record the current position in the list
		stemList.add(stem);
		return stem;
	}
	
	/**
	 * Create a new generation based on the existing generation
	 * Use the Rule to create the next generation
	 */
	public BGGeneration createNextGen() {
		BGGeneration nxtGen = rule.getNextGeneration(this);
		return nxtGen;
	}
	
	
}
