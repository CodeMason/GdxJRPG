package com.jsandusky.drt;
import java.util.ArrayList;
import java.io.Serializable;

public class Keyframe implements Serializable
{
	public int FrameNumber;
	public ArrayList<Bone> Bones;
	public String Trigger = "";
	public boolean FlipVertically;
	public boolean FlipHorizontally;

	public transient float FrameTime;
	public transient ArrayList<Bone> UpdateOrderBones;

	public Keyframe()
	{
		UpdateOrderBones = new ArrayList<Bone>();
	}
	
	public Keyframe cpy(int FrameNumber) {
		Keyframe k = new Keyframe();
		k.FrameNumber = FrameNumber;
		k.FlipVertically = FlipVertically;
		k.FlipHorizontally = FlipHorizontally;
		k.Trigger = "";
		for (Bone b : Bones) {
			k.Bones.add(b.cpy());
		}
		return k;
	}

	public void SortBones()
	{
		UpdateOrderBones.clear();

		for (Bone bone : Bones)
		{
			BoneSortAdd(bone);
		}
	}

	protected void BoneSortAdd(Bone b)
	{
		if (UpdateOrderBones.contains(b))
			return;

		if (b.ParentIndex != -1)
			BoneSortAdd(Bones.get(b.ParentIndex));

		UpdateOrderBones.add(b);
		b.UpdateIndex = UpdateOrderBones.size() - 1;
	}
}
