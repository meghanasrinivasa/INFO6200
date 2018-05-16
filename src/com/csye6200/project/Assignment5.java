package com.csye6200.project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;



/**
 * @author megha
 *
 */
public class Assignment5 extends BGApp {

	private static Logger log = Logger.getLogger(Assignment5.class.getName());
	static Thread t = null; 

	protected JPanel mainPanel = null;
	protected JPanel northPanel = null;
	protected static JButton startBtn = null;
	protected static JButton stopBtn = null;

	protected static JComboBox<String> comboboxRule;
	protected static JSlider slider = null;

	public static boolean stopValue=false;
	private static BGCanvas bgPanel =  new BGCanvas();

	private static JTextField txtGenerationNum=null;
	private JLabel labelGenerationNum=null;
	private JLabel labelGenerationNum2=null;
	private static int genCount;
	private static int ruleNum;
	private static int timeNum;
	private static int startFlag=0;

	/**
	 * Sample app constructor
	 */
	public Assignment5() {
		frame.setSize(1000,800); // initial Frame size
		frame.setTitle("Assignment5");
		frame.setResizable(true);
		menuMgr.createDefaultActions(); // Set up default menu items
		showUI(); // Cause the Swing Dispatch thread to display the JFrame
	}

	/**
	 * Create a main panel that will hold the bulk of our application display
	 */
	@Override
	public JPanel getMainPanel() {
		System.out.println("Entering main panel");
		mainPanel = new JPanel();
		bgPanel = new BGCanvas();

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.NORTH, getNorthPanel());

		mainPanel.add(BorderLayout.CENTER, bgPanel);

		return mainPanel;
	}

	/**
	 * Create a top panel that will hold control buttons
	 * @return
	 */
	public JPanel getNorthPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());


		comboboxRule = new JComboBox<>();
		slider= new JSlider(JSlider.HORIZONTAL,50,1000,500);


		startBtn = new JButton("Start");
		startBtn.addActionListener(this); // Allow the app to hear about button pushes
		northPanel.add(startBtn);

		stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
		stopBtn.addActionListener(this);
		northPanel.add(stopBtn);

		labelGenerationNum = new JLabel("Number of Generations");
		txtGenerationNum=new JTextField("10",4);
		northPanel.add(labelGenerationNum);
		northPanel.add(txtGenerationNum);	

		comboboxRule.addItem("Rule 1");
		comboboxRule.addItem("Rule 2");
		comboboxRule.addItem("Rule 3");
		northPanel.add(comboboxRule);

		labelGenerationNum2 = new JLabel("Select the speed");
		northPanel.add(labelGenerationNum2);
		northPanel.add(slider);
		return northPanel;
	}


	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
	}

	@Override
	public void windowClosing(WindowEvent e) {	
		log.info("Window closing");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		log.info("Window closed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {	
		log.info("Window deiconified");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {	
		log.info("Window deactivated");
	}




	public static void setStartFlag(int flag) {
		startFlag=flag;
	}

	public static int getGenCount() {
		return genCount;
	}



	public static int getRuleNum() {
		return ruleNum;
	}

	public static int getTimerNum() {
		return timeNum;
	}

	public static boolean getStopValue() {
		return stopValue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startBtn) {
			stopValue=false;
			startFlag++;
			genCount=Integer.parseInt(txtGenerationNum.getText());
			ruleNum = comboboxRule.getSelectedIndex();
			timeNum=  slider.getValue();
			BGGenerationSet genSet = new BGGenerationSet();
			genSet.addObserver(bgPanel);			
			System.out.println("Rule number is "+ruleNum);
			if(startFlag==1) {
				t = new Thread(genSet);
			}
			t.start();		
		}
		else if (e.getSource() == stopBtn) {
			stopValue=true;
			startFlag=0;
			System.out.println("Stop button pressed");
		}
		
	}

	/**
	 * Sample Wolf application starting point
	 * @param args
	 */
	public static void main(String[] args) {
		Assignment5 wapp = new Assignment5();
		log.info("Assignment5 started");
	}

}

