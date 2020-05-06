// Andy Sokolowski    CS212    Project 2     TicTacToe.java

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class TicTacToe extends JFrame {

    //  window frame coordinates:
    private static final int LOCATION_X = 350;
    private static final int LOCATION_Y = 300;

    //  dimensions:
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 200;
    private static final int INFO_WIDTH = 150;

    //  this next move will be recorded as this player:
    private static char nextPlayer;
    private static boolean xPositions[][];  //boolean grid of X's
    private static boolean oPositions[][];  //boolean grid of O's
    private static int numberOfMoves;       //moves made this game
    private static Mode gameType;           //stores game mode

    private BoardPanel board;       //left panel
    private InfoPanel info;         //right panel

    //these next three allow the other panels to share references
    private JButton buttonList[];
    private JTextField infoBox;
    private JComboBox<String> modeBox;

    public TicTacToe() {
	//  game-start defaults:
	gameType = Mode.TWO_PLAYERS;
	nextPlayer = 'X';
	numberOfMoves = 0;

	//  create the matrices for progress-keeping
	xPositions = new boolean[3][3];
	oPositions = new boolean[3][3];

	//  set each matrix entirely to false
	xPositions = setToFalse(xPositions);
	oPositions = setToFalse(oPositions);

	//  set Frame attributes:
	setTitle("Tic Tac Toe");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(new BorderLayout());
	setLocation(LOCATION_X, LOCATION_Y);
	setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

	//  initialize each contained panel
	board = new BoardPanel();
	info = new InfoPanel();

	//  swap data references between the two panels:
	buttonList = board.getButtons();
	info.setButtons(buttonList);
	infoBox = info.getInfoBox();
	board.setInfoBox(infoBox);
	modeBox = info.getModeBox();
	board.setModeBox(modeBox);

	//  set the size of the right panel -- 
	info.setPreferredSize(new Dimension(INFO_WIDTH, FRAME_HEIGHT));
	
	//  add
	add(board, BorderLayout.CENTER);
	add(info, BorderLayout.EAST);

	//  put it all together and set to visible:
	pack();
	setVisible(true);
    }

    //  main function: construct main window, that's about it
    public static void main(String args[]) {

	new TicTacToe();
    }

    //  sets every element of a 3x3 boolean matrix to false.
    //  I don't remember the details of passing an array in java
    //  so I passed it back for simplicity's sake, not sure
    //  if that creates extra work for the computer or actually
    //  the only way to do it
    private static boolean[][] setToFalse(boolean matrix[][]) {
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++)
		matrix[i][j] = false;
	}

	return matrix;
    }

    //  restores defaults: intended for use by reset button
    public static void reset() {
	nextPlayer = 'X';
	numberOfMoves = 0;
	xPositions = setToFalse(xPositions);
	oPositions = setToFalse(oPositions);
    }

    //  returns X or O:
    public static String getNextPlayer() {
	return Character.toString(nextPlayer);
    }

    //  change player to X unless he already is X, then change to O
    public static void changePlayer() {
	if (nextPlayer == 'X')
	    nextPlayer = 'O';
	else
	    nextPlayer = 'X';
    }

    //  set a position on X's grid to true
    public static void addXPosition(int x, int y) {
	xPositions[x][y] = true;
    }

    //  set a position on Y's grid to true:
    public static void addOPosition(int x, int y) {
	oPositions[x][y] = true;
    }

    //  getter for X's past moves
    public static boolean[][] getXPositions() {
	return xPositions;
    }

    //  getter for O's past moves
    public static boolean[][] getOPositions() {
	return oPositions;
    }

    //  setter for numberOfMoves -- only permits incrementing 
    public static void incrementMoves() {
	numberOfMoves++;
    }

    //  setter for gameType variable
    public static void setGameMode(Mode m) {
	gameType = m;
    }

    //  getter for gameType variable
    public static Mode getGameMode() {
	return gameType;
    }

    //  this function probably gets used more often to make sure
    //  no one has one than to make sure someone has. either way,
    //  return true if either player has a winning condition --
    //  remember, the arrays are of type boolean
    public static boolean checkWin() {
	for (int i = 0; i < 3; i++) {
	    //  check if any rows are winners
	    if ((xPositions[i][0] && xPositions[i][1] &&
		     xPositions[i][2]) ||
	            (oPositions[i][0] && oPositions[i][1] &&
		     oPositions[i][2])) 
		return true;

	    //  check if any columns are winner
	    if ((xPositions[0][i] && xPositions[1][i] &&
	    	     xPositions[2][i]) ||
		    (oPositions[0][i] && oPositions[1][i] &&
		     oPositions[2][i]))
		return true;
	}
	
	//  check if forward diagonal is winner
	if ((xPositions[0][2] && xPositions[1][1] &&
		     xPositions[2][0]) ||
		    (oPositions[0][2] && oPositions[1][1] &&
		     oPositions[2][0]))
	    return true;

	// check if backward diagonal is winner
	if ((xPositions[0][0] && xPositions[1][1] &&
		     xPositions[2][2]) ||
		    (oPositions[0][0] && oPositions[1][1] &&
		     oPositions[2][2]))
	    return true;

	return false;
    }

    //  rather than iterating over buttons, figure it would take
    //  less effort to find a draw just by counting from 0 to 9:
    public static boolean isDraw() {
	if (numberOfMoves == 9)
	    return true;
	return false;
    }
}
