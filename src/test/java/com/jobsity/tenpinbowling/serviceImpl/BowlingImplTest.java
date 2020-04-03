package com.jobsity.tenpinbowling.serviceImpl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jobsity.tenpinbowling.service.IFileService;
import com.jobsity.tenpinbowling.service.IFrameService;
import com.jobsity.tenpinbowling.service.IInputValidationService;
import com.jobsity.tenpinbowling.service.ILineService;
import com.jobsity.tenpinbowling.service.IScoreService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
    GameBoardServiceImpl.class,
    FileServiceImpl.class,
    FrameServiceImpl.class,
    ScoreServiceImpl.class,
    LineServiceImpl.class,
    PointServiceImpl.class,
    OutputServiceImpl.class,
    InputValidationServiceImpl.class})
/**
 * Base Class for Injecting service
 * @author Juan_
 *
 */
public abstract class BowlingImplTest {
	
	@Autowired
	protected IFileService fileService;
	
	@Autowired
	protected IScoreService scoreService;
	
	@Autowired
	protected ILineService lineService;
	
	@Autowired
	protected IFrameService frameService;
	
	@Autowired
	protected IInputValidationService inputValidationService;

}
