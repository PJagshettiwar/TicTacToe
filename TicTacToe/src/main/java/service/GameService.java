package service;

import com.example.model.Board;
import com.example.model.GameStatus;
import com.example.model.Player;
import com.example.model.Symbol;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameService {

	Player previousPlayer;
	Board board;
	Integer noOfMoves = 0;
	int boardSize = 0;

	public GameService(Board board) {
		this.board = board;
		boardSize = this.board.getBoard().length;
	}
	
	

	public GameStatus playGame(Player player, int x, int y) {
		try {
			if (isPlayerValid(player)) {
				if (isValidMove(x, y)) {
					board.getBoard()[x][y] = player.getSymbol();
					if (isWinningMove(x, y)) {
						System.out.println("Player won: " + player.getName());
						return GameStatus.WON;
					}
					noOfMoves++;
					if (noOfMoves >= boardSize * boardSize) {
						System.out.println("Match Draw");
						return GameStatus.DRAWN;
					}
				}else {
					return GameStatus.INVALIDMOVE;
				}
				previousPlayer = player;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return GameStatus.PLAYING;
	}

	private boolean isWinningMove(int x, int y) {

		if ((x == y) || (x + y == board.getBoard().length-1)) {
			if (diagonal1(board.getBoard()[x][y]) || diagonal2(board.getBoard()[x][y]))
				return true;

		}

		if (horizontal(board.getBoard()[x][y], x, y) || vertical(board.getBoard()[x][y], x, y))
			return true;

		return false;
	}

	private boolean diagonal1(Symbol symbol) {
		for (int i = 0; i < board.getBoard().length; i++) {
			if (board.getBoard()[i][i] != symbol) {
				return false;
			}
		}
		return true;
	}

	private boolean diagonal2(Symbol symbol) {
		int size = board.getBoard().length;
		for (int i = 0; i < size; i++) {
			if (board.getBoard()[size - 1 - i][i] != symbol) {
				return false;
			}
		}
		return true;
	}

	private boolean horizontal(Symbol symbol, int x, int y) {
		int size = board.getBoard().length;
		for (int i = 0; i < size; i++) {
			if (board.getBoard()[x][i] != symbol) {
				return false;
			}
		}
		return true;
	}

	private boolean vertical(Symbol symbol, int x, int y) {
		int size = board.getBoard().length;
		for (int i = 0; i < size; i++) {
			if (board.getBoard()[i][y] != symbol) {
				return false;
			}
		}
		return true;
	}

	private boolean isValidMove(int x, int y) {
		int size = board.getBoard().length;

		if (x < 0 || x >= size || y < 0 || y >= size)
			return false;

		if (board.getBoard()[x][y] == null)
			return true;

		return false;
	}

	private boolean isPlayerValid(Player player) throws Exception {
		if (previousPlayer == null)
			previousPlayer = player;
		else if (previousPlayer.equals(player))
			throw new Exception("Same player");
		return true;
	}

	public void printBoard() {
		Symbol[][] input = board.getBoard();
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (input[i][j] == null)
					System.out.print("|" + " ");
				else {
					System.out.print("|" + input[i][j]);
				}
			}
			System.out.print("|");
			System.out.println();
		}
	}
	

}
