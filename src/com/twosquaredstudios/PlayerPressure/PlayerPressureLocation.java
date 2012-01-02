package com.twosquaredstudios.PlayerPressure;

import java.util.logging.Logger;

import org.bukkit.Location;

public final class PlayerPressureLocation extends java.lang.Object {
	
	private static int argsCount = 4;
	
	private String worldName;
	private double x;
	private double y;
	private double z;
	
	Logger log = Logger.getLogger("Minecraft");
	
	public PlayerPressureLocation(String serializedString) throws IllegalArgumentException {
		super();
		String args[] = serializedString.split(";");
		if (args.length != argsCount) {
			throw new IllegalArgumentException(args.length + " arguments given. Expected " + argsCount + ".");
		}
		String worldName = args[0];
		double x = 0;
		double y = 0;
		double z = 0;
		try {
			x = Double.valueOf(args[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(args[1] + " is not a valid value for 'x'.");
		}
		try {
			y = Double.valueOf(args[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(args[2] + " is not a valid value for 'y'.");
		}
		try {
			z = Double.valueOf(args[3]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(args[3] + " is not a valid value for 'z'.");
		}
		this.worldName = worldName;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public PlayerPressureLocation(Location location) {
		super();
		this.worldName = location.getWorld().getName();
		this.x = location.getX();
		this.y = location.getY();
		this.z = location.getZ();
	}
	
	public String toString() {
		return (worldName + ";" + x + ";" + y + ";" + z + ";");
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
        	return false;
        }
        
        if (obj.getClass() == getClass()) {
        	return equalsLocation((PlayerPressureLocation)obj);
        }
        else if (obj.getClass() == Location.class) {
        	return equalsLocation((Location)obj);
        }
        return false;
    }
	
	public boolean equalsLocation(PlayerPressureLocation obj) {
		if (obj == null) { 
            return false;
        }
        if (getClass() != obj.getClass()) {
        	return false;
        }
        final PlayerPressureLocation other = (PlayerPressureLocation)obj;

        if (this.worldName != other.worldName && (this.worldName == null || !this.worldName.equals(other.worldName))) {
            return false;
        }
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        return true;
	}
	
	public boolean equalsLocation(Location other) {
		if (other == null) {
            return false;
        }
        if (other.getClass() != Location.class) {
            return false;
        }

        if (this.worldName != other.getWorld().getName() && (this.worldName == null || !this.worldName.equals(other.getWorld().getName()))) {
            return false;
        }
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.getX())) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.getY())) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.getZ())) {
            return false;
        }
        return true;
	}
}
