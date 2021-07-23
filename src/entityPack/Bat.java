package entityPack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import asset.AssetManager;
import asset.ProjectileManager;
import asset.TileManager;
import inventoryPack.Projectile;
import mainGame.WindowGame;
import mainGame.World;

public class Bat extends LivingEntity{
	int phase = 0;
	private Animation[] janganim = new Animation[4];
	public Bat(Animation mobAnim[], String name, Rectangle rect, int life, boolean pacific, float speed, int damage) {
		super(mobAnim[0], name, rect, life, pacific, speed, damage);
		this.janganim[0] = mobAnim[0];
		this.janganim[1] = mobAnim[1];
		this.janganim[2] = mobAnim[2];
		this.janganim[3] = mobAnim[3];
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pacificAI(int delta) {
		int rng =  r.nextInt(30);
		if(rng < 2) {
			this.direction = r.nextInt(4);
		}
		float futurY = getFuturY(delta);
		float futurX = getFuturX(delta);
        boolean collision = WindowGame.world.isCollision(1, 1, Math.round(futurX/World.getTileSize())/12, Math.round((futurY/World.getTileSize())/12),delta,futurX,futurY,this.getRect());
        if (collision) {
            
        } else {
            this.setX(futurX);
            this.setY(futurY);
            
        }
	}
	@Override
	public void aggroAI(int delta, double distance) {
		if(distance > detectionrange) {
			if(this.direction == 1) {
				this.phase = 0;
			}
			else if(this.direction == 3) {
				this.phase = 1;
			}
			pacificAI(delta);
		}
		else {
			if(this.direction == 1) {
				this.phase = 2;
			}
			else if(this.direction == 3) {
				this.phase = 3;
			}
			this.tick++;
			if(this.tick > 25) {
				try {
					Projectile temp;
					temp = (Projectile) ProjectileManager.batshoot.clone();
					temp.setAlly(false);
					int x = (int)this.x+this.mobAnim.getImage(0).getWidth()/2;
					int y = (int)this.y+this.mobAnim.getImage(0).getHeight()/2;
					int pointx = (int)(WindowGame.player.getX());
					int pointy = (int)(WindowGame.player.getY());
					temp.initProjectile(x, y, pointx, pointy);
					World.allEntity.add(temp);
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.tick = 0;
			}

		}
	}
	@Override
	public void update(int delta) {
		double detx = this.getX()/World.tileSize - WindowGame.player.getX()/World.tileSize;
		double dety = this.getY()/World.tileSize - WindowGame.player.getY()/World.tileSize;
		double distance = Math.sqrt(Math.pow(detx, 2) +  Math.pow(dety, 2));
		if(distance > 72) {
			this.setDestroy(true);
		} 
		if(this.actuallife <= 0) {
			this.OnKill();
		}
		if(this.getRect().intersects(WindowGame.player.getRect())) {
			WindowGame.player.dealDamage(this.Damage);
		}
		
		
		if(this.isPacific()) {
			this.pacificAI(delta);
		}
		else {
			this.aggroAI(delta,distance);
		}
			

		
	}
	
	@Override
	public void OnKill() {
		this.destroy = true;
		Entity temp = new Entity(AssetManager.wood.getScaledCopy(0.25f),TileManager.wood.getName());
		temp.setX((int)this.x);
		temp.setY((int)this.y);
		World.addEntity(temp);
	}
	@Override
	public void render(Graphics g) {

		if(phase == 0) {
			g.drawAnimation(this.janganim[0], x, y);
		}
		else if(phase == 1) {
			
			g.drawAnimation(this.janganim[1], x, y);
		}
		else if(phase == 2){
			g.drawAnimation(this.janganim[2], x, y);
		}
		else {
			g.drawAnimation(this.janganim[3], x, y);
		}
		
		g.setColor(Color.green);
		g.fillRect(this.x, this.y, ((float)this.actuallife/(float)this.maxLife) * BARWIDHT, BARHEIGHT);
		g.drawImage(barimg, x-2, y-2);
		g.setColor(Color.white);
		
	}



}
