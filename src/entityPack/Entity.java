package entityPack;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import asset.AssetManager;

import mainGame.World;

public class Entity implements Cloneable{
	protected Image image;
	protected int x,y;
	protected String name;
	protected Rectangle rect;
	protected boolean destroy = false;
	protected boolean harvestable = true;
	private String tag = "item";
	public Entity(Image image, String name) {
		this.image = image;
		this.name = name;
		this.rect = AssetManager.rectEntity;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		if(World.getTileFromChunk(x/World.tileSize, (y+this.image.getHeight())/World.tileSize).getTag().equals("transparent")) {
			this.y = this.y + 14;
		}

	}
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	public void render(Graphics g) {
		g.drawImage(this.image, this.x, this.y);
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Rectangle getRect() {
		this.rect.setX(x);
		this.rect.setY(y);
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	public boolean isDestroy() {
		return destroy;
	}
	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
	}
	public boolean isHarvestable() {
		return harvestable;
	}
	public void setHarvestable(boolean harvestable) {
		this.harvestable = harvestable;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

}
