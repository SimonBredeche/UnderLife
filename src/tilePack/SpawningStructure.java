package tilePack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import asset.AssetManager;
import entityPack.LivingEntity;
import mainGame.WindowGame;
import mainGame.World;

public class SpawningStructure extends Tile{
	private LivingEntity spawningCreature;
	private int runtime = 0;
	public SpawningStructure(String name, Image img,LivingEntity entity) {
		super(name, img);
		this.spawningCreature = entity;
		this.setTag("transparent");
	}
	
	@Override 
	public void update(GameContainer container, int delta){
		if(runtime > 2000) {
			Input input = container.getInput();
			if(WindowGame.cursor.getTileXIndex() == this.getX() &&
			   WindowGame.cursor.getTileYIndex() == this.getY()) {
				if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
					WindowGame.world.spawnEntity(this.xdraw, this.ydraw-500, this.spawningCreature);
					Tile tile = new BasicTile("Voidt",AssetManager.voidimg);
					tile.setTag("transparent");
					World.setTileFromIndex(this.x,this.y, tile);
				}
			}
		}
		else {
			this.runtime += delta;
		}


	}



}
