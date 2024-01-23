package players;

import interfaces.IModel;
import interfaces.IPlayer;
import util.InputUtil;

/**
 * Implementing this player is a basic task.
 * See assignment instructions for what to do.
 * There are also helpful comments in this file (which you can remove).
 *
 * @author <YOUR UUN>
 */
public class HumanPlayer implements IPlayer
{
	// The constructor is called when the player is selected from the game menu.
	public HumanPlayer()
	{
		// You can leave this empty.
	}
	
	// This method is called when a new game is started or loaded.
	public void prepareForGameStart(IModel model, byte playerId)
	{
		// You can leave this empty.
	}
	
	// This method is called to ask the player to take their turn.
	// The move they choose should be returned from this method.
	public int chooseMove(){
		// Until you have implemented this player, it will always concede.
		System.out.println("Choose");
		int choose = InputUtil.readIntFromUser();
		if (choose == -1) {
			return IModel.CONCEDE_MOVE;
			// if statement not required however makes it more readable
		}
		return choose;
		// Remove the above return statement, ask the user to enter a move and return that instead.
		// The user should enter the index of the column into which they want to put their next piece.
		// They can also enter -1 if they really do want to concede.
	}
}
