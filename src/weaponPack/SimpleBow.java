package weaponPack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import inventoryPack.FiringWeapon;
import inventoryPack.Projectile;
import mainGame.WindowGame;
import mainGame.World;

public class SimpleBow extends FiringWeapon{
	
	public SimpleBow(Image image, String name, boolean place, int delay) {
		super(image, name, place,delay);
	}
	
	
	@Override
	public void update(GameContainer gc,int delta) {
		Input input = gc.getInput();
		this.runtime += delta;
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			this.assign();
			if(this.project != null) {
				if(runtime > this.delay) {
					try {
						Projectile temp = (Projectile) this.project.clone();
						int x = (int)WindowGame.player.getX();
						int y = (int)WindowGame.player.getY();
						int pointx = (WindowGame.cursor.getTileXIndex())*World.tileSize;
						int pointy = (WindowGame.cursor.getTileYIndex())*World.tileSize;
						temp.initProjectile(x, y, pointx, pointy);
						World.allEntity.add(temp);
						this.consume();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					this.runtime = 0;
					
				}
			}
			//new Projectile(this.shootImg,"arrow",(int)WindowGame.player.getX(),(int)WindowGame.player.getY(),AssetManager.arrow716);
		}
		
	}



}
