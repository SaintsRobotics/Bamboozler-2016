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
	Timer timer = new Timer();
	TimerTask stop = new TimerTask(){
		@Override
		public void run(){
			Motors.CHOOCHOO.set(0);
		}
	};
	TimerTask checkSwitchStop = new TimerTask(){
		@Override
		public void run(){
			if(Sensor.LimitSwitch){
				
			}
		}
	};
	public Stage currentStage = Stage.UNWOUND;
	public Stage brakakaka(){
		if(currentStage == Stage.UNWOUND){
			timer.schedule(stop, 1000);
		}
	}
}
