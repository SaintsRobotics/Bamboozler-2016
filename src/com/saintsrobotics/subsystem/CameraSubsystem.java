package com.saintsrobotics.subsystem;


import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Range;
import com.ni.vision.NIVision.ShapeMode;


import com.saintsrobotics.Robot;

import edu.wpi.first.wpilibj.CameraServer;


public class CameraSubsystem{
    
	boolean isReady = true;
    int session;
    Image raw;
    
    
    public CameraSubsystem() {
    	try{
    		raw = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    		session = NIVision.IMAQdxOpenCamera("cam0",
    				
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
    		NIVision.IMAQdxConfigureGrab(session);
    	}catch(Exception e){
    		isReady = false;
    	}
    }
    public void enable(){
    	if(!isReady) return;
    		NIVision.IMAQdxStartAcquisition(session);
    }

    public void grabImage() {
    	if(!isReady) return;
        NIVision.IMAQdxGrab(session, raw, 1);
        
        /*NIVision.imaqColorThreshold(raw, raw, 1, NIVision.ColorMode.HSI,
                new Range(0, 255), new Range(0, 255), new Range(0, 255));*/  
        CameraServer.getInstance().setImage(raw);
    }
    public void end() {
    	if(!isReady) return;
        NIVision.IMAQdxStopAcquisition(session);
    }
}


