package com.jsandusky.jrpg.model;

//Not consumable
public class QuestItem extends Item
{
	@Override
	public boolean canUse() {
		return false;
	}
}
