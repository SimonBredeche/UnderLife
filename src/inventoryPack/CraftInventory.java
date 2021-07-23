package inventoryPack;

import java.util.ArrayList;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import armorPack.ArmorManager;
import asset.AssetManager;
import asset.ItemManager;
import asset.TileManager;
import mainGame.WindowGame;

public class CraftInventory implements ComponentListener {
	private boolean open = false;
	public static ArrayList<Craft> allCraft = new ArrayList<>();
	public static ArrayList<MouseOverArea> allCraftArea = new ArrayList<>();
	public CraftInventory() {
		// TODO Auto-generated constructor stub
	}
	public void loadCraft(GameContainer container) {
		/**
		 * IRON INGOT
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
							add(new InventorySlot(TileManager.ironore,2));
						}},
						ItemManager.ironingot,TileManager.Furnace));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		
		/**
		 * Crafting table
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.planks,4));
		}},
		ItemManager.Crafter));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		
		/**
		 * Stone brick (back)
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.stone,1));
		}},
		ItemManager.stoneback, TileManager.blockbuilder,4));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		
		
		/**
		 * Stone brick
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.stone,1));
		}},
		ItemManager.stonebrick, TileManager.blockbuilder));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		
		/**
		 * Furnace
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.stonebrick,9));
		}},
		ItemManager.furnace, TileManager.craftingtable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Anvil
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.ironingot,4));
		}},
		ItemManager.anvil, TileManager.craftingtable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Jungle pickaxe
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.wood,2));
			add(new InventorySlot(ItemManager.ironingot,4));
		}},
		ItemManager.Emeraldpick, TileManager.anvil));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * ame pickaxe
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.amethystcrystal,6));
			add(new InventorySlot(ItemManager.ironingot,4));
		}},
		ItemManager.amepick, TileManager.anvil));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Wooden door
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.wood,2));
		}},
		ItemManager.woodendoor,TileManager.blockbuilder));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Jungle bow
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.dirt,1));
		}},
		ItemManager.junglebow,TileManager.archery));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Iron helmet
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.ironingot,4));
		}},
		ArmorManager.ironset.getHelmet(),TileManager.anvil));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Iron Chest plate
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.ironingot,6));
		}},
		ArmorManager.ironset.getChestplate(),TileManager.anvil));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Iron boots
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.ironingot,5));
		}},
		ArmorManager.ironset.getBoots(),TileManager.anvil));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Gold helmet
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.amethystcrystal,4));
		}},
		ArmorManager.goldset.getHelmet(),TileManager.anvil));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Gold Chest plate
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.amethystcrystal,6));
		}},
		ArmorManager.goldset.getChestplate(),TileManager.anvil));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Gold boots
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.amethystcrystal,4));
		}},
		ArmorManager.goldset.getBoots(),TileManager.anvil));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Chest
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.planks,5));
			add(new InventorySlot(ItemManager.ironingot,5));

		}},
		ItemManager.chest,TileManager.blockbuilder));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * wood planks
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.wood,1));
		}},
		ItemManager.planks,4));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * wood planks back
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.planks,1));
		}},
		ItemManager.planksback,TileManager.blockbuilder,4));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Stone arrow
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.stone,1));
		}},
		ItemManager.arrows,TileManager.archery,4));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Iron staff
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.ironingot,4));
		}},
		ItemManager.ironstaff,TileManager.magictable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Healing potion
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.dirt,1));
		}},
		ItemManager.healingpotion,TileManager.alchemytable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Mana potion
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.dirt,1));
		}},
		ItemManager.manapotion,TileManager.alchemytable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Ame staff
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			//add(new InventorySlot(ItemManager.amethystcrystal,10));
			//add(new InventorySlot(ItemManager.ironingot,10));
			add(new InventorySlot(TileManager.dirt,1));
		}},
		ItemManager.amestaff));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Jungle statue
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.amethystcrystal,1));
			add(new InventorySlot(TileManager.wood,1));
			add(new InventorySlot(ItemManager.ironingot,1));
		}},
		ItemManager.junglestatue,TileManager.totem));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Totem
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.stone,4));
			add(new InventorySlot(ItemManager.star,1));
			add(new InventorySlot(ItemManager.heart,1));
		}},
		ItemManager.totem,TileManager.alchemytable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Alchemy table
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.planks,4));
			add(new InventorySlot(ItemManager.ironingot,2));
		}},
		ItemManager.alchemy,TileManager.craftingtable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Archery table
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.planks,4));
			add(new InventorySlot(ItemManager.ironingot,2));
		}},
		ItemManager.archery,TileManager.craftingtable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Magic table
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(ItemManager.star,1));
			add(new InventorySlot(ItemManager.amethystcrystal,4));
		}},
		ItemManager.magic,TileManager.alchemytable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		/**
		 * Block builder
		 * */
		allCraft.add(new Craft(new ArrayList<InventorySlot>(){{
			add(new InventorySlot(TileManager.stone,4));
		}},
		ItemManager.blockbuilder,TileManager.craftingtable));
		allCraftArea.add(new MouseOverArea(container,AssetManager.slot,0,0,this));
		//END CRAFTING
	}
	
	
	public void render(Graphics g,GameContainer container) {
		if(this.isOpen()) {
			int basey = 70;
			for(int i = 0; i < allCraft.size(); i++) {
				boolean render = false;
				if(allCraft.get(i).getCraftingStation().equals("none")) 
					render = true;
				else if(allCraft.get(i).getCraftingStation().equals(WindowGame.player.getCurrentStation()))
					render = true;
				
				if(render) {
					allCraftArea.get(i).setX(1700);
					allCraftArea.get(i).setY(basey);
					allCraftArea.get(i).render(container, g);
					allCraft.get(i).render(g, 1700, basey);
					basey += 78;
				}
			}

		}
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	@Override
	public void componentActivated(AbstractComponent source) {
		
		for(int i = 0; i < allCraftArea.size();i++) {
			if(source == allCraftArea.get(i)) {
				if(WindowGame.cursor.getItem() == null && WindowGame.cursor.getTile() == null) {
					if(allCraft.get(i).asRequiredItem(WindowGame.player.getInventory())) {
						allCraft.get(i).removeItem(WindowGame.player.getInventory());
						WindowGame.cursor.setItem(allCraft.get(i).getProducedItem());
						WindowGame.cursor.setQuantity(allCraft.get(i).getQuantity());
					}
				}
				else if(WindowGame.cursor.getItem() != null){
					if(allCraft.get(i).asRequiredItem(WindowGame.player.getInventory())) {
						if(WindowGame.cursor.getItem().getName().equals(allCraft.get(i).getProducedItem().getName())) {
							WindowGame.cursor.setQuantity(WindowGame.cursor.getQuantity()+allCraft.get(i).getQuantity());
							allCraft.get(i).removeItem(WindowGame.player.getInventory());
						}
					}
				}
				//allCraft.get(i).setShow(!allCraft.get(i).isShow());
			}
		}
		// TODO Auto-generated method stub
		
	}

}
