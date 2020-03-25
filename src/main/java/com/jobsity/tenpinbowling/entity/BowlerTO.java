package com.jobsity.tenpinbowling.entity;

/**
 * Model for player
 * @author Juan_
 *
 */
public class BowlerTO {

	//Player's name
	private String name;
	
	public BowlerTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
