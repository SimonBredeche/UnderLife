package mainGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import asset.SoundManager;
import asset.TileManager;
import inventoryPack.Item;
import tilePack.Chest;
import tilePack.Door;
import tilePack.Tile;
import weaponPack.Pickaxe;



public class Cursor {

	float x,y;
	private Image image;
	private int angle = 0;
	private boolean placing = false;
	private boolean breaking = false;
	private boolean breakingore = false;
	private Tile tile;
	private int quantity  = 1;
	private boolean dragging = false;
	private Item item;
	private int currentEfficiency = 0;
	int tileSize = 64;

	public Cursor(Image image) {
		this.image = image;
	}
	  public void updateCursor(GameContainer container, double distance,int delta) {
		  Input input = container.getInput();

	    	if(this.getItem() == null) {
	    		this.setCurrentEfficiency(0);
	    		this.setQuantity(1);
	    	}

	    	if(!WindowGame.player.getInventory().isOpen()) {
	    		if(this.item != null) {
	    	    	if(this.getItem().getTag().equals("Pickaxe")) {
	    	    		Pickaxe pick = (Pickaxe)this.getItem();
	    	    		this.setCurrentEfficiency(pick.getEfficiency());
	    	    	}
	    	    	else {
	    	    		this.setCurrentEfficiency(0);
	    	    	}
	    	    	this.getItem().update(container,delta);
	    	    	/*if(this.getItem().getTag().equals("FiringWeapon")) {
	    	    		this.getItem().update(container,delta);
	    	    	}*/
	
	    		}
	    		if(this.getTile() != null) {
	    			
	    			if(this.getTile().isReverse()) {
		    			if (input.isKeyPressed(Input.KEY_R)) {
		    				if(this.getTile() instanceof Door) {
		    					Door door = (Door)this.getTile();
		    					door.reverseImage();
		    					
		    				}
		    				if(this.getTile().getImg().getRotation() == 180)
		    					this.getTile().getImg().setRotation(0);
		    				else
		    					this.getTile().getImg().setRotation(180);
		    			}
	    			}
	    		}
				if(this.isBreaking()) {
					SoundManager.pioche.stop();
					SoundManager.blocbreak.play();
					World.getTileFromChunk(this.getTileXIndex(), this.getTileYIndex()).OnDestroy();
					this.setBreaking(false);
					
				}

				if(this.isPlacing() && distance < 2) {
					boolean consume = false;
					if(this.getTile() != null) {
						this.setCurrentEfficiency(0);
							if(this.getTile().getTag().equals("Chest")) {
								Chest temp = (Chest)this.getTile();
								Chest newChest = new Chest("Chest",temp.getImg(),container);
								int tempxdraw = this.getTileXIndex()*World.tileSize;
								int tempydraw = this.getTileYIndex()*World.tileSize;
								newChest.setX(this.getTileXIndex());
								newChest.setY(this.getTileYIndex());
								newChest.setYdraw(tempydraw);
								newChest.setXdraw(tempxdraw);
								if (World.getTileFromChunk(this.getTileXIndex(), this.getTileYIndex()).getName().equals("Voidt")){
									World.setTileFromIndex(this.getTileXIndex(), this.getTileYIndex(), newChest);
									consume = true;
								}
							}
							else {
								try {
									Tile temp = (Tile) this.getTile().clone();
									int tempxdraw = this.getTileXIndex()*World.tileSize;
									int tempydraw = this.getTileYIndex()*World.tileSize;
									temp.setX(this.getTileXIndex());
									temp.setY(this.getTileYIndex());
									temp.setYdraw(tempydraw);
									temp.setXdraw(tempxdraw);
									if(this.getTile().getTag().equals("background")) {
										if(World.getTileFromChunkBack(this.getTileXIndex(), this.getTileYIndex()) == null){
											World.setTileFromIndexBack(this.getTileXIndex(), this.getTileYIndex(), temp);
											consume = true;
										}
	
									}
									else if (World.getTileFromChunk(this.getTileXIndex(), this.getTileYIndex()).getName().equals("Voidt")){
										World.setTileFromIndex(this.getTileXIndex(), this.getTileYIndex(), temp);
										consume = true;
									}
									
								} catch (CloneNotSupportedException e) {
									e.printStackTrace();
								}
							}
							this.setPlacing(false);
							if(consume)
								WindowGame.player.getInventory().RemoveItem(this.getTile().getName(), 1);
					}
				}

			}
	    	
	    	
	    	if(WindowGame.player.getInventory().getCurrentInventorySlot().getCurrentitem() != null && !WindowGame.player.getInventory().isOpen()) {
	    		if(WindowGame.player.getInventory().getCurrentInventorySlot().getCurrentitem().isPlace()) {
					WindowGame.cursor.setTile(TileManager.findTileByName(WindowGame.player.getInventory().getCurrentInventorySlot().getCurrentitem().getName()));
					WindowGame.cursor.setItem(null);
	    		}
	    		else {
	    			WindowGame.cursor.setItem(WindowGame.player.getInventory().getCurrentInventorySlot().getCurrentitem());
	    			WindowGame.cursor.setTile(null);
	    			
	    		}
	    		WindowGame.cursor.setQuantity(WindowGame.player.getInventory().getCurrentInventorySlot().getQuantity());
	    	}
	    	else {
	    		if(!WindowGame.player.getInventory().isOpen()) {
	    			WindowGame.cursor.setTile(null);
	    			WindowGame.cursor.setItem(null);
	    		}
	    	}
	    		
			this.x = (Mouse.getX()-(container.getWidth() / 2 - (int) WindowGame.player.getX()))-this.tileSize/2;
			this.y = ((container.getHeight() - (int)Mouse.getY())-(container.getHeight() / 2 - (int) WindowGame.player.getY()))-this.tileSize/2;
	    }

	public void Draw(Graphics g) {
			int drawx = Math.round(this.x/this.tileSize)*this.tileSize;
			int drawy = Math.round(this.y/this.tileSize)*this.tileSize;
			if(this.item != null) {
				g.drawImage(item.getImage(), drawx, drawy);
				g.drawString(Integer.toString(this.quantity), drawx, drawy);
				
			}
			if(this.tile != null) {
				g.drawImage(tile.getImg(), drawx, drawy);
				g.drawString(Integer.toString(this.quantity), drawx, drawy);
			}
			g.drawImage(this.image, drawx, drawy);
		
	}
	public void drawInnerCursor(Graphics g,GameContainer container) {
		Input input = container.getInput();
		int drawx = input.getAbsoluteMouseX();
		int drawy = input.getAbsoluteMouseY();
		if(this.item != null) {
			g.drawImage(item.getImage(), drawx, drawy);
			g.drawString(Integer.toString(this.quantity), drawx, drawy);
			
		}
		if(this.tile != null) {
			g.drawImage(tile.getImg(), drawx, drawy);
			g.drawString(Integer.toString(this.quantity), drawx, drawy);
		}

	}

	public Image getImage() {
		return this.image;
	}
	public int getTileXIndex() {
		return Math.round(this.x/this.tileSize);
	}
	public int getTileYIndex() {
		return Math.round(this.y/this.tileSize);
	}

	public boolean isPlacing() {
		return placing;
	}

	public void setPlacing(boolean placing) {
		this.placing = placing;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		if(this.angle >= 360) {
			this.angle = 0;
		}
		else
			this.angle = angle;
	}

	public boolean isBreaking() {
		return breaking;
	}

	public void setBreaking(boolean breaking) {
		this.breaking = breaking;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public boolean isBreakingore() {
		return breakingore;
	}

	public void setBreakingore(boolean breakingore) {
		this.breakingore = breakingore;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isDragging() {
		return dragging;
	}

	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}
	public int getCurrentEfficiency() {
		return currentEfficiency;
	}
	public void setCurrentEfficiency(int currentEfficiency) {
		this.currentEfficiency = currentEfficiency;
	}


}
