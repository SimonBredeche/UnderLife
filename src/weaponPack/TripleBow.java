package weaponPack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import asset.ProjectileManager;
import inventoryPack.FiringWeapon;
import inventoryPack.Projectile;
import mainGame.WindowGame;
import mainGame.World;

public class TripleBow extends FiringWeapon{

	public TripleBow(Image image, String name, boolean place,int delay) {
		super(image, name, place,delay);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void update(GameContainer gc,int delta) {
		Input input = gc.getInput();
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			try {
				for(int i = -1; i < 2; i++) {
					Projectile temp = (Projectile) ProjectileManager.arrow.clone();
					int x = (int)WindowGame.player.getX();
					int y = (int)WindowGame.player.getY();
					int pointx = (WindowGame.cursor.getTileXIndex()+i)*World.tileSize;
					int pointy = (WindowGame.cursor.getTileYIndex()+i)*World.tileSize;
					temp.initProjectile(x, y, pointx, pointy);
					World.allEntity.add(temp);
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			//new Projectile(this.shootImg,"arrow",(int)WindowGame.player.getX(),(int)WindowGame.player.getY(),AssetManager.arrow716);
		}
		
	}



}
