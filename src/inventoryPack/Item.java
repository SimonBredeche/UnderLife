package inventoryPack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import tilePack.Tile;

public class Item {
	private String name;
	private Image image;
	private boolean place;
	private String tag = "normal";
	public Item(Image image, String name,boolean place) {
		this.image = image;
		this.name = name;
		this.place = place;

	}
	public Item(Tile tile) {
		this.image = tile.getImg();
		this.name = tile.getName();
		this.place = true;

	}
	public Item(Projectile projectile) {
		this.image = projectile.getImage();
		this.name = projectile.getName();
		this.place = false;
		this.setTag("projectile");
	}
	

	
	public void update(GameContainer gc,int delta) {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isPlace() {
		return place;
	}

	public void setPlace(boolean place) {
		this.place = place;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

}
