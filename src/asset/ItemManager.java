package asset;

import java.util.ArrayList;

import armorPack.ArmorManager;
import inventoryPack.Item;
import inventoryPack.Potion;
import inventoryPack.Up;
import weaponPack.Amestaff;
import weaponPack.IronStaff;
import weaponPack.Pickaxe;
import weaponPack.SimpleBow;

public class ItemManager {
	public static Item ironingot,Crafter,stoneback,stonebrick,furnace,Emeraldpick,woodendoor, slime, arrows, 
	junglebow,anvil,chest,planks,planksback, ironstaff,manapotion,healingpotion,star,heart,amethystcrystal,
	amestaff,junglestatue,totem,alchemy,archery,magic,amepick,blockbuilder,wood;
	public static ArrayList<Item> allItems = new ArrayList<>();
	public ItemManager() {
		// TODO Auto-generated constructor stub
	}
	public static void loadItems() {
		ArmorManager.loadArmotSet();
		ProjectileManager.loadProjectile();
		
		amethystcrystal = new Item(AssetManager.amethystcrystal.getImage(0),"Amethyst crystal",false);allItems.add(amethystcrystal );
		star = new Up(AssetManager.star,"Mana star",true);allItems.add(star);
		heart = new Up(AssetManager.heart,"Heart crystal",false);allItems.add(heart);
		manapotion = new Potion(AssetManager.manapotion,"Low mana potion",10,true);allItems.add(manapotion);
		healingpotion = new Potion(AssetManager.healpotion,"Low healing potion",25,false);allItems.add(healingpotion);
		ironingot = new Item(AssetManager.ironingot,"jungle ingot",false);allItems.add(ironingot);
		Crafter = new Item(TileManager.craftingtable);allItems.add(Crafter);
		anvil = new Item(TileManager.anvil);allItems.add(anvil);
		chest = new Item(TileManager.chest);allItems.add(chest);
		totem = new Item(TileManager.totem);allItems.add(totem);
		wood = new Item(TileManager.wood);allItems.add(wood);
		blockbuilder = new Item(TileManager.blockbuilder);allItems.add(blockbuilder);
		alchemy = new Item(TileManager.alchemytable);allItems.add(alchemy);
		archery = new Item(TileManager.archery);allItems.add(archery);
		magic = new Item(TileManager.magictable);allItems.add(magic);
		junglestatue = new Item(TileManager.jungleStatue); allItems.add(junglestatue);
		planks = new Item(TileManager.planks);allItems.add(planks);
		planksback = new Item(TileManager.planksback);allItems.add(planksback);
		stoneback = new Item(TileManager.stoneback);allItems.add(stoneback);
		stonebrick = new Item(TileManager.stonebrick);allItems.add(stonebrick);
		furnace = new Item(TileManager.Furnace);allItems.add(furnace);
		arrows = new Item(ProjectileManager.arrow);allItems.add(arrows);
		slime = new Item(AssetManager.slimeitem,"Slime ", false);allItems.add(slime);
		Emeraldpick = new Pickaxe(AssetManager.emeraldpick,"Jungle pickaxe",false,10);allItems.add(Emeraldpick);
		amepick = new Pickaxe(AssetManager.amepick,"Amethyst pickawe",false,20);allItems.add(amepick);
		junglebow = new SimpleBow(AssetManager.junglebow,"Jungle bow",false,750);allItems.add(junglebow);
		woodendoor = new Item(TileManager.woodoor);allItems.add(woodendoor);
		ironstaff = new IronStaff(AssetManager.IronStaff,"Iron staff",false,400,2);allItems.add(ironstaff);
		amestaff = new Amestaff(AssetManager.amestaff,"Amethyst staff",false,100,0);allItems.add(amestaff);
		
		
		
	}
	
	
	public static Item SearchItemByName(String name) {
		for(Item i : allItems) {
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}

}
