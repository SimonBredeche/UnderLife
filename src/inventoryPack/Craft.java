package inventoryPack;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import asset.AssetManager;

import tilePack.Tile;


public class Craft {
	
	private boolean show = true;
	private ArrayList<InventorySlot> neededItems;
	private Item producedItem;
	private Image SlotImage;
	private String craftingStation = "none";
	private int quantity = 1;

	public Craft(ArrayList<InventorySlot> neededItems,Item producedItem,Tile crafter) {
		this.neededItems = neededItems;
		this.producedItem = producedItem;
		this.SlotImage = AssetManager.slot;
		this.craftingStation = crafter.getName();
		// TODO Auto-generated constructor stub
	}
	public Craft(ArrayList<InventorySlot> neededItems,Item producedItem,Tile crafter,int quantity) {
		this.neededItems = neededItems;
		this.producedItem = producedItem;
		this.SlotImage = AssetManager.slot;
		this.craftingStation = crafter.getName();
		this.quantity = quantity;
		
		// TODO Auto-generated constructor stub
	}
	public Craft(ArrayList<InventorySlot> neededItems,Item producedItem) {
		this.neededItems = neededItems;
		this.producedItem = producedItem;
		this.SlotImage = AssetManager.slot;

		// TODO Auto-generated constructor stub
	}
	public Craft(ArrayList<InventorySlot> neededItems,Item producedItem, int quantity) {
		this.neededItems = neededItems;
		this.producedItem = producedItem;
		this.SlotImage = AssetManager.slot;
		this.quantity = quantity;

		// TODO Auto-generated constructor stub
	}
	
	public boolean asRequiredItem(Inventory inventory) {
		for(int i = 0; i < neededItems.size(); i++) {
			if(!inventory.isItemPresent(neededItems.get(i).getCurrentitem().getName(), neededItems.get(i).getQuantity())) {
				return false;
			}
		}
		return true;
	}
	public void removeItem(Inventory inventory) {
		for(int i = 0; i < neededItems.size(); i++) {
			inventory.RemoveItem(neededItems.get(i).getCurrentitem().getName(), neededItems.get(i).getQuantity());
			
		}
	}
	public void render(Graphics g, int x, int y) {
		int xdraw = x+(32-this.producedItem.getImage().getWidth()/2)+2;
		int ydraw = y+(32-this.producedItem.getImage().getHeight()/2)+2;
		g.drawImage(producedItem.getImage(), xdraw+2, ydraw+2);
		g.drawString(Integer.toString(quantity),x,y);
		if(show) {
			int basex = x-50-64;
			for(InventorySlot i : this.neededItems) {
				g.drawImage(SlotImage, basex, y);
				i.render(g, basex, y);
				
				basex -= 78;
			}
		}
	}
	public ArrayList<InventorySlot> getNeededItems() {
		return neededItems;
	}
	public void setNeededItems(ArrayList<InventorySlot> neededItems) {
		this.neededItems = neededItems;
	}
	public Item getProducedItem() {
		return producedItem;
	}
	public void setProducedItem(Item producedItem) {
		this.producedItem = producedItem;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getCraftingStation() {
		return craftingStation;
	}

	public void setCraftingStation(String craftingStation) {
		this.craftingStation = craftingStation;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
