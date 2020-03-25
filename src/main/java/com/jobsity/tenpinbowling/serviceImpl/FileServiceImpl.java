package com.jobsity.tenpinbowling.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.service.IFileService;

/**
 * Implementation for IFileService
 * 
 * @author Juan_
 *
 */
@Service
public class FileServiceImpl implements IFileService {

	private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	// Creates a stream of data, reading each line of file
	public List<String> load(String fileName) {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			return stream.collect(Collectors.toList());
		} catch (IOException e) {
			this.logger.error(String.format("Error loading the file {0}", fileName, e.getMessage()));
			return null;
		}
	}
}
