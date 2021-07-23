package weaponPack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import asset.ProjectileManager;
import inventoryPack.MagicWeapon;
import inventoryPack.Projectile;
import mainGame.WindowGame;
import mainGame.World;

public class IronStaff extends MagicWeapon{

	public IronStaff(Image image, String name, boolean place, int delay, int manacost) {
		super(image, name, place, delay, manacost);
		
	}
	
	@Override
	public void update(GameContainer gc,int delta) {
		Input input = gc.getInput();
		this.runtime += delta;
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if(WindowGame.player.getMana() > this.manacost) {
				if(this.runtime > delay) {
					try {
						
						Projectile temp = (Projectile) ProjectileManager.flaironshoot.clone();
						temp.setAlly(true);
						int x = (int)WindowGame.player.getX();
						int y = (int)WindowGame.player.getY();
						int pointx = WindowGame.cursor.getTileXIndex()*World.tileSize;
						int pointy = WindowGame.cursor.getTileYIndex()*World.tileSize;
						temp.initProjectile(x, y, pointx, pointy);
						WindowGame.player.setMana(WindowGame.player.getMana()-this.manacost);
						World.allEntity.add(temp);
						
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
