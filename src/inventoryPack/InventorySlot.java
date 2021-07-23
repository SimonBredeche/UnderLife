package inventoryPack;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


import asset.AssetManager;
import tilePack.Tile;

public class InventorySlot {
	private Image image;
	private int quantity = 1;
	private final int maxquantity = 999;
	private Item currentitem;
	private int x,y;

	public InventorySlot( int x, int y) {
		this.image = AssetManager.slot;
		this.x = x;
		this.y = y;

		// TODO Auto-generated constructor stub
	}
	public InventorySlot() {
		this.image = AssetManager.slot;
	}
	public InventorySlot(Tile tile,int quantity) {
		this.currentitem = new Item(tile.getImg(),tile.getName(),true);
		this.quantity = quantity;
	}
	public InventorySlot(Item item, int quantity) {
		this.currentitem = item;
		this.quantity = quantity;
	}

	public Image getImg() {
		return this.image;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void render(Graphics g) {
		if(this.currentitem != null) {
			int xdraw = x+(32-this.currentitem.getImage().getWidth()/2)+2;
			int ydraw = y+(32-this.currentitem.getImage().getHeight()/2)+2;
			g.drawImage(this.currentitem.getImage(), xdraw , ydraw);
			g.drawString(Integer.toString(this.quantity), x, y);
		}
	}
	public void render(Graphics g,int x,int y) {
		if(this.currentitem != null) {
			int xdraw = x+(32-this.currentitem.getImage().getWidth()/2)+2;
			int ydraw = y+(32-this.currentitem.getImage().getHeight()/2)+2;
			g.drawImage(this.currentitem.getImage(), xdraw, ydraw);
			g.drawString(Integer.toString(this.quantity), x, y);
		}
	}
	public void setQuantity(int quantity) {
		if(quantity <= maxquantity) {
			this.quantity = quantity;
		}
	}
	public Item getCurrentitem() {
		return currentitem;
	}
	public void setCurrentitem(Item currentitem) {
		this.currentitem = currentitem;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


}
