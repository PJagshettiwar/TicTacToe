package com.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class Board {
	Symbol[][]  board;
	
	public Board(int size){
		this.board = new Symbol[size][size];
	}
	
	/*
	 * public void initialiseBoard() { Arrays.stream(board).forEach(arr ->
	 * Arrays.fill(arr, -1)); }
	 */
	
}
