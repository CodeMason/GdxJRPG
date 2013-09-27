GdxJRPG
=======

JRPG Base project and tools for LibGDX

License for code is to be considered as Apache 2.0. Until otherwise clarified, license for any media content (including sound and graphics of all formats) should be considered as 'working content' not to be distributed until a thorough breakdown of rights is detailed (all current content is at worst of CC3-Attribution - the hold off is to compile a detailed list of names for attribution).

Purpose
=======

Ultimately this project is meant to serve as combined multi-platform runtime and toolset for the creation of JRPG style games. Despite the distinct JRPG designation, regards are given towards the development of more action oriented RPGs (or Zelda clones). No particular 'battle system' is favored leaving the battle system to be used (whether classic 'Dragon Quest,' 'Mystic Quest,' or 'FF3/6' style the compatibility will only be in regards to graphic content/setup).

Parts of the Project
=======

The first core part is the database model. This is mostly complete but will be revised heavily as tools are enhanced. The current tool-set is technically inferior. While the database editor technically works, it is not feasible to use in regards to user experience.

The second core part is the map editor. This will be an adaptation of my own prior work on developing a map editor for use on Android devices. At present (Sept 27 - 2013) it's at 50% for a conversion to running in swing.

Completion of the map editor ties in with completion of the database model editor.

The initial running build will only support sequence animation (refer to FF Mystic Quest to see what that looks like). Later builds will add support for 2d skeletal / non-skeletal animation for effects.

Scripting
=======

The project uses a modified version of FScript (tweaked to use float instead of double and allows repeated "." notations).
