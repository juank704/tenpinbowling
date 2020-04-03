package com.jobsity.tenpinbowling.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.jobsity.tenpinbowling.util.ConsoleOutputCapturer;

/**
 * Testing Class for Line
 * @author Juan_
 *
 */
public class LineServiceImplTest extends BowlingImplTest {

	@Test
	 public void whenLineIsNotParsable() {
		
		ConsoleOutputCapturer consoleOutputCapturer = new ConsoleOutputCapturer();
		
		consoleOutputCapturer.start();
		
		Map<String, List<String>> scores = new LinkedHashMap<String, List<String>>();
		
		ArrayList<String> juanScore = new ArrayList<String>();
		juanScore.add("7");
		juanScore.add("9");
		juanScore.add("9");
		
		ArrayList<String> carlosScore = new ArrayList<String>();
		carlosScore.add("10");
		carlosScore.add("0");
		carlosScore.add("3");
		
		scores.put("Juan", juanScore);
		scores.put("Carlos", carlosScore);
		
		lineService.parse(scores);
		
		String logger = consoleOutputCapturer.stop();
		
		Assert.assertThat(logger, CoreMatchers.containsString("Error creating Frame"));
		

	 }
	
	
}
