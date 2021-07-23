package tilePack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import asset.AssetManager;
import asset.TileManager;
import entityPack.Entity;
import mainGame.World;




public abstract class Tile implements Cloneable{
	protected int x,y;
	protected int xdraw, ydraw;
	protected String name;
	protected int harvestLevel = 0;
	protected int ID;
	protected Image img;
	protected Image baseimg;
	protected int width, height;
	protected String tag = "solid";
	protected Rectangle rect;
	protected boolean renderRect = false;
	protected int durability = 100;
	protected boolean reverse = false;
	public Tile(String name, Image img) {
		this.name = name;
		this.img = img;
		this.baseimg = img;
		this.baseimg.setFilter(Image.FILTER_NEAREST);
		this.img.setFilter(Image.FILTER_NEAREST);
		//this.img = this.img.getScaledCopy(2);
		this.width = this.img.getWidth();
		this.height = this.img.getHeight();
		this.rect = AssetManager.rect1;
		
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTag() {
		return this.tag;
	}
	public void render(Graphics g, GameContainer container) {
		g.drawImage(this.img,this.xdraw,this.ydraw);
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	
	public void update(GameContainer container, int delta) {
		//UPDATE METHOD
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}


	public int getXdraw() {
		return xdraw;
	}


	public void setXdraw(int xdraw) {
		this.xdraw = xdraw;
		//this.setRect(new Rectangle(this.xdraw,this.ydraw,img.getWidth(),img.getHeight()));
	}


	public int getYdraw() {
		return ydraw;
	}


	public void setYdraw(int ydraw) {
		this.ydraw = ydraw;
		//this.setRect(new Rectangle(this.xdraw,this.ydraw,img.getWidth(),img.getHeight()));
	}
	public void OnDestroy() {
		Entity temp = new Entity(this.img.getScaledCopy(0.25f),this.name);
		temp.setX((xdraw+this.width/2) - temp.getImage().getWidth()/2);
		temp.setY((ydraw+this.height/2) - temp.getImage().getHeight()/2);
		World.addEntity(temp);
		Tile tile = new BasicTile("Voidt",AssetManager.voidimg);
		tile.setTag("transparent");
		World.setTileFromIndex(this.x,this.y, tile);
	}

	public Rectangle getRect() {
		this.rect.setX(this.xdraw);
		this.rect.setY(this.ydraw);
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public boolean isRenderRect() {
		return renderRect;
	}

	public void setRenderRect(boolean renderRect) {
		this.renderRect = renderRect;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		TileManager.dictionnary[ID] = this;
		this.ID = ID;
	}

	public int getHarvestLevel() {
		return harvestLevel;
	}

	public void setHarvestLevel(int harvestLevel) {
		this.harvestLevel = harvestLevel;
	}

}
