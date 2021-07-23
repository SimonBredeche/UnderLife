package weaponPack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import asset.ProjectileManager;
import inventoryPack.MagicWeapon;
import inventoryPack.Projectile;
import mainGame.WindowGame;
import mainGame.World;

public class Amestaff extends MagicWeapon{

	public Amestaff(Image image, String name, boolean place, int delay, int manacost) {
		super(image, name, place, delay, manacost);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(GameContainer gc,int delta) {
		Input input = gc.getInput();
		this.runtime += delta;
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if(WindowGame.player.getMana() > this.manacost) {
				if(this.runtime > delay) {
					try {
						for(int i = -1; i < 2; i++) {
							Projectile temp = (Projectile) ProjectileManager.ameshoot.clone();
							temp.setAlly(true);
							int x = (int)(WindowGame.cursor.getTileXIndex()+i)*World.tileSize;
							int y = (int)WindowGame.player.getY()-604;
							int pointx = WindowGame.cursor.getTileXIndex()*World.tileSize;
							int pointy = WindowGame.cursor.getTileYIndex()*World.tileSize;
							temp.initProjectile(x, y, pointx, pointy);
							World.allEntity.add(temp);
						}
						WindowGame.player.setMana(WindowGame.player.getMana()-this.manacost);
						
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
