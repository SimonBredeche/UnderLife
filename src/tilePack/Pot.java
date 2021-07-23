package tilePack;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import asset.AssetManager;
import mainGame.World;

public class Pot extends Tile{

	public Pot(String name, ArrayList<Image> Frames) {
		super(name, Frames.get(0));
		
		// TODO Auto-generated constructor stub
	}
	public Pot(String name, Image Frames) {
		super(name, Frames);
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		if(World.getTileFromChunk(this.getX(), this.getY()+1) != null) {
			if(World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt") ||
					World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals(this.getName())	) {
				Tile temp = new BasicTile("Voidt",AssetManager.voidimg);
				temp.setTag("transparent");
				World.setTileFromIndex(this.getX(), this.getY(), temp );
			}
		}
	}



}
