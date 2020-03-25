package com.jobsity.tenpinbowling.service;

import java.util.List;
import java.util.Map;

import com.jobsity.tenpinbowling.entity.LineTO;


/**
 * Service for line File
 * @author Juan_
 *
 */
public interface ILineService {

	//parse each line to score
	public List<LineTO> parse(Map<String,List<String>> scores);
	
}
