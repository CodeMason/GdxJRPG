# Femto Script

# Control script for the library user interface

func getCount()
	int ret
	ret = 8
	return ret
endfunc

func getItem(object adapter, int idx)
	if idx == 0
		return "startinggame"
	elseif idx == 1
		return "moving"
	elseif idx == 2
		return "fighting"
	elseif idx == 3
		return "weapons"
	elseif idx == 4
		return "usinggear"
	elseif idx == 5
		return "deployables"
	elseif idx == 6
		return "objectives"
	elseif idx == 7
		return "back"
	endif
endfunc

func populate(object frag, int key)
	object items = frag.TextItems
	
	#frag.MainTitle = key
	if key == 0
		object starting = create("com.jsandusky.ui.GenInfoItem")
		object startingitems = starting.Items
		starting.Title = "Starting a Game"
			startingitems.add("After selecting 'Play' from the main menu you'll be presented with three options. The first is 'Solo,' followed by 'LAN' and 'Online.'")
			startingitems.add("Solo play is confined to playing alone against bots. It's an excellent way to get acquanted with gameplay.")
			startingitems.add("'LAN' play allows you to host or join games that are being played on the local network (WiFi) to which your device is attached. This has a considerable advantage in playability.")
			startingitems.add("'Online' lets you either host an accessible game (via data connection or the LAN/WAN connectivity of your device) to many parties or join someone else's game. Latency can become a problem especially when playing over a device's data connection.")
		items.add(starting)
	elseif key == 1
		 object moving = create("com.jsandusky.ui.GenInfoItem")
		 moving.Title = "Moving Around"
		 object movingitems = moving.Items
			movingitems.add("Once your character has spawned you may tap the screen to move your character around.")
			movingitems.add("Double-tap the screen and your character will move to the target point extra fast at the cost of a drain on their energy.")
			movingitems.add("In general you'll want to use single tap for most of your movements and double-tap in emergencies where you need to evade incoming fire.")
		items.add(moving)
	elseif key == 2
		#titles.put(0,"Fighting")
		 object fighting = create("com.jsandusky.ui.GenInfoItem")
		 object fightingitems = fighting.Items
		 fighting.Title = "Fighting"
			fightingitems.add("On screen there are several buttons that engage different fighting modes.")
			fightingitems.add("To either fire a weapon or make a melee attack first touch (and hold/maintain touch) on the button representing the attack you'd like to make and then tap where you'd like to attack.")
			fightingitems.add("If using a ranged weapon you will fire in that direction, or if using a melee weapon you will dash in that direction and attack once you make contact with a target.")
			fightingitems.add("Melee weapons give you special abilities. Sometimes these abilities behave as above in the form of either melee or projectile attacks, but in other cases they behave as buffs or area of effect abilities.")
			fightingitems.add("Consult the 'Library' section on 'Melee Weapons' for information about melee weapon special abilities and how to effectively use them.")
			items.add(fighting)
	elseif key == 3
		 object gear = create("com.jsandusky.ui.GenInfoItem")
		 object gearitems = gear.Items
		 gear.Title = "Understanding Weapons"
			gearitems.add("That's just how much they mess stuff up.")
			items.add(gear)
	elseif key == 4
		 object gear = create("com.jsandusky.ui.GenInfoItem")
		 object gearitems = gear.Items
		 gear.Title = "Using Gear"
			gearitems.add("That's just how much they mess stuff up.")
			items.add(gear)
	elseif key == 5
		object gear = create("com.jsandusky.ui.GenInfoItem")
		 object gearitems = gear.Items
		 gear.Title = "Deployables"
			gearitems.add("That's just how much they mess stuff up.")
			items.add(gear)
	elseif key == 6
		object gear = create("com.jsandusky.ui.GenInfoItem")
		 object gearitems = gear.Items
		 gear.Title = "Objectives"
			gearitems.add("That's just how much they mess stuff up.")
			items.add(gear)
	endif
endfunc
