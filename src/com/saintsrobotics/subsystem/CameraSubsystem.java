package com.saintsrobotics.subsystem;


import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Range;
import com.ni.vision.NIVision.ShapeMode;


import com.saintsrobotics.Robot;

import edu.wpi.first.wpilibj.CameraServer;


public class CameraSubsystem{
    
	private boolean isReady = true;
    private int session;
    private Image raw;
    
    public CameraSubsystem(){
    	//Default to cam0
    	this("cam0");
    }
    public CameraSubsystem(String camPort) {
    	try{
    		//Create the object that holds the raw image
    		raw = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    		//Open a camera session 
    		session = NIVision.IMAQdxOpenCamera(camPort,
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
    		//Configure the session
    		NIVision.IMAQdxConfigureGrab(session);
    	}catch(Exception e){
    		//If the camera isn't plugged in, isn't configured, etc, then now we know. 
    		isReady = false;
    	}
    }
    public void enable(){
    	//Start camera acquisition. Return if we havn't initialized the camera 
    	if(!isReady) return;
    	NIVision.IMAQdxStartAcquisition(session);
    }

    public void grabImage() {
    	//Grab the image and serve it up on the cameraserver
    	if(!isReady) return;
        NIVision.IMAQdxGrab(session, raw, 1);
          
        CameraServer.getInstance().setImage(raw);
    }
    public void end() {
    	//Stop acquisition.
    	if(!isReady) return;
        NIVision.IMAQdxStopAcquisition(session);
    }
}


