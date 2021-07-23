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

public class Asmora extends Boss{
	private int phase = 0;
	private int runningtime = 0,runningtime2 = 0;
	private int targetx = 0;
	private int targety = 0;
	private static final int DODGEX = 200;
	private static final int DODGEY = 200;
	public Asmora(Animation mobAnim, String name, Rectangle rect, int life, boolean pacific, float speed, Music music,int damage) {
		super(mobAnim, name, rect, life, pacific, speed, music,damage);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void aggroAI(int delta, double distance) {
		this.runningtime += delta;
		this.tick++;

		try {
			if(this.tick > 100) {
				for(int i = 0; i < 1; i++) {
					Projectile temp = (Projectile) ProjectileManager.slash.clone();
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

		
		if(phase == 1) {
			this.runningtime2 += delta;
			if(this.runningtime2 > 2000) {
				this.targetx = -this.targetx;
				this.targety = -this.targety;
				this.runningtime2 = 0;
			}
			
		}
		
		if(this.x < WindowGame.player.getX()+targetx*2) {
			this.setDirection(3);
			this.updateposition(delta);
		}
		if(this.x > WindowGame.player.getX()+targetx*2) {
			this.setDirection(1);
			this.updateposition(delta);
		}
		
		if(this.y < WindowGame.player.getY()+targety*2) {
			this.setDirection(2);
			this.updateposition(delta);
		}
		if( this.y > WindowGame.player.getY()+targety*2) {
			this.setDirection(0);
		}
		
		if(runningtime > 10000) {
			if(phase == 0) {
				this.setSpeed(1f);
				this.phase = 1;
				targetx = DODGEX;
				targety = DODGEY;
				this.targetx = -this.targetx*2;
				this.targety = -this.targety*2;
			}
			else {
				this.phase = 0;
				this.setSpeed(0.2f);
				targetx = 0;
				targety = 0;
			}
			this.runningtime = 0;
		}

		this.updateposition(delta);
	}
	

	
	@Override
	public void render(Graphics g) {
		g.drawAnimation(mobAnim, x, y);
		g.setColor(Color.green);
		g.fillRect(this.x, this.y, ((float)this.actuallife/(float)this.maxLife) * BARWIDHT, BARHEIGHT);
		
		g.drawImage(barimg, x-2, y-2);
		//g.draw(getRect());
		g.setColor(Color.white);
		
	}




}
