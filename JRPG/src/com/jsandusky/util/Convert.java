package com.jsandusky.util;
import com.badlogic.gdx.math.*;

public class Convert
{
	public static Vector3 quatToEuler(Quaternion q1) {
		Vector3 ret = new Vector3();
		
		double test = q1.x*q1.y + q1.z*q1.w;
		if (test > 0.499) { // singularity at north pole
			ret.x = (float)(2 * Math.atan2(q1.x,q1.w));
			ret.y = (float)Math.PI/2;
			ret.z = 0;
			return ret;
		}
		if (test < -0.499) { // singularity at south pole
			ret.x = (float)(-2 * Math.atan2(q1.x,q1.w));
			ret.y = (float)- Math.PI/2;
			ret.z = 0;
			return ret;
		}
		float sqx = q1.x*q1.x;
		float sqy = q1.y*q1.y;
		float sqz = q1.z*q1.z;
		ret.x = (float)Math.atan2(2*q1.y*q1.w-2*q1.x*q1.z , 1 - 2*sqy - 2*sqz);
		ret.y = (float)Math.asin(2*test);
		ret.z = (float)Math.atan2(2*q1.x*q1.w-2*q1.y*q1.z , 1 - 2*sqx - 2*sqz);
		
		return ret;
	}
}
