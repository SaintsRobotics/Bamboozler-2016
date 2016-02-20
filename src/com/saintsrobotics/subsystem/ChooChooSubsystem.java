package com.saintsrobotics.subsystem;

import java.util.Timer;
import java.util.TimerTask;
import com.saintsrobotics.Motors;
import com.saintsrobotics.Sensor;

public class ChooChooSubsystem {
	public static volatile boolean dirty = false;
	public enum Stage {
		UNWOUND,
		PARTWOUND,
		HAIRTRIGGER,
		RUNNING
	}
	Timer timer = new Timer(true);
	//This timertask is used to stop the choochooafter a given amount of time
	class stop extends TimerTask{
		@Override
		public void run(){
			dirty = false;
			Motors.CHOOCHOO.set(0);
		}
	};
	//This timertask is used to stop the choochoo after the limit switch hits a certain value.
	class checkSwitchStop extends TimerTask{
		public checkSwitchStop(){
			super();
			checkAgainst = Sensor.LimitSwitches.CHOOCHOO.get();
		}
		boolean checkAgainst;
		boolean tripped = false;
		@Override
		public void run(){
			if(Sensor.LimitSwitches.CHOOCHOO.get() != checkAgainst){
				tripped = true;
			}
			if(tripped && Sensor.LimitSwitches.CHOOCHOO.get() == checkAgainst){
				Motors.CHOOCHOO.set(0);
				dirty = false;
				this.cancel();
			}
		}
	};
	
	public Stage currentStage = Stage.UNWOUND;
	public void backDrive(){
		Motors.CHOOCHOO.set(0.1);
	}
	public void stop(){
		Motors.CHOOCHOO.set(0);
	}
	public Stage brakakaka(){
		//if it's already running, don't run something else
		if(dirty) return Stage.RUNNING;
		dirty = true;
		Motors.CHOOCHOO.set(1);
		timer.schedule(new stop(), 700);
		return Stage.RUNNING;
		/*Motors.CHOOCHOO.set(1);
		if(currentStage == Stage.PARTWOUND){
			timer.schedule(new checkSwitchStop(), 0, 50);
			currentStage = Stage.UNWOUND;
		}else{
			timer.schedule(new stop(), 700);
			currentStage = Stage.PARTWOUND;
		}
		return currentStage;*/
	}
}
