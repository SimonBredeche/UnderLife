package tilePack;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import asset.AssetManager;
import asset.ItemManager;
import entityPack.Entity;
import mainGame.World;

public class Amethyst extends BasicTile{

	public Amethyst(String name, ArrayList<Image> Frames) {
		super(name, Frames);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void OnDestroy() {
		Entity temp;
		temp = new Entity(ItemManager.amethystcrystal.getImage().getScaledCopy(0.25f),ItemManager.amethystcrystal.getName());
		temp.setX((xdraw+this.width/2) - temp.getImage().getWidth()/2);
		temp.setY((ydraw+this.height/2) - temp.getImage().getHeight()/2);
		World.addEntity(temp);
		Tile tile = new BasicTile("Voidt",AssetManager.voidimg);
		tile.setTag("transparent");
		World.setTileFromIndex(this.x,this.y, tile);
	}


}
