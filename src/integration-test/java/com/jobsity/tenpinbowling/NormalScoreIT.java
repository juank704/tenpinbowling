package com.jobsity.tenpinbowling;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.entity.LineTO;

public class NormalScoreIT extends BowlingIT {

	@Test
	public void regularGame() {
		File regularGame = new File(this.getClass().getResource("/score.txt").getFile());
		String pathFile = String.format("%s", regularGame.getAbsolutePath().toString());

		GameBoardTO gameboard = this.gameBoardService.load(pathFile);
		Assert.assertNotNull(gameboard);

		gameboard = this.pointService.calculate(gameboard);
		Assert.assertNotNull(gameboard);
		LineTO bowler1 = gameboard.getLines().get(0);
		LineTO bowler2 = gameboard.getLines().get(1);

		Assert.assertNotNull(bowler1);
		Assert.assertNotNull(bowler2);

		List<FrameTO> framesBowler1 = bowler1.getFrames();
		List<FrameTO> framesBowler2 = bowler2.getFrames();

		Assert.assertNotNull(framesBowler1);
		Assert.assertNotNull(framesBowler2);

		FrameTO lastFrameBowler1 = framesBowler1.get(FrameTO.MAX_NUMBER_OF_FRAMES - 1);
		FrameTO lastFrameBowler2 = framesBowler2.get(FrameTO.MAX_NUMBER_OF_FRAMES - 1);

		Assert.assertNotNull(lastFrameBowler1);
		Assert.assertNotNull(lastFrameBowler2);

		int scoreBowler1 = 167;
		int scoreBowler2 = 151;

		Assert.assertTrue(lastFrameBowler1.getResult() == scoreBowler1);
		Assert.assertTrue(lastFrameBowler2.getResult() == scoreBowler2);
	}

}
