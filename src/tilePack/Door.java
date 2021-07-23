package tilePack;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import mainGame.WindowGame;

public class Door extends Tile {
	private Image openImg;
	private Image closeImg;
	private boolean open = false;
	private int rotation = 0;
	public Door(String name, ArrayList<Image> img,GameContainer container) {
		super(name, img.get(1));
		openImg = img.get(0);
		closeImg = img.get(1);
		this.setReverse(true);
		
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void render(Graphics g,GameContainer container) {
		if(this.isOpen()) {
			openImg.setRotation(this.rotation);
			g.drawImage(openImg, xdraw, ydraw);
		}
		else {
			closeImg.setRotation(this.rotation);
			g.drawImage(closeImg, xdraw, ydraw);
		}
	}
	@Override 
	public void update(GameContainer container, int delta){
		Input input = container.getInput();
		if(WindowGame.cursor.getTileXIndex() == this.getX() &&
		   WindowGame.cursor.getTileYIndex() == this.getY()) {
			if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
				this.setOpen(!this.isOpen());
			}
		}

	}
	public void reverseImage() {
		if(this.getRotation() == 180) {
			this.setRotation(0);
			this.setRotation(0);
		}
		else {
			this.setRotation(180);
			this.setRotation(180);
		}
	}


	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		if(open == false) {
			this.tag = "solid";
		}
		else {
			this.tag = "transparent";
		}
		this.open = open;
	}


	public int getRotation() {
		return rotation;
	}


	public void setRotation(int rotation) {
		this.rotation = rotation;
	}



}
