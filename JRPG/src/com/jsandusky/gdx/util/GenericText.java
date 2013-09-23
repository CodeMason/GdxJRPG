package com.jsandusky.gdx.util;

public class GenericText {
	public enum TextType {
		Battle,
		Win,
		Defeat,
		RefuseBattle,
		Trade,
		Shop
	}
	public enum Region {
		Starter,
		Forest,
		Mountain
	}
	public enum AgeRange {
		Young,
		Old
	}
	public static String getMessage(TextType type) {
		Random r = new Random(java.lang.System.currentTimeMillis());
		switch (type) {
		case Battle:
			switch (r.randI(0, 5)) {
			case 0:
				return "I'm going to crush you!";
			case 1:
				return "Smell that? It's your impending defeat!";
			case 2:
				return "Which of us is greater? Only one way to find out!";
			case 3:
				return "You can't really think you have a chance.";
			case 4:
				return "I can never refuse a chance to battle!";
			}
			break;
		case Win:
			switch (r.randI(0, 5)) {
			case 0:
				return "You're no match for me!";
			case 1:
				return "Yet another victory.";
			case 2:
				return "That wasn't even exciting.";
			case 3:
				return "That was a good challenge!";
			case 4:
				return "Did you even try?";
			}
			break;
		case Defeat:
			switch (r.randI(0, 5)) {
			case 0:
				return "I only made a small error.";
			case 1:
				return "I was sure I would win.";
			case 2:
				return "Unbelievable.";
			case 3:
				return "Beaten?";
			case 4:
				return "It'll never happen again.";
			}
			break;
		case RefuseBattle:
			switch (r.randI(0, 5)) {
			case 0:
				return "I'm not ready for another round.";
			case 1:
				return "What? Now you want to brow-beat me?";
			case 2:
				return "In time I'll be ready to take you down!";
			case 3:
				return "I need more practice.";
			case 4:
				return "No way, not again.";
			}
			break;
		case Trade:
			switch (r.randI(0, 3)) {
			case 0:
				return "Let's see what you've got.";
			case 1:
				return "Have anything worthwhile?";
			case 2:
				return "Only if it's a fair trade.";
			}
			break;
		case Shop:
			switch (r.randI(0, 5)) {
			case 0:
				return "I'm sure I have just what you need.";
			case 1:
				return "Take a look at my wares.";
			case 2:
				return "And what might you be looking for?";
			case 3:
				return "I have the best prices around.";
			case 4:
				return r.randI(4, 10) + " years of business without an unhappy customer.";
			}
			break;
		}
		return "...";
	}
	
	public static String getRegionText(Region region) {
		Random r = new Random(java.lang.System.currentTimeMillis());
		switch (region) {
		case Starter:
			switch (r.randI(0, 5)) {
			case 0:
				return "You don't look like your from around here.";
			case 1:
				return "Ships are always sailing in, but they never seem to leave with anything.";
			case 2:
				return "Seems like strange temples keep popping up these days.";
			case 3:
				return "There's not much here, you should head up the road.";
			case 4:
				return "Theo's is a mighty fine pub.";
			}
			break;
		case Forest:
			switch (r.randI(0, 5)) {
			case 0:
				return "Never trust a druid.";
			case 1:
				return "If you find a druid who isn't out for your head - he can heal your spirits.";
			case 2:
				return "Stay out of the long grass!";
			case 3:
				return "With all the spirits the forest is a scary place to hunt.";
			case 4:
				return "Once a week or so someone get's lost in the misty parts of the forest.";
			}
			break;
		case Mountain:
			switch (r.randI(0, 5)) {
			case 0:
				return "I hear there's exceptionally rare spirits to be found in the caves.";
			case 1:
				return "You have to take care on some of the mountain paths ... very hazardous.";
			case 2:
				return "The earth spirits love these mountains.";
			case 3:
				return "There's rumors of a labryinth hidden away in the mountains.";
			case 4:
				return "There's a hermit up in the mountains who can heal spirits.";
			}
			break;
		}
		return "...";
	}
}
