package com.jobsity.tenpinbowling.serviceImpl;

import static org.junit.Assert.assertThat;

import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.jobsity.tenpinbowling.util.ConsoleOutputCapturer;





/**
 * Test for loading the file
 * @author Juan_
 *
 */
public class FileServiceImplTest extends BowlingImplTest {

	
    @Test
    public void whenTestIsNotFound() {
    	
    	ConsoleOutputCapturer consoleOutputCapturer = new ConsoleOutputCapturer();
    	
    	consoleOutputCapturer.start();
    	
    	fileService.load("NotFoundFile.txt");
    	
    	String logger = consoleOutputCapturer.stop();
    	
    	Assert.assertThat(logger, CoreMatchers.containsString("Error loading the file"));
    	
    }
    
    
    @Test
    public void whenTestIsFound() {
    	
    	String fileName = this.getClass().getResource("/score.txt").getPath().substring(1);
    	
    	List<String> playList = fileService.load(fileName);
    	
    	assertThat(playList, CoreMatchers.hasItems(CoreMatchers.startsWith("Jeff"),CoreMatchers.endsWith("10")));
    	
    }
	
	 
	
	
}
