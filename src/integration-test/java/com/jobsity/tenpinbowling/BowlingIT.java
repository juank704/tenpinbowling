package com.jobsity.tenpinbowling;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jobsity.tenpinbowling.service.IGameBoardService;
import com.jobsity.tenpinbowling.service.IPointService;
import com.jobsity.tenpinbowling.serviceImpl.FileServiceImpl;
import com.jobsity.tenpinbowling.serviceImpl.FrameServiceImpl;
import com.jobsity.tenpinbowling.serviceImpl.GameBoardServiceImpl;
import com.jobsity.tenpinbowling.serviceImpl.InputValidationServiceImpl;
import com.jobsity.tenpinbowling.serviceImpl.LineServiceImpl;
import com.jobsity.tenpinbowling.serviceImpl.OutputServiceImpl;
import com.jobsity.tenpinbowling.serviceImpl.PointServiceImpl;
import com.jobsity.tenpinbowling.serviceImpl.ScoreServiceImpl;


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
public abstract class BowlingIT {

	  @Autowired
	  protected IGameBoardService gameBoardService;

	  @Autowired
	  protected IPointService pointService;
	
	
}
