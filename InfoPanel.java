//  Andy Sokolowski    CS212    Project 2    InfoPanel.java

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class InfoPanel extends JPanel implements ActionListener {

    //  if I'm going to compare Strings, I prefer to make them
    //  constants to reduce typing if I choose to change one
    //  later:
    private static final String MODE_1 = "2-player mode";
    private static final String MODE_2 = "1-player - easy";
    private static final String MODE_3 = "1-player - hard";
    //  array for initializing combobox:
    private static final String modeList[] = 
    	        { MODE_1, MODE_2, MODE_3 };

    //  containers:
    private JPanel info;
    private JPanel top;
    private JPanel bottom;
    private JTextField infoBox;
    private JButton reset;
    private JButton[] buttonList;
    private JComboBox<String> mode;

    public InfoPanel() {
	setLayout(new BorderLayout()); // set borderlayout

	info = new JPanel();     //  panel to hold infoBox
	top = new JPanel();      //  panel to hold info and
				 //  space it
	bottom = new JPanel();   //  panel to hold options
	infoBox = new JTextField();  //  displays status info
	reset = new JButton();       //  reset game at any time
	mode = new JComboBox<String>(modeList); // choose mode

	infoBox.setEditable(false);     //set to read-only
	infoBox.setText("X's turn.");   //default text on start

	info.setLayout(new BorderLayout());   //set infobox layout
	top.setLayout(new GridLayout(5, 1));  //set top layout
	bottom.setLayout(new BorderLayout()); //set bottom layout

	//  tried to pad the left and right a little:
	info.add(new JPanel(), BorderLayout.WEST);
	info.add(infoBox, BorderLayout.CENTER);
	info.add(new JPanel(), BorderLayout.EAST);

	reset.setText("Reset");        // button text
	reset.addActionListener(this); // add action listener to button
	mode.addActionListener(this);  // add listener to combobox

	//  pad top and bottom of the top panel so that the info
	//  panel is centered and 1/5th of the panel in height:
	top.add(new JPanel());
	top.add(new JPanel());
	top.add(info);
	top.add(new JPanel());
	top.add(new JPanel());

	//  add components to the bottom panel:
	bottom.add(reset, BorderLayout.NORTH);
	bottom.add(mode, BorderLayout.SOUTH);

	//  add top and bottom panels to this panel (InfoPanel):
	add(top, BorderLayout.CENTER);
	add(bottom, BorderLayout.SOUTH);
    }

    //  accepts access to array of buttons elsewhere in shared
    //  frame:
    public void setButtons(JButton buttonList[]) {
	this.buttonList = buttonList;
    }

    //  allows infoBox to be referenced from elsewhere:
    public JTextField getInfoBox() {
	return infoBox;
    }

    //  allows mode box to be referenced from elsewhere:
    public JComboBox<String> getModeBox() {
	return mode;
    }

    //  this method reacts to actions on the right half of
    //  the GUI:
    public void actionPerformed(ActionEvent e) {
	//  if reset button is pressed:
	if (e.getSource() == reset) {
	    TicTacToe.reset();  //  call the reset 
	    for (int i = 0; i < 9; i++) {
		buttonList[i].setEnabled(true);
		buttonList[i].setText(null);
	    }

	    //  Enable the mode box, which gets disabled
	    //  after the first player's turn:
	    mode.setEnabled(true);

	    //  set the infobox based on which mode is
	    //  currently selected:
	    if (mode.getSelectedItem().toString() == MODE_1)
		infoBox.setText("X's turn.");
	    else if (mode.getSelectedItem().toString() == MODE_2)
		infoBox.setText("Easy mode.");
	    else if (mode.getSelectedItem().toString() == MODE_3)
		infoBox.setText("Hard mode.");

	//  if and item in the mode box has been selected:
	} else if (e.getSource() == mode) {
	    //  for each mode, set the gameMode variable and
	    //  update the infoBox to indicate which mode is
	    //  being played (or with 2-player that X goes first):
	    if (mode.getSelectedItem().toString() == MODE_1) {
		TicTacToe.setGameMode(Mode.TWO_PLAYERS);
		infoBox.setText("X's turn.");
	    } else if (mode.getSelectedItem().toString() == MODE_2) {
		TicTacToe.setGameMode(Mode.ONE_PLAYER_EASY);
		infoBox.setText("Easy mode.");
	    } else if (mode.getSelectedItem().toString() == MODE_3) {
		TicTacToe.setGameMode(Mode.ONE_PLAYER_HARD);
		infoBox.setText("Hard mode.");
	    }
	}
    }
}
