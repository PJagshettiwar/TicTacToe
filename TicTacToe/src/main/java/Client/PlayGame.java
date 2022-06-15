package Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.model.Board;
import com.example.model.GameStatus;
import com.example.model.History;
import com.example.model.Player;
import com.example.model.Symbol;

import service.GameService;

public class PlayGame {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Player player1 = new Player("Pankaj", 1, Symbol.X);
		Player player2 = new Player("Diplav", 2, Symbol.O);
		List<History> allHistory = new ArrayList<>();
		
		int boardSize = 3;
		GameService gameService;
		
		int x=0;
		int y=0;
		GameStatus gameStatus;
		Scanner scan = new Scanner(System.in);
		
		int noOfPlays = boardSize*boardSize;
		Player player = player1;
		while(true) {
			Board board = new Board(boardSize);
			gameService = new GameService(board);
			while(noOfPlays>0) {
				gameService.printBoard();
				System.out.println("Current player "+player);
				System.out.println("Enter x");
				x = scan.nextInt();
				System.out.println("Enter Y");
				y = scan.nextInt();
				gameStatus = gameService.playGame(player, x, y);
				if(gameStatus.equals(GameStatus.INVALIDMOVE)) continue;
				if(gameStatus.equals(GameStatus.WON) || gameStatus.equals(GameStatus.DRAWN)) {
					gameService.printBoard();
					History history = new History();
					history.setBoard(board);
					history.setWon(player);
					history.setLost(player.equals(player1)?player2:player1);
					allHistory.add(history);
					break;
				}
				if(player.equals(player1)) {
					player = player2;
				}else {
					player = player1;
				}
				noOfPlays--;
			}
			System.out.println("Do you want to play again, press 1/0");
			int input = scan.nextInt();
			if(input==1) continue;
			else break;
		}
		
		System.out.println("History"+allHistory);
	}
}
