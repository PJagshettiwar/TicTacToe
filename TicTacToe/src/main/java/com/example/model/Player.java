package com.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class Player {
	String name;
	int id;
	Symbol symbol;
	
	public Player(String name, int id, Symbol symbol){
		this.name= name;
		this.id=id;
		this.symbol = symbol;
	}
	
	
}
