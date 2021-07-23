package inventoryPack;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import armorPack.Armor;
import asset.AssetManager;
import asset.ItemManager;
import entityPack.Entity;
import mainGame.WindowGame;
import tilePack.Tile;


public class Inventory implements ComponentListener{
	private boolean open = false;
	private int selectedX = 0;
	private int selectedY = 0;
	private Image indexSelector;
	private InventorySlot[][] inventory = new InventorySlot[9][9];
	private MouseOverArea[][] inventoryArea = new MouseOverArea[9][9];
	private MouseOverArea[] armorArea = new MouseOverArea[4];
	private InventorySlot[] armor = new InventorySlot[4];
	private static final int BARWIDHT = 300;
	private static final int BARHEIGHT = 40;
	private final int x = 100, y = 100;
	private boolean chest;
	public InventorySlot getCurrentInventorySlot() {
		return this.inventory[selectedX][selectedY];
	}
	public InventorySlot[][] getInventory(){
		return this.inventory;
	}
	public InventorySlot getProjectile() {
		return this.armor[3];
	}
	public boolean isItemPresent(String name, int quantity) {
		for(int i  = 0; i < inventory.length; i++) {
			for(int j = 0; j < inventory[i].length; j++) {
				if(inventory[i][j].getCurrentitem() != null) {
					if(inventory[i][j].getCurrentitem().getName().equals(name) &&  inventory[i][j].getQuantity() >= quantity) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public Inventory(GameContainer container, boolean chest, int sizex, int sizey) {
		this.indexSelector = AssetManager.indexSelector;
		this.chest = chest;
		this.inventory = new InventorySlot[sizex][sizey];
		this.inventoryArea = new MouseOverArea[sizex][sizey];
		if(!chest) {
			for(int i  = 0; i < inventory.length; i++) {
				for(int j = 0; j < inventory[i].length; j++) {
					inventory[i][j] = new InventorySlot(x+(i*68),y+(j*68));
					inventoryArea[i][j] = new MouseOverArea(container,inventory[i][j].getImg(),x+(i*68),y+(j*68),this);
				}
			}
			int basey = 808;
			for(int i =0; i < this.armorArea.length; i++) {
				this.armorArea[i] = new MouseOverArea(container,inventory[0][0].getImg(),1700,basey,this);
				this.armor[i] = new InventorySlot(1700,basey);
				basey += 64;
			}
		}
		else {
			for(int i  = 0; i < inventory.length; i++) {
				for(int j = 0; j < inventory[i].length; j++) {
					inventory[i][j] = new InventorySlot(750+(i*68),y+(j*68));
					inventoryArea[i][j] = new MouseOverArea(container,inventory[i][j].getImg(),750+(i*68),y+(j*68),this);
				}
			}
		}
			

	}
	public void render(Graphics g, GameContainer container) {
		g.resetTransform();
		if(this.isOpen()) {
			for(int i  = 0; i < inventory.length; i++) {
				for(int j = 0; j < inventory[i].length; j++) {
					inventoryArea[i][j].render(container, g);
					inventory[i][j].render(g);
					if(this.selectedX == i && this.selectedY == j) {
						g.drawImage(indexSelector, inventoryArea[i][j].getX(), inventoryArea[i][j].getY());
					}
				}
			}
			if(!this.chest) {
				for(int i = 0; i < armor.length; i++) {
					armorArea[i].render(container, g);
					armor[i].render(g);
				}
				g.drawImage(AssetManager.shieldicon, 1095,0);
				g.setColor(Color.green);
				g.drawString(Integer.toString(WindowGame.player.getArmor()), 1095+(AssetManager.shieldicon.getWidth()/2), 0+(AssetManager.shieldicon.getHeight()/2));
				g.setColor(Color.white);
				
			}
		}else {
			if(!this.chest) {
				for(int i = 0; i < inventory.length; i++) {
					inventoryArea[i][0].render(container, g);
					inventory[i][0].render(g);
					if(this.selectedX == i && this.selectedY == 0) {
						g.drawImage(indexSelector, inventoryArea[i][0].getX(), inventoryArea[i][0].getY());
					}
				}
			}
		}

		if(!this.chest) {
			//HEALTH BAR
			g.drawImage(AssetManager.lifebar.getSubImage(0, 0, (int)(((float)WindowGame.player.getLife()/(float)WindowGame.player.getMaxlife()) * BARWIDHT), BARHEIGHT), 1504,8);
			g.drawImage(AssetManager.playerbar,1500,4);
			
			//MANA BAR
			g.drawImage(AssetManager.manabar.getSubImage(0, 0, (int)(((float)WindowGame.player.getMana()/(float)WindowGame.player.getMaxmana()) * BARWIDHT), BARHEIGHT), 1154,8);
			g.drawImage(AssetManager.playerbar,1150,4);
		}

	}
	public void addItem(Object o) {
		if(o instanceof Entity) {
			Entity temp = (Entity)o;
			ConvertEntitytoItem(temp);
			
		}
		else if(o instanceof Tile) {
			Tile temp = (Tile)o;
			ConvertTiletoItem(temp);
		}
	}
	
	public void RemoveItem(String name,int quantity) {
		for(int i  = 0; i < inventory.length; i++) {
			for(int j = 0; j < inventory[i].length; j++) {
				if(inventory[i][j].getCurrentitem() != null) {
					if(inventory[i][j].getCurrentitem().getName().equals(name)) {
						if(inventory[i][j].getQuantity()-quantity <= 0) {
							inventory[i][j].setCurrentitem(null);	
							inventory[i][j].setQuantity(1);
						}
						else {
							inventory[i][j].setQuantity(inventory[i][j].getQuantity()-quantity);
							
						}
					}
				}
			}
		}

	}
	public void ConvertEntitytoItem(Entity entity) {
		String name = entity.getName();
		boolean found = false;
		for(int i  = 0; i < inventory.length; i++) {
			for(int j = 0; j < inventory[i].length; j++) {
				if(inventory[i][j].getCurrentitem() != null) {
					if(inventory[i][j].getCurrentitem().getName().equals(name)) {
						found = true;
						inventory[i][j].setQuantity(inventory[i][j].getQuantity()+1);
					}
				}
			}
		}
		if(!found) {
			outerloop:
			for(int i  = 0; i < inventory.length; i++) {
				for(int j = 0; j < inventory[i].length; j++) {
					if(inventory[j][i].getCurrentitem() == null) {
						Item itemp = ItemManager.SearchItemByName(entity.getName());
						if(itemp != null) {
							
							inventory[j][i].setCurrentitem(itemp);
						}
						else {
							Item item = new Item(entity.getImage().getScaledCopy(4),entity.getName(),true);
							inventory[j][i].setCurrentitem(item);
						}
						break outerloop;
					}
				}
			}
		}
		
		
	}
	public void ConvertTiletoItem(Tile entity) {
		String name = entity.getName();
		boolean found = false;
		for(int i  = 0; i < inventory.length; i++) {
			for(int j = 0; j < inventory[i].length; j++) {
				if(inventory[i][j].getCurrentitem() != null) {
					if(inventory[i][j].getCurrentitem().getName().equals(name)) {
						found = true;
						inventory[i][j].setQuantity(inventory[i][j].getQuantity()+1);
					}
				}
			}
		}
		if(!found) {
			outerloop:
			for(int i  = 0; i < inventory.length; i++) {
				for(int j = 0; j < inventory[i].length; j++) {
					if(inventory[j][i].getCurrentitem() == null) {
						Item itemp = ItemManager.SearchItemByName(entity.getName());
						if(itemp != null) {
							
							inventory[j][i].setCurrentitem(itemp);
						}
						else {
							Item item = new Item(entity.getImg(),entity.getName(),true);
							inventory[j][i].setCurrentitem(item);
						}
						break outerloop;
					}
				}
			}
		}
		
		
	}
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		if(this.isOpen()) {
			if(WindowGame.cursor.getItem() != null) {
				this.getCurrentInventorySlot().setCurrentitem(WindowGame.cursor.getItem());
				this.getCurrentInventorySlot().setQuantity((WindowGame.cursor.getQuantity()));
			}
			this.selectedY = 0;
		}
		WindowGame.cursor.setTile(null);
		WindowGame.cursor.setItem(null);
		this.open = open;
		
	}
	@Override
	public void componentActivated(AbstractComponent source) {
		if(this.isOpen()) {
			for(int i  = 0; i < inventory.length; i++) {
				for(int j = 0; j < inventory[i].length; j++) {
					if(inventoryArea[i][j] == source) {
						if(inventory[i][j].getCurrentitem() != null && WindowGame.cursor.getItem() != null) {
							if(inventory[i][j].getCurrentitem().getName().equals(WindowGame.cursor.getItem().getName())) {
								inventory[i][j].setQuantity(inventory[i][j].getQuantity() + WindowGame.cursor.getQuantity());
								WindowGame.cursor.setQuantity(1);
								WindowGame.cursor.setItem(null);
							}
							else {
								InventorySlot temp = new InventorySlot();
								temp.setCurrentitem(inventory[i][j].getCurrentitem());
								temp.setQuantity(inventory[i][j].getQuantity());
								inventory[i][j].setCurrentitem(WindowGame.cursor.getItem());
								inventory[i][j].setQuantity(WindowGame.cursor.getQuantity());
								WindowGame.cursor.setQuantity(temp.getQuantity());
								WindowGame.cursor.setItem(temp.getCurrentitem());
								/*this.getCurrentInventorySlot().setCurrentitem(temp.getCurrentitem());
								this.getCurrentInventorySlot().setQuantity(temp.getQuantity());
								WindowGame.cursor.setQuantity(1);
								WindowGame.cursor.setItem(null);*/
							}
						}
						else if(inventory[i][j].getCurrentitem() != null && WindowGame.cursor.getItem() == null ) {
							WindowGame.cursor.setItem(inventory[i][j].getCurrentitem());
							WindowGame.cursor.setQuantity(inventory[i][j].getQuantity());
							inventory[i][j].setQuantity(1);
							inventory[i][j].setCurrentitem(null);
						}
						else if(inventory[i][j].getCurrentitem() == null){
							inventory[i][j].setQuantity(WindowGame.cursor.getQuantity());
							inventory[i][j].setCurrentitem(WindowGame.cursor.getItem());
							WindowGame.cursor.setQuantity(1);
							WindowGame.cursor.setItem(null);
						}
						this.selectedX = i;
						this.selectedY = j;
						
					}
				}
			}
			if(!this.chest) {
				//ARMOR SLOT MANAGEMENT
				if(WindowGame.cursor.getItem() != null) {
					if(armorArea[0] == source) {
						if(WindowGame.cursor.getItem().getTag().equals("Helmet")) {
							CursorManagement(0);
						}
					}
					else if(armorArea[1] == source) {
						if(WindowGame.cursor.getItem().getTag().equals("Chestplate")) {
							CursorManagement(1);
						}
					}
					else if(armorArea[2] == source) {
						if(WindowGame.cursor.getItem().getTag().equals("Boots")) {
							CursorManagement(2);
						}
					}
					else if(armorArea[3] == source) {
						if(WindowGame.cursor.getItem().getTag().equals("projectile")) {
							CursorManagement(3);
						}
					}
				}
				else {
					for(int i = 0; i < armorArea.length;i++) {
						if(source == armorArea[i]) {
							WindowGame.cursor.setQuantity(armor[i].getQuantity());
							WindowGame.cursor.setItem(armor[i].getCurrentitem());
							armor[i].setQuantity(1);
							armor[i].setCurrentitem(null);
							updateArmorValue();
						}
					}
				}
			}
		}
		
	}
	public void CursorManagement(int index) {
		if(armor[index].getCurrentitem() != null) {
			InventorySlot temp = new InventorySlot();
			temp.setCurrentitem(armor[index].getCurrentitem());
			temp.setQuantity(armor[index].getQuantity());
			armor[index].setCurrentitem(WindowGame.cursor.getItem());
			armor[index].setQuantity(WindowGame.cursor.getQuantity());
			WindowGame.cursor.setQuantity(temp.getQuantity());
			WindowGame.cursor.setItem(temp.getCurrentitem());
			updateArmorValue();
		}
		else {
			armor[index].setCurrentitem(WindowGame.cursor.getItem());
			armor[index].setQuantity(WindowGame.cursor.getQuantity());
			WindowGame.cursor.setQuantity(1);
			WindowGame.cursor.setItem(null);
			updateArmorValue();
		}
	}
	public void updateArmorValue() {
		int value = 1;
		for(int i = 0; i < armor.length-1;i++) {
			if(armor[i] != null && armor[i].getCurrentitem() != null) {
				Armor temp = (Armor)armor[i].getCurrentitem();
				value += temp.getArmorValue();
			}
		}
		WindowGame.player.setArmor(value);

	}
	public int getSelectedX() {
		return selectedX;
	}
	public void setSelectedX(int selectedX) {
		this.selectedX = selectedX;
	}
	public int getSelectedY() {
		return selectedY;
	}
	public void setSelectedY(int selectedY) {
		this.selectedY = selectedY;
	}
	

}
