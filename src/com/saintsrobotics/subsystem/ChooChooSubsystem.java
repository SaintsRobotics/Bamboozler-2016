package com.saintsrobotics.subsystem;

import java.util.Timer;
import java.util.TimerTask;
import com.saintsrobotics.Motors;
import com.saintsrobotics.Sensor;

public class ChooChooSubsystem {
	public enum Stage {
		UNWOUND,
		PARTWOUND,
		HAIRTRIGGER,
	}
	Timer timer = new Timer(true);
	TimerTask stop = new TimerTask(){
		@Override
		public void run(){
			Motors.CHOOCHOO.set(0);
		}
	};
	class checkSwitchStop extends TimerTask{
		boolean checkAgainst;
		boolean firstRun = true;
		@Override
		public void run(){
			if(firstRun){
				checkAgainst = Sensor.LimitSwitches.CHOOCHOO.get();
				return;
			}
			if(Sensor.LimitSwitches.CHOOCHOO.get() != checkAgainst){
				Motors.CHOOCHOO.set(0);
				this.cancel();
			}
		}
	};
	public Stage currentStage = Stage.UNWOUND;
	public Stage brakakaka(){
		if(currentStage == Stage.UNWOUND){
			timer.schedule(stop, 1000);
			return currentStage = Stage.PARTWOUND;
		}else if(currentStage== Stage.PARTWOUND){
			timer.schedule(new checkSwitchStop(), 10,50);
			return currentStage = Stage.HAIRTRIGGER;
		}else if (currentStage == Stage.HAIRTRIGGER){
			timer.schedule(new checkSwitchStop(), 0,50);
			return currentStage = Stage.UNWOUND;
		}
	}
}
