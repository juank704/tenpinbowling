package com.jobsity.tenpinbowling.util;

import com.jobsity.tenpinbowling.entity.ScoreTO;


/**
 * Utility for getting score representation
 * @author Juan_
 *
 */
public class ScoreUtil {

	/**
	  * @param score to convert
	  * @return Integer, numeric conversion of current string score.
	  */
	  public static int getScoreInt(String score) {
	    try {
	      if (ScoreTO.FAULT_SCORE.equals(score)) {
	        return 0;
	      }
	      
	      return Integer.parseInt(score);
	    } catch(Exception e) {
	      return ScoreTO.INVALID_SCORE;
	    }
	  }

	  /**
	  * It determines if is a frame fault score
	  */
	  public static boolean isFaultScore(String score) {
	    return ScoreTO.FAULT_SCORE.equals(score);
	  }
	
}
