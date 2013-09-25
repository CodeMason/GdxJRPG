package com.jsandusky.jrpg.model;
import java.io.Serializable;
import com.jsandusky.gdx.common.TextureHandle;
import java.util.ArrayList;

public class MapMonster extends Base implements Serializable
{
	public Reference<CharacterSet> CharSet;
	public ArrayList< Reference<MonsterTeam> > PossibleTeams = new ArrayList< Reference<MonsterTeam> >();
}
