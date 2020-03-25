package com.jobsity.tenpinbowling.service;

import java.util.List;
import java.util.Map;

/**
 * Service to Score by player
 * @author Juan_
 *
 */
public interface IScoreService {

	/**
	 * map the score by each bowler in a Map
	 * @param lines
	 * @return map -> (bowler, scores list)
	 */
	public Map<String,List<String>> parse(List<String> lines);

	/**
	 * 
	 * @param scores list
	 * @param bowler 
	 * @param score
	 * @return map -> (bowler, scores list)
	 */
	public Map<String,List<String>> addScore(Map<String,List<String>> scores, String bowler, String score);
	
	
}
