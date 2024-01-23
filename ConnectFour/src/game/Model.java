package game;

import interfaces.IModel;
import util.GameSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the state of the game.
 * It has been partially implemented, but needs to be completed by you.
 *
 * @author <YOUR UUN>
 */
public class Model<length> implements IModel
{
	// A reference to the game settings from which you can retrieve the number
	// of rows and columns the board has and how long the win streak is.
	private GameSettings settings;
	private int[][] board; public boolean player; public boolean other;

	// The default constructor.
	public Model()
	{
		// You probably won't need this.
	}
	
	// A constructor that takes another instance of the same type as its parameter.
	// This is called a copy constructor.
	public Model(IModel model)
	{

	}
	
	// Called when a new game is started on an empty board.
	public void initNewGame(GameSettings settings)
	{
		this.settings = settings;
		this.board = new int[settings.nrCols][settings.nrRows];
		this.player = true;
		this.other = false;
		
		// This method still needs to be extended.
	}
	
	// Called when a game state should be loaded from the given file.
	public void initSavedGame(String fileName)
	{
//		int rows;
//		int columns;
//		int streak;
//		int player;
//
//		ArrayList<String> data = new ArrayList<String>();
//		try {
//			File myObj = new File("saves/" + fileName);
//			Scanner myReader = new Scanner (myObj);
//			while (myReader.hasNextLine()){
//				data.add(myReader.nextLine());
//			}
//			myReader.close();
//		}catch (FileNotFoundException e) {
//			System.out.println("An Error Occured.");
//			e.printStackTrace();
//		}
//	rows = Integer.parseInt(data.get(0));
//	columns = Integer.parseInt(data.get(1));
//	streak = Integer.parseInt(data.get(2));
//	player = Integer.parseInt(data.get(3));

//	this.board = new int[rows][columns];
//	for (int x = 0; x < rows; x++) {
//		for (int y = 0; y< columns; y++) {
//			char i = ((data.get(x+4)).charAt(y));
//			this.board[x][y] = i - '0';
//		}
//	}
//		if (player==1){
//		turnCount = 0;
//	}
//	else{
//		turnCount = 1;
//	}
//	this.settings = new GameSettings(rows,columns,streak);
	}
	
	// Returns whether or not the passed in move is valid at this time.
	public boolean isMoveValid(int move) {
		if (move == -1) {
			return true;
		}
		// Assuming all moves are valid.
		if (move < 0 | move > settings.nrCols - 1)
			return false;
		if (board[move][0] != 0) {
			return false;
		}
		return true;
	}
	
	// Actions the given move if it is valid. Otherwise, does nothing.
	public void makeMove(int move)
	{
		if(move == -1)
		{
			other =  true;
			return;
		}
		for (int x = (this.board[0].length-1);x>=0; x--){
			if(this.board[move][x] ==0)
			{
				this.board[move][x] =getActivePlayer();
				break;
			}
		}
		this.player = !(this.player);
	}
	
	// Returns one of the following codes to indicate the game's current status.
	// IModel.java in the "interfaces" package defines constants you can use for this.
	// 0 = Game in progress
	// 1 = Player 1 has won
	// 2 = Player 2 has won
	// 3 = Tie (board is full and there is no winner)
	public byte getGameStatus()
	{
		// Assuming the game is never ending.
		//System.out.println(horizontalCheck());
		//System.out.println(verticalCheck());
		//System.out.println(diagonalCheck());
		//System.out.println(diagonalCheck2());
		boolean isOnGoing = false;
		for(int x=0; x<this.settings.nrCols;x++){
			if(this.board[x][0]==0){
				isOnGoing = true;
				break;
			}
		}
		if(other){
			if(player){
				return IModel.GAME_STATUS_WIN_2;
			}
			return IModel.GAME_STATUS_WIN_1;
		}
		if(horizontalCheck() | verticalCheck() | diagonalCheck() | diagonalCheck2()){
			if(player){
				return IModel.GAME_STATUS_WIN_2;
			}
			return IModel.GAME_STATUS_WIN_1;
		}
		if(isOnGoing){
			return IModel.GAME_STATUS_ONGOING;
		}
		return IModel.GAME_STATUS_TIE;
	}
	private boolean horizontalCheck(){
		for (int x =0; x<= settings.nrCols- settings.minStreakLength; x++){
			for (int y = settings.nrRows-1; y>-1; y--){
				if(board[x][y]==0) {
					break;
				}
					boolean check = true;
					for(int z =0; z< settings.minStreakLength-1; z++){
						if(board[x+z][y]!=board[x+z+1][y]){
							check = false;
							break;
						}

				}
				if(check == true){
					return true;
				}
			}
		}
		return false;
	}
	private boolean verticalCheck(){
		for (int x =0; x< settings.nrCols; x++){
			for (int y = settings.nrRows- settings.minStreakLength; y>-1; y--){
				if(board[x][y]==0) {
					break;
				}
				boolean check = true;
				for(int z =0; z< settings.minStreakLength-1; z++){
					if(board[x][y+z]!=board[x][y+z+1]){
						check = false;
						break;
					}

				}
				if(check == true){
					return true;
				}
			}
		}
		return false;
	}
	private boolean diagonalCheck(){
		for (int x =0; x<= settings.nrCols- settings.minStreakLength; x++){
			for (int y = settings.nrRows- settings.minStreakLength; y>-1; y--){
				if(board[x][y]==0) {
					break;
				}
				boolean check = true;
				for(int z =0; z< settings.minStreakLength-1; z++){
					if(board[x+z][y+z]!=board[x+z+1][y+z+1]){
						check = false;
						break;
					}

				}
				if(check == true){
					return true;
				}
			}
		}
		return false;
	}
	private boolean diagonalCheck2(){
		for (int x = settings.nrCols- settings.minStreakLength;x< settings.nrCols; x++){
			for (int y = settings.nrRows- settings.minStreakLength; y>-1; y--){
				if(board[x][y]==0) {
					break;
				}
				boolean check = true;
				for(int z =0; z< settings.minStreakLength-1; z++){
					if(board[x-z][y+z]!=board[x-z-1][y+z+1]){
						check = false;
						break;
					}

				}
				if(check == true){
					return true;
				}
			}
		}
		return false;
	}
	// Returns the number of the player whose turn it is.
	public byte getActivePlayer()
	{
		// Assuming it is always the turn of player 1.
		if(player){
			return 1;
		}
		else{
			return 2;
		}
	}

	// Returns the owner of the piece in the given row and column on the board.
	// Return 1 or 2 for players 1 and 2 respectively or 0 for empty cells.
	public byte getPieceIn(int row, int column)
	{
		return (byte) this.board[column][row];
		//if(this.board[column][row] == 1){
		//	return 1;
		//} else if (this.board[column][row] == 2){
		//	return 2;
		//} else {
		//	return 0;
		//}
		// Assuming all cells are empty for now.
	}
	
	// Returns a reference to the game settings, from which you can retrieve the
	// number of rows and columns the board has and how long the win streak is.
	public GameSettings getGameSettings()
	{
		return settings;
	}
	
	// =========================================================================
	// ================================ HELPERS ================================
	// =========================================================================
	
	// You may find it useful to define some helper methods here.
	
}
