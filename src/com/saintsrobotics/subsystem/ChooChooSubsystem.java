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
	TimerTask stop = new TimerTask(){
		@Override
		public void run(){
			dirty = false;
			Motors.CHOOCHOO.set(0);
		}
	};
	//This timertask is used to stop the choochoo after the limit switch hits a certain value.
	class checkSwitchStop extends TimerTask{
		public checkSwitchStop(boolean stopVal){
			super();
			checkAgainst = stopVal;
		}
		boolean checkAgainst;
		@Override
		public void run(){
			if(Sensor.LimitSwitches.CHOOCHOO.get() == checkAgainst){
				Motors.CHOOCHOO.set(0);
				dirty = false;
				this.cancel();
			}
		}
	};
	public Stage currentStage = Stage.UNWOUND;
	public Stage brakakaka(){
		//if it's already running, don't run something else
		if(dirty) return Stage.RUNNING;
		dirty = true;
		if(currentStage == Stage.UNWOUND){
			//Run the motor for a second, then stop
			Motors.CHOOCHOO.set(1);
			timer.schedule(stop, 1000);
			return currentStage = Stage.PARTWOUND;
		}else if(currentStage== Stage.PARTWOUND){
			//Stop the motor when the switch returns true
			Motors.CHOOCHOO.set(1);
			timer.schedule(new checkSwitchStop(true), 10,50);
			return currentStage = Stage.HAIRTRIGGER;
		}else if (currentStage == Stage.HAIRTRIGGER){
			//Stop the motor when the switch returns false
			Motors.CHOOCHOO.set(1);
			timer.schedule(new checkSwitchStop(false), 0,50);
			return currentStage = Stage.UNWOUND;
		}else{
			//I'm not sure how you would get here, but here you are
			return null;
		}
	}
}
