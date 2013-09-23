# Femto Script

# Control script for the library user interface

func getCount()
	int ret
	ret = 7
	return ret
endfunc

func getItem(object adapter, int idx)
	if idx == 0
		return "melee"
	elseif idx == 1
		return "ballistics"
	elseif idx == 2
		return "explosives"
	elseif idx == 3
		return "energyweapons"
	elseif idx == 4
		return "suitrisers"
	elseif idx == 5
		return "instantitems"
	elseif idx == 6
		return "back"
	endif
	return ""
endfunc

func populate(object frag, int key)
	object items = frag.TextItems
	
	#frag.MainTitle = key
	if key == 0
		object warknife = create("com.jsandusky.ui.GenInfoItem")
		object warknifeitems = warknife.Items
		warknife.Title = "War Knife"
			warknifeitems.add("The no frills standard issue over-sized combat knife.")
			warknifeitems.add("SPECIAL ABILITY:")
			warknifeitems.add("The only melee weapon without a special ability.")		
		items.add(warknife)
		
		object gravsword = create("com.jsandusky.ui.GenInfoItem")
		gravsword.Title = "Gravity Sword"
		object gravsworditems = gravsword.Items
			gravsworditems.add("That's just how much they mess stuff up.")
			gravsworditems.add("SPECIAL ABILITY:")
			gravsworditems.add("The gravity sword throws a small gravitational field which traps what it hits in place.")
		items.add(gravsword)
		
		object powerlance = create("com.jsandusky.ui.GenInfoItem")
		powerlance.Title = "Power Lance"
		object powerlanceitems = powerlance.Items
			powerlanceitems.add("The power lance is a unique melee weapon with greater range than most.")
			powerlanceitems.add("It's large weight make it both powerful on the attack and sturdy on guard, at the cost of speed.");
			powerlanceitems.add("SPECIAL ABILITY:")
			powerlanceitems.add("Projects a powerful energy beam in the targeted direction.")
		items.add(powerlance)
			
		object plasmasword = create("com.jsandusky.ui.GenInfoItem")
		plasmasword.Title = "Plasma Sword"
		object plasmasworditems = plasmasword.Items
			plasmasworditems.add("A pint sized gravity control constrains a stream of plasma within the confines of a blade. Having little mass it's fast, but without a solid form it's not very useful for blocking.")
			plasmasworditems.add("Attacks that most other weapons would block, pass right through the Plasma Sword. But the Plasma Sword's attacks also pass through most other weapons when attacking as well.")
			plasmasworditems.add("It's raw offense.")
			plasmasworditems.add("SPECIAL ABILITY:")
			plasmasworditems.add("Unleashes a blast of plasma that damages everything near the user.")
		items.add(plasmasword)
			
		object shockknife = create("com.jsandusky.ui.GenInfoItem")
		shockknife.Title = "Shock Knife"
		object shockknifeitems = shockknife.Items
			shockknifeitems.add("An electrified knife. It's fast, it's light, and it electrocutes.")
			shockknifeitems.add("SPECIAL ABILITY:")
			shockknifeitems.add("Numerous electrical sparks explode from the victim, damaging anything they collide with.")
		items.add(shockknife)
		
		object reaver = create("com.jsandusky.ui.GenInfoItem")
		reaver.Title = "Reaver"
		object reaveritems = reaver.Items
			reaveritems.add("An oddly shaped weapon with a strong emphasis on defense.")
			reaveritems.add("It doesn't bring much to the table in regards to attack damage, but it's special ability gives it the ability to cause more damage than any other weapon.")
			reaveritems.add("SPECIAL ABILITY:")
			reaveritems.add("Every attack blocked by the Reaver accumulates a damage pool, the Reaver's special ability drains this pool to add all of the damage to it's attack.")
		items.add(reaver)
			
		object broadsword = create("com.jsandusky.ui.GenInfoItem")
		broadsword.Title = "Broadword"
		object broadsworditems = broadsword.Items
			broadsworditems.add("An oversized mega-lith of a blade and a fine balance of offense and defense.")
			broadsworditems.add("SPECIAL ABILITY:")
			broadsworditems.add("Generate a massive pillar of energy that remains for a few seconds damaging everything within.")
			broadsworditems.add("Best used to maintain control of the map.")
		items.add(broadsword)
		
		object stiletto = create("com.jsandusky.ui.GenInfoItem")
		stiletto.Title = "Stiletto"
		object stilettoitems = stiletto.Items
			stilettoitems.add("Wirey, complicated, and elegant. Designed for the quick and the graceful who attack swiftly and from all sides.")
			stilettoitems.add("SPECIAL ABILITY:")
			stilettoitems.add("The Stiletto is equiped with a translocation device that allows the user to instantly move from one point to another.")
		items.add(stiletto)
	elseif key == 1
		object abrifle = create("com.jsandusky.ui.GenInfoItem")
		object abrifleitems = abrifle.Items
		abrifle.Title = "Anti-Beast Rifle"
			abrifleitems.add("Large calibre rifle designed for dropping extremely large targets.")
			abrifleitems.add("It doesn't shoot all that fast, but it packs a whallop.")
		items.add(abrifle)
		
		object titan = create("com.jsandusky.ui.GenInfoItem")
		titan.Title = "Titan Anti-Tank Rifle"
		object titanitems = titan.Items
			titanitems.add("While the Anti-Beast Rifle can drop large fleshy targets with ease the Titan drops large solid objects with ease.")
			titanitems.add("It accomplishes this with a ridiculously overpowered propellent behind a slug with a weight measured in pounds.")
		items.add(titan)
		
		object mag = create("com.jsandusky.ui.GenInfoItem")
		object magitems = mag.Items
		mag.Title = "Mag Launcher"
			magitems.add("Miniature rail-gun that uses magnetism to propell a small projectile at break-neck speeds. Drains practically nothing in regards to energy.")
		items.add(mag)
	elseif key == 2
		object gren = create("com.jsandusky.ui.GenInfoItem")
		object grenitems = gren.Items
		gren.Title = "Grenade Launcher"
			grenitems.add("Launches grenades (you don't say) in parabolic arcs.")
			grenitems.add("The grenades detonate on contact with living targets or after a few seconds pass.")
			grenitems.add("While it's all explosive good-ness the smoke contrail left by the grenade leaves no questions as to where it came from.")
		items.add(gren)
		object mort = create("com.jsandusky.ui.GenInfoItem")
		object mortitems = mort.Items
		mort.Title = "AT Mortar"
			mortitems.add("The bigger badder grenade launcher.")
			mortitems.add("The added weight of the shell reduces it's velocity some, but that extra boost of explosive power is worth it.")
			mortitems.add("Like the grenade launcher it's pretty easy to spot the source.")
		items.add(mort)
	elseif key == 3
		object beamrifle = create("com.jsandusky.ui.GenInfoItem")
		beamrifle.Title = "Beam Rifle"
		object beamrifleitems = beamrifle.Items
			beamrifleitems.add("Fires a small diameter laser beam.")
			beamrifleitems.add("In comparison to ballistic weapons it's energy cost is expensive, but it's the least energy costly of all energy weapons.")
		items.add(beamrifle)
		object ppc = create("com.jsandusky.ui.GenInfoItem")
		object ppcitems = ppc.Items
		ppc.Title = "Particle Projector"
			ppcitems.add("If you detonated a very tiny nuclear missile and channeled all of it's particle into one direction, the Particle Projector is exactly what you'd have.")
			ppcitems.add("It's extremely powerful but it's also extremely energy hungry.")
		items.add(ppc)
		object bigppc = create("com.jsandusky.ui.GenInfoItem")
		object bigppcitems = bigppc.Items
		bigppc.Title = "Particle Cannon"
			bigppcitems.add("It's like a Particle Projector with an added love-dose of particles.")
			bigppcitems.add("A larger power-pack ramps up the beam diameter significantly.")
		items.add(bigppc)
		object hyperbeam = create("com.jsandusky.ui.GenInfoItem")
		hyperbeam.Title = "Hyperbeam Rifle"
		object hyperbeamitems = hyperbeam.Items
			hyperbeamitems.add("An upgraded Beam Rifle.")
			hyperbeamitems.add("Power consumption has gone up just slightly but the refire rate has been reduced to near zero. It'll fire non-stop until your batteries are empty.")
		items.add(hyperbeam)
		object plas = create("com.jsandusky.ui.GenInfoItem")
		object plasitems = plas.Items
		plas.Title = "Plasma Rifle"
			plasitems.add("Emits a glob of plasma. Said glob doesn't stop until it burns itself out on something else.")
			plasitems.add("When it does burn itself out, it doesn't in a spectacular burst of energy.")
		items.add(plas)
		object bigplas = create("com.jsandusky.ui.GenInfoItem")
		object bigplasitems = bigplas.Items		
		bigplas.Title = "Heavy Plasma Rifle"
			bigplasitems.add("Bigger glob of plasma equals a bigger more eye-searing detonation on contact.")
		items.add(bigplas)
		object reaper = create("com.jsandusky.ui.GenInfoItem")
		object reaperitems = reaper.Items
		reaper.Title = "Reaper"
			reaperitems.add("Fires concentrated globes of energy at high rates in a sort of shotgun spray pattern.")
			reaperitems.add("It uses very little energy per shot, but each particle doesn't do much harm.")
		items.add(reaper)
	elseif key == 4
		object mine = create("com.jsandusky.ui.GenInfoItem")
		object mineitems = mine.Items
		mine.Title = "Aerial Mines"
			mineitems.add("When deployed Aerial Mines remain in position and detonate on enemy contact.")
			mineitems.add("If you're trying to evade pursuit, just lay a few of these down.")
			items.add(mine)
	elseif key == 5
		object pd = create("com.jsandusky.ui.GenInfoItem")
		object pditems = pd.Items
		pd.Title = "PD Shield"
			pditems.add("Uses gravity control tech to deflect most anything coming at the user.")
			pditems.add("The gravitation based nature of it's design also strange effects on plasma based weapons.")
			items.add(pd)
		object ts = create("com.jsandusky.ui.GenInfoItem")
		object tsitems = ts.Items
		ts.Title = "Thorn Shield"
			tsitems.add("An electric bases shield. Unlike the PD Shield it does not effect plasma and it doesn't even protect the wearer.")
			tsitems.add("What it does do is give a nasty shock to anyone that gets too close (or that the wearer gets close to).")
			tsitems.add("It's an excellent item for aggressors.")
			items.add(ts)
	endif
endfunc
