package com.jsandusky.jrpg.model;
import java.io.Serializable;
import com.jsandusky.gdx.common.TextureHandle;
import java.util.ArrayList;
import com.jsandusky.util.Ref;

public class MapMonster extends Base implements Serializable
{
	@Ref(cl = CharacterSet.class)
	public Reference<CharacterSet> CharSet;
	
	@Ref(cl = MonsterTeam.class)
	public ArrayList< Reference<MonsterTeam> > PossibleTeams = new ArrayList< Reference<MonsterTeam> >();
}
