package armorPack;

import asset.AssetManager;

public class ArmorManager {
	public static ArmorSet ironset,goldset;
	public ArmorManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static void loadArmotSet() {
		ironset = new ArmorSet("Iron",AssetManager.ironhelmet,10,AssetManager.ironchestplate,10,AssetManager.ironboots,10);
		goldset = new ArmorSet("Gold",AssetManager.goldhelmet,20,AssetManager.goldchestplate,20,AssetManager.goldboots,20);
	}

}
