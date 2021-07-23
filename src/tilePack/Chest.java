package tilePack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import inventoryPack.Inventory;
import mainGame.WindowGame;

public class Chest extends Tile{
	private boolean open = false;
	private Inventory inventory;
	public Chest(String name, Image img,GameContainer container) {
		super(name, img);
		this.setTag("Chest");
		this.inventory = new Inventory(container,true,9,4);
		
	}
	
	
	@Override 
	public void update(GameContainer container, int delta){
		Input input = container.getInput();
		if(WindowGame.cursor.getTileXIndex() == this.getX() &&
		   WindowGame.cursor.getTileYIndex() == this.getY()) {
			if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
				this.setOpen(!this.isOpen());
				this.inventory.setOpen(!this.inventory.isOpen());
				if(this.isOpen()) {
					WindowGame.player.getInventory().setOpen(true);
					WindowGame.currentInv = this.inventory;
				}
				else {
					WindowGame.currentInv = null;
				}
			}

		}


	}
	@Override
	public void render(Graphics g, GameContainer container) {
		g.drawImage(this.img,this.xdraw,this.ydraw);

			
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}


	public Inventory getInventory() {
		return inventory;
	}


	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}



}
