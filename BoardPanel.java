// Andy Sokolowski  CS212     Project 2    BoardPanel.java

import java.util.Random;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BoardPanel extends JPanel implements ActionListener {

    //  size of the font on the buttons:
    private static final int FONT_SIZE = 30;

    private Random randGen;   //pseudorandom generator
    private Font buttonFont;  //font variable
    private JButton btn00, btn01, btn02, //9 buttons for 9 spaces
	            btn10, btn11, btn12,
		    btn20, btn21, btn22;

    private JTextField infoBox;  //shows whose turn it is on 2-player
    private JComboBox<String> modeBox;  //can choose mode

    public BoardPanel() {
	//  use system time for seed
	randGen = new Random(System.currentTimeMillis());
	//  bold font, change size
	buttonFont = new Font(Font.MONOSPACED, Font.BOLD, FONT_SIZE);

	//  3 x 3 grid with 4pt gaps (h and v) between
	setLayout(new GridLayout(3, 3, 4, 4));

	//  construct the buttons:
	btn00 = new JButton();
	btn01 = new JButton();
	btn02 = new JButton();
	btn10 = new JButton();
	btn11 = new JButton();
	btn12 = new JButton();
	btn20 = new JButton();
	btn21 = new JButton();
	btn22 = new JButton();

	//  on my computer the button text is a very light gray
	//  on white and is difficult to see, so I changed the
	//  background to black. setForeground() doesn't work
	//  on JButtons on my computer I figure it might on
	//  someone else's so I made it white for contrast
	//  (still shows as light gray on my laptop):
	btn00.setFont(buttonFont);
	btn00.setForeground(Color.WHITE);
	btn00.setBackground(Color.BLACK);
	btn01.setFont(buttonFont);
	btn01.setForeground(Color.WHITE);
	btn01.setBackground(Color.BLACK);
	btn02.setFont(buttonFont);
	btn02.setForeground(Color.WHITE);
	btn02.setBackground(Color.BLACK);
	btn10.setFont(buttonFont);
	btn10.setForeground(Color.WHITE);
	btn10.setBackground(Color.BLACK);
	btn11.setFont(buttonFont);
	btn11.setForeground(Color.WHITE);
	btn11.setBackground(Color.BLACK);
	btn12.setFont(buttonFont);
	btn12.setForeground(Color.WHITE);
	btn12.setBackground(Color.BLACK);
	btn20.setFont(buttonFont);
	btn20.setForeground(Color.WHITE);
	btn20.setBackground(Color.BLACK);
	btn21.setFont(buttonFont);
	btn21.setForeground(Color.WHITE);
	btn21.setBackground(Color.BLACK);
	btn22.setFont(buttonFont);
	btn22.setForeground(Color.WHITE);
	btn22.setBackground(Color.BLACK);

	//  add listeners to each button
	btn00.addActionListener(this);
	btn01.addActionListener(this);
	btn02.addActionListener(this);
	btn10.addActionListener(this);
	btn11.addActionListener(this);
	btn12.addActionListener(this);
	btn20.addActionListener(this);
	btn21.addActionListener(this);
	btn22.addActionListener(this);

	//  add the buttons to this panel
	add(btn00);
	add(btn01);
	add(btn02);
	add(btn10);
	add(btn11);
	add(btn12);
	add(btn20);
	add(btn21);
	add(btn22);
    }

    //  creates a list of buttons.
    //  this makes it easy to iterate through them.
    //  also makes it easy pass all 9 buttons with one
    //  variable
    public JButton[] getButtons() {
	JButton list[] = new JButton[9];

	list[0] = btn00;
	list[1] = btn01;
	list[2] = btn02;
	list[3] = btn10;
	list[4] = btn11;
	list[5] = btn12;
	list[6] = btn20;
	list[7] = btn21;
	list[8] = btn22;

	return list;
    }

    //  sets reference to the infoBox in the InfoPanel
    public void setInfoBox(JTextField infoBox) {
	this.infoBox = infoBox;
    }

    //  sets reference to the modeBox in the InfoPanel
    public void setModeBox(JComboBox<String> modeBox) {
	this.modeBox = modeBox;
    }

    //  this next method reacts to clicks on the buttons
    //  contained by this panel -- this method is sort of the
    //  "trunk" of the program, I think it has more lines
    //  of code than the rest of the program:
    public void actionPerformed(ActionEvent e) {
	//  player variable determines whether an X or O is
	//  printed on a button when clicked
	String player = TicTacToe.getNextPlayer();

	//  if button at (0, 0) is clicked:
	if (e.getSource() == btn00) {
	    //  set text to which player
	    btn00.setText(player);
	    
	    //  adds the position to the appropriate table,
	    //  these two tables keep track of the moves
	    //  independently of the GUI
	    if (player.equals("X"))
		TicTacToe.addXPosition(0, 0);
	    else
		TicTacToe.addOPosition(0,0);

	    btn00.setEnabled(false);     //disable button
	    TicTacToe.incrementMoves();  //draw is declared at 9 moves

	    //  Check if this move resulted in a win for a player:
	    if (TicTacToe.checkWin()) { // if win:
		//  Separate messages depending on mode:
		if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS)
		    JOptionPane.showMessageDialog(null,
			    player + " is the winner!");
		else //  in 1-player mode, computer moves are handled
		     //  by a different methods
		   JOptionPane.showMessageDialog(null,
			    "You won!");
		disableAll();  //disable 
	    } else if (TicTacToe.isDraw()) //if this game has resulted
		    			   //in a draw, say so:
		JOptionPane.showMessageDialog(null, "Draw!");
	    // else continue

	    //  change to "O" if already "X" and vice-versa
	    TicTacToe.changePlayer();

	//  repeat the above code for the rest of the buttons:
	} else if (e.getSource() == btn01) {
	    btn01.setText(player);

	    if (player.equals("X"))
		TicTacToe.addXPosition(0, 1);
	    else
		TicTacToe.addOPosition(0, 1);

	    btn01.setEnabled(false);
	    TicTacToe.incrementMoves();

	    if (TicTacToe.checkWin()) {
		if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS)
		    JOptionPane.showMessageDialog(null,
			    player + " is the winner!");
		else
		   JOptionPane.showMessageDialog(null,
			    "You won!");
		disableAll();
	    } else if (TicTacToe.isDraw())
		JOptionPane.showMessageDialog(null, "Draw!");

	    TicTacToe.changePlayer();

	} else if (e.getSource() == btn02) {
	    btn02.setText(player);

	    if (player.equals("X"))
		TicTacToe.addXPosition(0, 2);
	    else
		TicTacToe.addOPosition(0, 2);

	    btn02.setEnabled(false);
	    TicTacToe.incrementMoves();

	    if (TicTacToe.checkWin()) {
		if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS)
		    JOptionPane.showMessageDialog(null,
			    player + " is the winner!");
		else
		   JOptionPane.showMessageDialog(null,
			    "You won!");
		disableAll();
	    } else if (TicTacToe.isDraw())
		JOptionPane.showMessageDialog(null, "Draw!");

	    TicTacToe.changePlayer();

	} else if (e.getSource() == btn10) {
	    btn10.setText(player);

	    if (player.equals("X"))
		TicTacToe.addXPosition(1, 0);
	    else
		TicTacToe.addOPosition(1, 0);

	    btn10.setEnabled(false);
	    TicTacToe.incrementMoves();

	    if (TicTacToe.checkWin()) {
		if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS)
		    JOptionPane.showMessageDialog(null,
			    player + " is the winner!");
		else
		   JOptionPane.showMessageDialog(null,
			    "You won!");
    		disableAll();
	    } else if (TicTacToe.isDraw())
		JOptionPane.showMessageDialog(null, "Draw!");

	    TicTacToe.changePlayer();

	} else if (e.getSource() == btn11) {
	    btn11.setText(player);

	    if (player.equals("X"))
	   	TicTacToe.addXPosition(1, 1);
	    else
		TicTacToe.addOPosition(1, 1);

	    btn11.setEnabled(false);
	    TicTacToe.incrementMoves();

	    if (TicTacToe.checkWin()) {
		if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS)
		    JOptionPane.showMessageDialog(null,
			    player + " is the winner!");
		else
		   JOptionPane.showMessageDialog(null,
			    "You won!");
		disableAll();
	    } else if (TicTacToe.isDraw())
		JOptionPane.showMessageDialog(null, "Draw!");
    
	    TicTacToe.changePlayer();

	} else if (e.getSource() == btn12) {
	    btn12.setText(player);

	    if (player.equals("X"))
		TicTacToe.addXPosition(1, 2);
	    else
		TicTacToe.addOPosition(1, 2);

	    btn12.setEnabled(false);
	    TicTacToe.incrementMoves();
    
	    if (TicTacToe.checkWin()) {
		if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS)
		    JOptionPane.showMessageDialog(null,
			    player + " is the winner!");
		else
		   JOptionPane.showMessageDialog(null,
			    "You won!");
		disableAll();
	    } else if (TicTacToe.isDraw())
		JOptionPane.showMessageDialog(null, "Draw!");
    
	    TicTacToe.changePlayer();

	} else if (e.getSource() == btn20) {
	    btn20.setText(player);

	    if (player.equals("X"))
		TicTacToe.addXPosition(2, 0);
	    else
		TicTacToe.addOPosition(2, 0);

	    btn20.setEnabled(false);
	    TicTacToe.incrementMoves();

	    if (TicTacToe.checkWin()) {
		if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS)
		    JOptionPane.showMessageDialog(null,
			    player + " is the winner!");
		else
		   JOptionPane.showMessageDialog(null,
			    "You won!");
	        disableAll();
	    } else if (TicTacToe.isDraw())
		JOptionPane.showMessageDialog(null, "Draw!");

	    TicTacToe.changePlayer();

	} else if (e.getSource() == btn21) {
	    btn21.setText(player);

	    if (player.equals("X"))
		TicTacToe.addXPosition(2, 1);
	    else
		TicTacToe.addOPosition(2, 1);

	    btn21.setEnabled(false);
	    TicTacToe.incrementMoves();

	    if (TicTacToe.checkWin()) {
		if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS)
		    JOptionPane.showMessageDialog(null,
			    player + " is the winner!");
		else
		   JOptionPane.showMessageDialog(null,
			    "You won!");
		disableAll();
	    } else if (TicTacToe.isDraw())
		JOptionPane.showMessageDialog(null, "Draw!");
    
	    TicTacToe.changePlayer();

	} else if (e.getSource() == btn22) {
	    btn22.setText(player);
	    
	    if (player.equals("X"))
		TicTacToe.addXPosition(2, 2);
	    else
		TicTacToe.addOPosition(2, 2);

	    btn22.setEnabled(false);
	    TicTacToe.incrementMoves();

	    if (TicTacToe.checkWin()) {
		if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS)
		    JOptionPane.showMessageDialog(null,
			    player + " is the winner!");
		else
		   JOptionPane.showMessageDialog(null,
			    "You won!");
    		    disableAll();
	    } else if (TicTacToe.isDraw())
		JOptionPane.showMessageDialog(null, "Draw!");

	    TicTacToe.changePlayer();
	}
	/* END REPETITIVE CODE */

	//  if 2-player mode is set (and the game is not over)
	//  change the infoBox text to show it is the next
	//  player's turn
	if (TicTacToe.getGameMode() == Mode.TWO_PLAYERS &&
		! TicTacToe.checkWin() && ! TicTacToe.isDraw())
	    infoBox.setText(TicTacToe.getNextPlayer() + "'s turn.");

	//  if 1-player against easy computer opponent is set,
	//  play computer turn and increment the move count:
	if (TicTacToe.getGameMode() == Mode.ONE_PLAYER_EASY &&
		! TicTacToe.checkWin() && ! TicTacToe.isDraw()) {
	    computerTurn(Mode.ONE_PLAYER_EASY);
	    TicTacToe.incrementMoves();
	}
	//  same against hard opponent, except different parameter
	//  value
	if (TicTacToe.getGameMode() == Mode.ONE_PLAYER_HARD &&
		! TicTacToe.checkWin() && ! TicTacToe.isDraw()) {
	    computerTurn(Mode.ONE_PLAYER_HARD);
	    TicTacToe.incrementMoves();
	}

	//  if game is over, prompt user to press the RESET button
	if (TicTacToe.checkWin() || TicTacToe.isDraw())
	    infoBox.setText("Press RESET.");

	//  should already be false except on the first turn of any
	//  game:
	modeBox.setEnabled(false);
    }

    //  computer makes a turn.  if mode is EASY, random numbers
    //  are chosen until a disabled button is chosen. if mode is
    //  HARD, do the same thing but call the hardMode function
    //  instead of the random number generator directly
    private void computerTurn(Mode m) {
	JButton[] list = getButtons();
	boolean invalid = true;
	int index;

	if (m == Mode.ONE_PLAYER_EASY) {
	    do {
		index = randGen.nextInt(9);
		if (list[index].isEnabled()) {
		    list[index].setText("O");
		    list[index].setEnabled(false);
		    computerPush(index);
		    invalid = false;
		} else if (TicTacToe.checkWin())
		    invalid = false;
	    } while (invalid);
	} else if ( m == Mode.ONE_PLAYER_HARD) {
	    index = hardMode(list);
	    list[index].setText("O");
	    list[index].setEnabled(false);
	    computerPush(index);
	} else {
	    JOptionPane.showMessageDialog(null, "Fatal error. " +
		    "Quitting program.");
	    System.exit(1);
	}
	
	if (TicTacToe.checkWin()) {
	    JOptionPane.showMessageDialog(null, "You lost!");
	    disableAll();
	}
	TicTacToe.changePlayer();
    }

    //  the "brains" behind hard mode. the homework sheet indicates
    //  that this could be done with recursion, but I wanted to make
    //  a game that my 5 year old could beat with dumb luck.
    //
    //  My strategy: first the computer tries to choose the center.
    //
    //  If unavailable, it looks for unenabled buttons from top to
    //  bottom, left to right. If one is found, it checks if there
    //  are any winning opportunities. When one is found it either
    //  blocks or wins depending on the situation.
    //
    //  If no "smart" moves are found, then it moves onto "dumb"
    //  moves -- one of the 9 buttons is randomly drawn four times
    //  looking for a corner. If no available corners are drawn,
    //  then random numbers are drawn and the first that is open
    //  is accepted, whether an edge or corner.
    private int hardMode(JButton list[]) {
	boolean xPositions[][] = TicTacToe.getXPositions();
	boolean oPositions[][] = TicTacToe.getOPositions();
	boolean invalid = true;
	int index;

        //  if center is not chosen, choose it
	if (list[4].isEnabled())
	    return 4;

	//  defend position 0:
	if ((xPositions[0][1] && xPositions[0][2]) ||
	         (oPositions[0][1] && oPositions[0][2]) ||
		 (xPositions[1][1] && xPositions[2][2]) ||
		 (oPositions[1][1] && oPositions[2][2]) ||
	  	 (xPositions[1][0] && xPositions[2][0]) ||
		 (oPositions[1][0] && oPositions[2][0])) {
	     
	    if (list[0].isEnabled())
		return 0;
	}

	//  defend position 1:
	if ((xPositions[0][0] && xPositions[0][2]) ||
		(oPositions[0][0] && oPositions[0][2]) ||
       	        (xPositions[1][1] && xPositions[2][1]) ||
		(oPositions[1][1] && oPositions[2][1]))	{
	    
	    if (list[1].isEnabled())
		return 1;
	}

	//  defend positions 2:
	if ((xPositions[0][0] && xPositions[0][1]) ||
		(oPositions[0][0] && oPositions[0][1]) ||
	        (xPositions[2][0] && xPositions[1][1]) ||
		(oPositions[2][0] && oPositions[1][1]) ||
	        (xPositions[1][2] && xPositions[2][2]) ||
	        (oPositions[1][2] && oPositions[2][2]))	{
	    
	    if (list[2].isEnabled())
		return 2;
	}

	//  defend position 3:
	if ((xPositions[0][0] && xPositions[2][0]) ||
		(oPositions[0][0] && oPositions[2][0]) ||
       	        (xPositions[1][1] && xPositions[1][2]) ||
		(oPositions[1][1] && oPositions[1][2]))	{
	    
	    if (list[3].isEnabled())
		return 3;
	}

	//  defend position 4:
	if ((xPositions[0][1] && xPositions[2][1]) ||
		(oPositions[0][1] && oPositions[2][1]) ||
	        (xPositions[0][0] && xPositions[2][2]) ||
		(oPositions[0][0] && oPositions[2][2]) ||
	        (xPositions[1][0] && xPositions[1][2]) ||
	        (oPositions[1][0] && oPositions[1][2]))	{
	    
	    if (list[4].isEnabled())
		return 4;
	}

	//  defend positon 5:
	if ((xPositions[0][2] && xPositions[2][2]) ||
		(oPositions[0][2] && oPositions[2][2]) ||
       	        (xPositions[1][0] && xPositions[1][1]) ||
		(oPositions[1][0] && oPositions[1][1]))	{
	    
	    if (list[5].isEnabled())
		return 5;
	}


	//  defend position 6:
	if ((xPositions[0][0] && xPositions[1][0]) ||
		(oPositions[0][0] && oPositions[1][0]) ||
	        (xPositions[1][1] && xPositions[0][2]) ||
		(oPositions[1][1] && oPositions[0][2]) ||
	        (xPositions[2][1] && xPositions[2][2]) ||
	        (oPositions[2][1] && oPositions[2][2]))	{
	    
	    if (list[6].isEnabled())
		return 6;
	}


	//  defend position 7:
	if ((xPositions[0][1] && xPositions[1][1]) ||
		(oPositions[0][1] && oPositions[1][1]) ||
       	        (xPositions[2][0] && xPositions[2][2]) ||
		(oPositions[2][0] && oPositions[2][2]))	{
	    
	    if (list[7].isEnabled())
		return 7;
	}

	//  defend position 8:
	if ((xPositions[2][0] && xPositions[2][1]) ||
		(oPositions[2][0] && oPositions[2][1]) ||
	        (xPositions[0][0] && xPositions[1][1]) ||
		(oPositions[0][0] && oPositions[1][1]) ||
	        (xPositions[0][2] && xPositions[1][2]) ||
	        (oPositions[0][2] && oPositions[1][2]))	{
	    
	    if (list[8].isEnabled())
		return 8;
	}

	//  random bias toward corners: four attempts to pick
	//  an empty corner, otherwise move on
	for (int i = 0; i < 4; i++) {
	    index = randGen.nextInt(9);
	    switch (index) {
		case 0:
		    if (list[0].isEnabled())
			return 0;
		break;

		case 2:
		    if (list[2].isEnabled())
			return 2;
		break;

		case 6:
		    if (list[6].isEnabled())
			return 6;
		break;

		case 8:
		    if (list[8].isEnabled())
			return 8;

		default:
		    //  do nothing
		break;
	    }
	}

	//  otherwise choose randoms until hitting an open box
	do {
	    index = randGen.nextInt(9);
	    if (list[index].isEnabled()) {
		return index;
	    }
	} while (true);
    }

    //  accepts the buttonList index number and
    //  records the computer's move by assuming the
    //  buttons are numbered left to right, top to
    //  bottom
    private void computerPush(int index) {
	switch (index) {
	    case 0:
		TicTacToe.addOPosition(0, 0);
	    break;
	    
	    case 1:
	    	TicTacToe.addOPosition(0, 1);
	    break;

	    case 2:
	        TicTacToe.addOPosition(0, 2);
	    break;

	    case 3:
	        TicTacToe.addOPosition(1, 0);
	    break;

	    case 4:
	        TicTacToe.addOPosition(1, 1);
	    break;

	    case 5:
	        TicTacToe.addOPosition(1, 2);
	    break;

	    case 6:
	        TicTacToe.addOPosition(2, 0);
	    break;

	    case 7:
	        TicTacToe.addOPosition(2, 1);
	    break;

	    case 8:
	        TicTacToe.addOPosition(2, 2);
	    break;

	    //  should be unreachable in a working program:
	    default:
	        JOptionPane.showMessageDialog(null, "Fatal error. " +
				"Quitting program.");
		System.exit(1);
	    break;
	}
    }

    //  disable all 9 buttons:
    private void disableAll() {
	btn00.setEnabled(false);
	btn01.setEnabled(false);
	btn02.setEnabled(false);
	btn10.setEnabled(false);
	btn11.setEnabled(false);
	btn12.setEnabled(false);
	btn20.setEnabled(false);
	btn21.setEnabled(false);
	btn22.setEnabled(false);
    }
}
