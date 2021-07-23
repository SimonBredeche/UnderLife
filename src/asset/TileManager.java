package asset;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import tilePack.Amethyst;
import tilePack.BackgroundTile;
import tilePack.BasicTile;
import tilePack.Chest;
import tilePack.Crafter;
import tilePack.Door;
import tilePack.Leaves;
import tilePack.Platform;
import tilePack.Pot;
import tilePack.SingledTile;
import tilePack.Spawner;
import tilePack.SpawningStructure;
import tilePack.Spike;
import tilePack.Tile;
import tilePack.TintedRock;

public class TileManager {
	private static ArrayList<Tile> allTiles = new ArrayList<>();
	public static Tile voidt,dirt,stone,pot,jungleore,craftingtable, stoneback,stonebrick,Furnace,woodoor
						,wood, vineback,ironore, Abyssalite, blackstone,crystal,crystaldeposite, anvil,chest,
						planks,planksback,tintedrock,amethyst,jungleStatue,totem,archery,alchemytable,magictable,
						blockbuilder,woodplat,crystalbrick,crystalbrickback,crystalpillar,templeStatue,
						bluecrystal,spawner,spike;
	public static Tile[] dictionnary = new Tile[150];
	public TileManager() {
		
	}
	public static void loadTiles(GameContainer container) {
		woodplat = loadTiles(new Platform("woodplatform",AssetManager.platform));
		woodplat.setRect(AssetManager.platformrect);
		voidt = loadTiles(new BasicTile("Voidt",AssetManager.voidimg));
		voidt.setTag("transparent");
		amethyst = loadTiles(new Amethyst("Amethyst",AssetManager.amethyst));
		amethyst.setHarvestLevel(1);
		tintedrock = loadTiles(new TintedRock("Tinted rock",AssetManager.tintedrock));
		stone = loadTiles(new BasicTile("Stone",AssetManager.stoneimg));
		crystaldeposite = loadTiles(new Pot("Crystal deposite",AssetManager.crystaldeposite));
		crystaldeposite.setTag("transparent");
		crystal = loadTiles(new BasicTile("Crystal",AssetManager.Crystal));
		blackstone = loadTiles(new BasicTile("Black stone",AssetManager.blackstone));
		dirt = loadTiles(new BasicTile("Dirt",AssetManager.dirtimg));
		dirt.setDurability(50);
		jungleStatue = loadTiles(new SpawningStructure("Jungle statue",AssetManager.junglestatue,EntityManager.asmora));
		templeStatue = loadTiles(new SpawningStructure("Temple statue",AssetManager.yaronsummon,EntityManager.flairon));
		pot = loadTiles(new Pot("Pot", AssetManager.potimg));
		jungleore = loadTiles(new Leaves("Jungleore",AssetManager.jungleore));
		ironore = loadTiles(new BasicTile("Iron ore",AssetManager.ironore));
		pot.setTag("transparent");
		spike = loadTiles(new Spike("Spike",AssetManager.spike));
		spawner = loadTiles(new Spawner("Monster spawner",AssetManager.spawneranim));
		craftingtable = loadTiles(new Crafter("Crafting table",AssetManager.crafter));
		stoneback = loadTiles(new BackgroundTile("Stone back",AssetManager.stonebackground));
		vineback = loadTiles(new BackgroundTile("Vine back",AssetManager.vineback));
		stonebrick = loadTiles(new SingledTile("Stone brick",AssetManager.stonebrick));
		planks = loadTiles(new SingledTile("Wood plank",AssetManager.planks));
		planksback = loadTiles(new SingledTile("Wood plank back",AssetManager.planksback));
		crystalpillar = loadTiles(new SingledTile("Crystal pillar",AssetManager.crystalpillar));
		crystalpillar.setTag("transparent");
		bluecrystal = loadTiles(new SingledTile("Blue crystal",AssetManager.bluecrystal));
		bluecrystal.setTag("transparent");
		crystalbrick = loadTiles(new SingledTile("Crystal brick",AssetManager.crystalbrick));
		crystalbrickback = loadTiles(new BackgroundTile("Crystal brick back",AssetManager.crystalbrickback));
		Abyssalite = loadTiles(new SingledTile("Abyssalite",AssetManager.Abyssalite));
		Furnace = loadTiles(new Crafter("Furnace",AssetManager.furnace));
		anvil = loadTiles(new Crafter("Anvil",AssetManager.anvil)); 
		totem = loadTiles(new Crafter("totem",AssetManager.totem));
		archery = loadTiles(new Crafter("archery",AssetManager.archery));
		alchemytable = loadTiles(new Crafter("alchemytable",AssetManager.alchemy));
		magictable = loadTiles(new Crafter("magictable",AssetManager.crystalball));
		blockbuilder = loadTiles(new Crafter("blockbuidler",AssetManager.blockbuilder));
		woodoor = loadTiles(new Door("Wooden door",AssetManager.woodoor,container));
		wood = loadTiles(new SingledTile("wood",AssetManager.wood));
		chest = loadTiles(new Chest("Chest",AssetManager.chest,container));
		Abyssalite.setHarvestLevel(999);
		stonebrick.setHarvestLevel(1);
		setTileIDFront();
		setTileIDBack();
	}
	public static void setTileIDFront() {
		voidt.setID(0);
		dirt.setID(2);
		stone.setID(3);
		stonebrick.setID(4);
		jungleore.setID(5);
		Furnace.setID(6);
		pot.setID(7);
		craftingtable.setID(8);
		wood.setID(9);
		crystalbrick.setID(10);
		crystalpillar.setID(11);
		templeStatue.setID(12);
		spawner.setID(13);
		spike.setID(14);
		bluecrystal.setID(15);
		
	}
	public static void setTileIDBack() {
		stoneback.setID(65);
		vineback.setID(66);
		crystalbrickback.setID(67);
	}
	public static Tile loadTiles(Tile tile) {
		allTiles.add(tile);
		return tile;
	}
	public static Tile findTileByName(String name) {
		for(int i = 0; i < allTiles.size(); i++) {
			if(allTiles.get(i).getName().equals(name))
				return allTiles.get(i);
		}
		return null;
	}

}
