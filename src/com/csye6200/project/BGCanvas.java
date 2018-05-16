package com.csye6200.project;

import java.util.ArrayList;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;



/**
 * @author megha
 *
 */

public class BGCanvas extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(BGCanvas.class.getName());
	private Color col = null;
	private long counter = 0L;
	private BGGeneration lastGen=null;


	/**
	 * CellAutCanvas constructor
	 */
	public BGCanvas() {
		//lastGen=new BGGeneration();
		col = Color.WHITE;
	}


	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {
		System.out.println("repaint");
		drawBG(g); // Our Added-on drawing
	}

	/**
	 * Draw the CA graphics panel
	 * @param g
	 */
	public void drawBG(Graphics g) {
		log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, size.width, size.height);

		g2d.setColor(Color.RED);
		g2d.drawString("Meghana Srinivasa", 10, 15);
		

		//For plotting and translating
		//g2d.drawString("size: "+genTest.size(),20, 20);
		g2d.translate(size.width/2, size.height);
		g2d.scale(1.0, -1.0);
		if(counter>1) {
		printTree(g2d);
		}
	}

	/**
	 * printTree() prints the tree calculating the indexes of the stem
	 * @param g2d
	 */
	public void printTree(Graphics2D g2d) {

		ArrayList<BGStem> genTest= lastGen.getStemList();
		//System.out.println(genTest.size());
		Color color = null;
		int i=0;	
		for(BGStem stem :genTest ) {	

			int redVal = validColor(i*5);
			int greenVal = validColor(255-i*5);
			int blueVal = validColor((i*5)-(i*2));
			col = new Color(redVal, greenVal, blueVal);
			i++;
			int startx =  (int) Math.round(stem.getStartPos().getX());
			int starty = (int) Math.round(stem.getStartPos().getY());
			int endx = (int) Math.round(stem.getTipPosition().getX());
			int endy = (int) Math.round(stem.getTipPosition().getY());
			super.paintComponents(g2d);
			g2d.setColor(col);
			g2d.drawLine(startx, starty, endx, endy);
		}

	}


	/*
	 * A local routine to ensure that the color value is in the 0 to 255 range.
	 */
	private int validColor(int colorVal) {
		if (colorVal > 255)
			colorVal = 255;
		if (colorVal < 0)
			colorVal = 0;
		return colorVal;
	}





	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
		this.lastGen = (BGGeneration)arg;
		repaint();
	}

}
