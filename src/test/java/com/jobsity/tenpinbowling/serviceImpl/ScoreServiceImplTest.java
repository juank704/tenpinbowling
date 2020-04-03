package com.jobsity.tenpinbowling.serviceImpl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.jobsity.tenpinbowling.util.ConsoleOutputCapturer;

/**
 * Test for Score Implementation
 * @author Juan_
 *
 */
public class ScoreServiceImplTest extends BowlingImplTest {
	
	 @Test
	 public void whenScoreIsNotParsable() {
	    
		 ConsoleOutputCapturer consoleOutputCapturer = new ConsoleOutputCapturer();
		 
		 List<String> lines = new ArrayList<String>();
		 lines.add("Juan 10");
		 lines.add("Carlos 10");
		 
		 consoleOutputCapturer.start();
	    	
		 scoreService.parse(lines);
	    	
	     String logger = consoleOutputCapturer.stop();
	    	
	     Assert.assertThat(logger, CoreMatchers.containsString("Can't read line"));
		     	
	 }
	

	 @Test
	 public void shouldAddScore() {
		 
		 Map<String, List<String>> currentScore = new LinkedHashMap<String, List<String>>();
		 Map<String, List<String>> scoreExpected = new LinkedHashMap<String, List<String>>();
		 
		 List<String> points = new ArrayList<String>();
		 points.add("10");
		 points.add("7");
		 
		 List<String> expectedPoints = new ArrayList<String>();
		 expectedPoints.add("10");
		 expectedPoints.add("7");
		 expectedPoints.add("9");
		 
		 currentScore.put("Juan", points);
	    	
		 Map<String, List<String>> scoreActual = scoreService.addScore(currentScore, "Juan", "9");
		 
		 scoreExpected.put("Juan", expectedPoints);
	    	
		 assertTrue(scoreExpected.equals(scoreActual));
		     	
	 }
	 
	 
	 @Test
	 public void shouldAddScoreAndNewPlayer() {
		 
		 Map<String, List<String>> currentScore = new LinkedHashMap<String, List<String>>();
		 Map<String, List<String>> scoreExpected = new LinkedHashMap<String, List<String>>();
		 
		 List<String> points = new ArrayList<String>();
		 points.add("10");
		 points.add("7");
		 
		 List<String> expectedPointsPlayer1 = new ArrayList<String>();
		 expectedPointsPlayer1.add("10");
		 expectedPointsPlayer1.add("7");
		 
		 List<String> expectedPointsPlayer2 = new ArrayList<String>();
		 expectedPointsPlayer2.add("9");
		 
		 currentScore.put("Juan", points);
	    	
		 Map<String, List<String>> scoreActual = scoreService.addScore(currentScore, "Carlos", "9");
		 
		 scoreExpected.put("Juan", expectedPointsPlayer1);
		 scoreExpected.put("Carlos", expectedPointsPlayer2);
	    	
		 assertTrue(scoreExpected.equals(scoreActual));
		     	
	 }
	 
	 
	 
	 
	 
}
