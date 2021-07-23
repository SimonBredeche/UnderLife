package tilePack;


import java.util.Random;

import org.newdawn.slick.Image;

import asset.AssetManager;
import asset.ItemManager;
import entityPack.Entity;
import mainGame.World;

public class TintedRock extends Tile{
	private Random r = new Random();
	public TintedRock(String name, Image img) {
		super(name, img);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void OnDestroy() {
		int rng = this.r.nextInt(4);
		Entity temp = null;
		if(rng < 2 ) {
			temp = new Entity(ItemManager.star.getImage().getScaledCopy(0.25f),ItemManager.star.getName());
	
		}
		else {
			temp = new Entity(ItemManager.heart.getImage().getScaledCopy(0.25f),ItemManager.heart.getName());
		}
		temp.setX((xdraw+this.width/2) - temp.getImage().getWidth()/2);
		temp.setY((ydraw+this.height/2) - temp.getImage().getHeight()/2);
		World.addEntity(temp);
		Tile tile = new BasicTile("Voidt",AssetManager.voidimg);
		tile.setTag("transparent");
		World.setTileFromIndex(this.x,this.y, tile);
	}
	



}
