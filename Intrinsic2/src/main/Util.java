package main;

import org.bukkit.Location;

public class Util {
	
	public static Location getCenter(Location loc) {
		double x = getRelativeCoord( loc.getX() );
		double y = getRelativeCoord( loc.getY() );
		double z = getRelativeCoord( loc.getZ() );
		Location re = new Location(loc.getWorld(),x,y,z);
		return re;
	}
	
	static double getRelativeCoord(double i) {
		if(i>0) i+=.5;
		if(i<0) i+=.5;
		return i;
	}
}
