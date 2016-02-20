package test;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkBinder {
	NetworkTable table;
	public void init(){
		table = NetworkTable.getTable("pidtune");
		
	}
}
