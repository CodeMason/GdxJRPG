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
	
	public static Vector3 mult(Matrix3 mat, Vector3 vec) {
		Vector3 ret = new Vector3();
		ret.x = mat.val[mat.M00] * vec.x + mat.val[mat.M10] * vec.y + mat.val[mat.M20] * vec.z;
		ret.y = mat.val[mat.M01] * vec.x + mat.val[mat.M11] * vec.y + mat.val[mat.M21] * vec.z;
		ret.z = mat.val[mat.M02] * vec.x + mat.val[mat.M12] * vec.y + mat.val[mat.M22] * vec.z;
		return ret;
	}
	
	public static void mult(Matrix3 mat, Vector2 vec) {
		Vector3 out = mult(mat,new Vector3(vec.x,vec.y,0));
		vec.set(out.x,out.y);
	}
}
