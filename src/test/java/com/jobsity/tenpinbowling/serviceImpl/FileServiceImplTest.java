package com.jobsity.tenpinbowling.serviceImpl;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for loading the file
 * @author Juan_
 *
 */
public class FileServiceImplTest extends BowlingImplTest {

	 private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Test
    public void loadTest() {
    	
    	try (Stream<String> stream = Files.lines(Paths.get("score.txt"))) {
    		List<String> playList = stream.collect(Collectors.toList());
    		
    		 Assert.assertNotNull(stream);
    	        assertThat(playList, CoreMatchers.hasItems(CoreMatchers.startsWith("Jeff"),CoreMatchers.endsWith("10")));
    	} catch (IOException e) {
    		logger.error("Can't load file", e.getMessage());
		}
       
    }

	
	
}
