package com.csye6200.project;

import java.util.ArrayList;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * @author megha
 *
 */
public class BGGenerationSet extends Observable implements Runnable {

	private ArrayList<BGGeneration> genList = new ArrayList<BGGeneration>();
	private int maxGen = Assignment5.getGenCount(); // What is the current limit to the number of generations that we'll construct

	private boolean done = false; // Is this simulation generation run finished?
	private boolean paused = false; 
	private BGGeneration lastGen = null;

	/**
	 * A simple time series constructor that creates an initial generation
	 */
	public BGGenerationSet() {
		BGGeneration gen = new BGGeneration();
		genList.add(lastGen); // Add it to our list
		lastGen = gen; // Save a local copy to allow our run method to know we're ready	
	}


	/**
	 * A simulation run loop - Having a Thread to run this would be nice
	 * Use 'pause' to control execution (i.e. either do work and nap, or just sleep
	 * Use 'done' to stop working permanently
	 */
	@Override
	public void run() {
		System.out.println("BGGenerationSet - running");

		//if(Assignment5.getStopValue()== false) {

		while (!done) { // Loop infinitely, or until we are 'done'
			
			if(!Assignment5.getStopValue()) {
				if (lastGen == null) paused = true; // It we haven't created a generation, just wait for one to be created

				if (!paused)	 {
					BGGeneration nxtGen = lastGen.createNextGen();
					genList.add(nxtGen);
					lastGen = nxtGen;
					setChanged();
					notifyObservers(nxtGen);
				}

				try {
					Thread.sleep(Assignment5.getTimerNum());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (genList.size() >= maxGen) 
				{ 
					done = true;
					Assignment5.setStartFlag(0);
				}
			}
			
			
		}
	}
}
