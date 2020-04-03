package com.jobsity.tenpinbowling.serviceImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.ScoreTO;
import com.jobsity.tenpinbowling.util.ConsoleOutputCapturer;

public class InputValidationService extends BowlingImplTest {

	@Test
	 public void showErrorWhenFrameIsInvalid() {
		
		ConsoleOutputCapturer consoleOutputCapturer = new ConsoleOutputCapturer();
		
		consoleOutputCapturer.start();
		
		FrameTO frame = new FrameTO(0);
		
		List<ScoreTO> scores = new ArrayList<ScoreTO>();
		
		ScoreTO score = new ScoreTO("9",0,false,true);
		scores.add(score);
		frame.setNumber(1);
		frame.setPoint(10);
		frame.setResult(0);
		frame.setFrameScore(0);
		
		frame.setScore(scores);		
		
		inputValidationService.isValidFrame(frame);
		
		String logger = consoleOutputCapturer.stop();
		
		Assert.assertThat(logger, CoreMatchers.containsString("is not strike but have just one throw"));
				
		
		
	 }
	
	@Test
	 public void returnTrueWhenFrameIsInvalid() {
		
		FrameTO frame = new FrameTO(0);
		
		List<ScoreTO> scores = new ArrayList<ScoreTO>();
		
		ScoreTO score = new ScoreTO("10",0,false,true);
		scores.add(score);
		frame.setNumber(1);
		frame.setPoint(10);
		frame.setResult(0);
		frame.setFrameScore(0);
		
		frame.setScore(scores);
		
		
		assertTrue(inputValidationService.isValidFrame(frame));
		
		
		
	 }
	
	
	
	@Test
	public void returnTrueWhenIsInvalidScore() {
		
		ScoreTO score = new ScoreTO("F",0,false,true);
		
		inputValidationService.isValidScore(score);
		
		assertTrue(inputValidationService.isValidScore(score));
		
	}
	
	
	@Test
	public void returnFalseWhenIsInvalidScore() {
		
		ScoreTO score = new ScoreTO("?",0,false,true);
		
		inputValidationService.isValidScore(score);
		
		assertFalse(inputValidationService.isValidScore(score));
		
	}

	
	
	
	

	
	
}
