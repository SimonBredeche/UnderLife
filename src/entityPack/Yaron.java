package entityPack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.geom.Rectangle;

import asset.ProjectileManager;
import inventoryPack.Projectile;
import mainGame.WindowGame;
import mainGame.World;

public class Yaron extends Boss{
	private int phase = 0;
	private int runningtime = 0;
	private int rotation = 0;
	public Yaron(Animation mobAnim, String name, Rectangle rect, int life, boolean pacific, float speed,Music music,int damage) {
		super(mobAnim, name, rect, life, pacific, speed,music,damage);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void aggroAI(int delta, double distance) {
		this.runningtime += delta;
		for(int i = 0; i < this.mobAnim.getFrameCount(); i++) {
			this.mobAnim.getImage(i).setRotation(rotation);
		}
		if(distance < detectionrange && phase == 0) {
			this.tick++;
			
			try {
				if(this.tick > 25) {
					for(int i = -1; i < 6; i++) {
						Projectile temp = (Projectile) ProjectileManager.flaironshoot.clone();
						temp.setAlly(false);
						int x = (int)this.x+this.mobAnim.getImage(0).getWidth()/2;
						int y = (int)this.y+this.mobAnim.getImage(0).getHeight()/2;
						int pointx = (int)(WindowGame.player.getX());
						int pointy = (int)(WindowGame.player.getY());
						temp.initProjectile(x, y, pointx+i*World.tileSize, pointy+i*World.tileSize);
						World.allEntity.add(temp);
					}
					this.tick = 0;
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			if(this.runningtime > 10000) {
				this.runningtime = 0;
				this.phase = 1;
			}
			
		}
		else {
			if(this.x < WindowGame.player.getX()) {
				this.setDirection(3);
				//this.rotation = -10;
				this.updateposition(delta);
			}
			if(this.x > WindowGame.player.getX()) {
				this.setDirection(1);
				//this.rotation = 10;
				this.updateposition(delta);
			}
			
			if((this.y - WindowGame.player.getY()) > 64) {
				this.setDirection(2);
				this.updateposition(delta);
			}
			if( this.y > WindowGame.player.getY()) {
				this.setDirection(0);
			}
			
			if(this.runningtime > 10000) {
				this.runningtime = 0;
				this.phase = 0;
				this.rotation = 0;
			}
			this.updateposition(delta);
		}

	}
	

	
	@Override
	public void render(Graphics g) {
		g.drawAnimation(mobAnim, x, y);
		g.setColor(Color.green);
		g.fillRect(this.x, this.y, ((float)this.actuallife/(float)this.maxLife) * BARWIDHT, BARHEIGHT);
		
		g.drawImage(barimg, x-2, y-2);
		g.setColor(Color.white);
		
	}



}
