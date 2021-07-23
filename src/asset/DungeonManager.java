package asset;

import structurePack.Dungeon;

public class DungeonManager {
	public static Dungeon Map1, Dungeon1, tree1, tree2,crystaltemple;
	public DungeonManager() {
		// TODO Auto-generated constructor stub
	}
	public static void loadDungeon() {
		Map1 = new Dungeon("Map1",10,10);
		Dungeon1 = new Dungeon("Dungeon1",10,10);
		tree1 = new Dungeon("tree1",5,8);
		tree2 = new Dungeon("tree2",5,8);
		crystaltemple = new Dungeon("crystal",48,48);
	}

}
