#
# Common scripts
#

func createBasicThing(object pos, object rot, object sz)
	object entity = create("com.jsandusky.artemis.entity")
	
	object spatial = create("com.jsandusky.iv.Spatial")
	spatial.Position = pos
	spatial.Rotation = rot
	spatial.Scale = sz
	
	entity.addComponent(comp)
	
	return entity
endfunc

func doInstant(object sk, object target, object source)
	object vic = target.getComponent(getClass("com.jsandusky.iv.Killable"))
	vic.CurrentHP -= sk.HPDamage
	vic.CurrentMP -= sk.MPDamage
endfunc

func doBuff(object sk, object target)
	
endfunc

func createProto()
    return create("com.jsandusky.ent.EntityPrototype")
endfunc

func createComp()
    return create("com.jsandusky.ent.CompRecord")
endfunc

#######################################################
#######################################################
#   MAIN INITIALIZATION FUNCTION
#######################################################
#######################################################
func init()
    object factoryClass = getClass("com.jsandusky.iv.ent.EntityFactory")
    object factory = factorClass.getInst()
    
    createPlatformTemplates(factory)
    
    createMineTemplates(factory)
    
    createControlPoints(factory)
    
    createPickups(factory)
    
    createBallistics(factory)
    
    createSpecialFX(factory)
    
    createExplosives(factory)
    
    createEnergyWeapons(factory)
    
    createBeams(factory)
    
    createGore(factory)
    
    createPlayerModels(factory)
    
    createSpawns(factory)
endfunc

#######################################################
#   Creates the two kinds of platforms
#######################################################
func createPlatformTemplates(object factory)

    #CREATE THE SMALLER PLATFORMS

    object smallPlatform = createProto()
    
        object draw2d = createComp()
            draw2d.Comp = create("com.jsandusky.iv.Draw2D")
        object spatial = createComp()
            spatial.Comp = create("com.jsandusky.iv.Spatial")
            spatial.Clone = true
        object physics = createComp()
            physics.Comp = create("com.jsandusky.iv.Physics")
        object platform = createComp()
            platform.Comp = create("com.jsandusky.iv.Platform")
        object collider = createComp()
            collider.Comp = create("com.jsandusky.iv.Collider")
        smallPlatform.Comps.add(draw2d)
        smallPlatform.Comps.add(spatial)
        smallPlatform.Comps.add(physics)
        smallPlatform.Comps.add(platform)
        smallPlatform.Comps.add(collider)
        
        factory.addPrototype("small_platform",smallPlatform)
        
    #CREATE THE LARGE PLATFORM
    
    object largePlatform = createProto()
    
        draw2d = createComp()
            draw2d.Comp = create("com.jsandusky.iv.Draw2D")
        spatial = createComp()
            spatial.Comp = create("com.jsandusky.iv.Spatial")
            spatial.Clone = true
        physics = createComp()
            physics.Comp = create("com.jsandusky.iv.Physics")
        platform = createComp()
            platform.Comp = create("com.jsandusky.iv.Platform")
        collider = createComp()
            collider.Comp = create("com.jsandusky.iv.Collider")
        
        largePlatform.Comps.add(draw2d)
        largePlatform.Comps.add(spatial)
        largePlatform.Comps.add(physics)
        largePlatform.Comps.add(platform)
        largePlatform.Comps.add(collider)
        
        factory.addPrototype("large_platform",largePlatform)
endfunc

#######################################################
#   Creates the deployable mines
#######################################################
func createMineTemplates(object factory)

# The actual mine just sitting there
    object mine = createProto()
    
        object draw2d = createComp()
            draw2d.Comp = create("com.jsandusky.iv.Draw2D")
        object spatial = createComp()
            spatial.Comp = create("com.jsandusky.iv.Spatial")
        object physics = createComp()
            physics.Comp = create("com.jsandusky.iv.Physics")
        object killable = createComp()
            killable.Health = 5.0
            killable.MaxHealth = 5.0
            killable.Promote = "mine_exp"
            
        mine.Comps.add(draw2d)
        mine.Comps.add(spatial)
        mine.Comps.add(physics)
        mine.Comps.add(killable)
    
# When the mine explodes
    object mineExp = createProto()
        
        object spatial = createComp()
            spatial.Comp = create("com.jsandusky.iv.Spatial")
            spatial.Clone = true
        object exploder = createComp()
            exploder.BlastPower = 10.0
            exploder.DamagePerRay = 10.0
            exploder.ParticleCount = 16
            
        mineExp.Comps.add(spatial)
        mineExp.Comps.add(exploder)
        
        factory.addPrototype("mine",mine)
        factory.addPrototype("mine_exp",mineExp)
endfunc

#######################################################
#   Creates the basic scoring control point
#######################################################
func createControlPoints(object factory)

    object ctrlPt = createProto()
    
        object draw2d = createComp()
        object spatial = createComp()
        object playerOwned = createComp()
        object controlPoint = createComp()
        object physics = createComp()
        object collider = createComp()

    factory.addPrototype("control_point",ctrlPt)
endfunc

#######################################################
#   Creates item pickups
#######################################################
func createPickups(object factory)

    object res = getClass("com.jsandusky.iv.scripting.Resources")

    object pickupBase = createProto()
        object draw2d = createComp()
        draw2d.Comp = create("com.jsandusky.iv.Draw2D")
        object physics = createComp()
        physics.Comp = create("com.jsandusky.iv.Physics")
        object collider = createComp()
        collider.Comp = create("com.jsandusky.iv.Collider")
        object pickup = createComp()
        pickup.Comp = create("com.jsandusky.iv.Pickup")
        
    pickupBase.Comps.add(draw2d)
    pickupBase.Comps.add(physics)
    pickupBase.Comps.add(collider)
    pickupBase.Comps.add(pickup)
    
#Protective shield belt thing
    object shieldGenerator = pickupBase.clone()
    object sgDraw = shieldGenerator.Comps.get(0)
        sgDraw.Default = res.get("TextureName")
    object sgPick = shieldGenerator.Comps.get(3)
        sgPick.Item = "shield_generator"
        sgPick.Attach = "com.jsandusky.iv.Shield"
        sgPick.Value = 1
        
    factory.addPrototype("shield",shieldGenerator)
    
#Health up

    object medKit = pickupBase.clone()
    object medDraw = medKit.Comps.get(0)
        medDraw.Default = ""
    object medPick = medKit.Comps.get(3)
        medPick.Item = "medkit"
        medPick.EntityScript = "applyMedkit"
        medPick.Value = 1
        
    factory.addPrototype("medkit",medKit)

#Energy booster

    object energyKit = pickupBase.clone()
    object energyDraw = energyKit.Comps.get(0)
        energyDraw.Default = ""
    object energyPick = energyKit.Comps.get(3)
        energyPick.Item = "energykit"
        energyPick.EntityScript = "applyEnergyBoost"
        energyPick.Value = 1
        
    factory.addPrototype("energykit",energyKit)
    
#Weapon Pickups

endfunc

#######################################################
# Creates both the projectiles and their blasts
#######################################################
func createBallistics(object factory)
endfunc
    
#######################################################
# 
#######################################################
func createSpecialFX(object factory)
endfunc

#######################################################
# Creates both the projectiles and their explosions
#######################################################
func createExplosives(object factory)
endfunc

#######################################################
# 
#######################################################
func createEnergyWeapons(object factory)
endfunc

#######################################################
#
#######################################################
func createBeams(object factory)
endfunc

#######################################################
#
#######################################################
func createGore(object factory)
endfunc

#######################################################
#
#######################################################
func createPlayerModels(object factory)
endfunc

#######################################################
#
#######################################################
func createSpawns(object factory)

    object spawn = createProto()
        object spatial = createComp()
        spatial.Comp = create("com.jsandusky.iv.Spatial")
        object spawnPt = createComp()
        spawnPt.Comp = create("com.jsandusky.iv.Spawn")
    
endfunc