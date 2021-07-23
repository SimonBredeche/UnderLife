package entityPack;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import asset.AssetManager;
import mainGame.WindowGame;
import mainGame.World;

public class LivingEntity implements Cloneable{
	protected float x = 0,y = 0;
	protected Animation mobAnim;
	protected String name;
	protected float Speed = 0.2f;
	protected int maxLife;
	protected int actuallife;
	protected Rectangle rect;
	protected int direction = 0;
	protected int tick = 0;
	protected Image barimg;
	protected int detectionrange = 5;
	protected boolean pacific =true;
	protected boolean destroy= false;
	protected static final int BARWIDHT = 75;
	protected static final int BARHEIGHT = 12;
	protected int Damage = 1;
	protected Random r = new Random();
	protected int rectx, recty;
	public LivingEntity(Animation mobAnim,String name, Rectangle rect, int life,boolean pacific, float speed,int damage) {
		// TODO Auto-generated constructor stub
		this.mobAnim = mobAnim;
		this.maxLife = life;
		this.actuallife = life;
		this.name = name;
		this.rect = rect;
		this.pacific = pacific;
		this.barimg = AssetManager.enemyBar;
		this.Speed = speed;
		this.Damage = damage;
		this.rectx = (int) (this.mobAnim.getImage(0).getWidth()/2-this.rect.getWidth()/2);
		this.recty = (int) (this.mobAnim.getImage(0).getHeight()/2-this.rect.getHeight()/2);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	public void setLife(int life) {
		if(life < 0)
			this.actuallife = 0;
		else
			this.actuallife = life;
	}
	public int getActualLife() {
		return this.actuallife;
	}
	public void OnKill() {
		this.destroy = true;
	}
	public boolean spawnConditions(int tilex, int tiley) {
		for(int i = -1; i < 2;i++) {
			for(int j = -1; j < 2;j++) {
				if(!World.getTileFromChunk(tilex+i,tiley+j).getName().equals("Voidt")) {
					return false;
				}
			}
		}
		return true;
		
		
	}
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
		
		if(this.isPacific()) {
			this.pacificAI(delta);
		}
		else {
			this.aggroAI(delta,distance);
		}
			
		
		
	}
	public void pacificAI(int delta) {
		int rng =  r.nextInt(10);
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
	public void aggroAI(int delta, double distance) {
		if(distance > detectionrange) {
			pacificAI(delta);
		}
		else {
			if(this.x < WindowGame.player.getX()) {
				this.setDirection(3);
				this.updateposition(delta);
			}
			if(this.y < WindowGame.player.getY()) {
				this.setDirection(2);
				this.updateposition(delta);
			}
			if(this.x > WindowGame.player.getX()) {
				this.setDirection(1);
				this.updateposition(delta);
			}
			if(this.y > WindowGame.player.getY()) {
				this.setDirection(0);
			}
			this.updateposition(delta);

		}
	}
	public void updateposition(int delta) {
		float futurY = getFuturY(delta);
		float futurX = getFuturX(delta);
        boolean collision = WindowGame.world.isCollision(1, 1, Math.round(futurX/World.getTileSize())/12, Math.round((futurY/World.getTileSize())/12),delta,futurX,futurY,this.getRect());
        if (collision) {
            
        } else {
            this.setX(futurX);
            this.setY(futurY);
            
        }
	}
	public void render(Graphics g) {
		g.drawAnimation(mobAnim, x, y);
		g.setColor(Color.green);
		g.fillRect(this.x, this.y, ((float)this.actuallife/(float)this.maxLife) * BARWIDHT, BARHEIGHT);
		g.drawImage(barimg, x-2, y-2);
		g.setColor(Color.white);
		
	}
    protected float getFuturX(int delta){
        float futurX = this.getX();
        switch (this.getDirection()) {
        case 1: futurX = (this.getX()) - this.getSpeed() * delta; break;
        case 3: futurX = (this.getX()) + this.getSpeed() * delta; break;
        }
        return futurX;
    }

    protected float getFuturY(int delta) {
        float futurY = this.getY();
        switch (this.getDirection()) {
	        case 0: futurY = (this.getY()) - this.getSpeed() * delta; break;
	        case 2 : futurY = (this.getY()) + this.getSpeed() * delta; break;
        }
        return futurY;
    }
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Animation getMobAnim() {
		return mobAnim;
	}
	public void setMobAnim(Animation mobAnim) {
		this.mobAnim = mobAnim;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Rectangle getRect() {
		this.rect.setX(this.x + this.rectx);
		this.rect.setY(this.y + this.recty);
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	public float getSpeed() {
		return Speed;
	}
	public void setSpeed(float speed) {
		Speed = speed;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getTick() {
		return tick;
	}
	public void setTick(int tick) {
		this.tick = tick;
	}

	public boolean isPacific() {
		return pacific;
	}

	public void setPacific(boolean pacific) {
		this.pacific = pacific;
	}

	public boolean isDestroy() {
		return destroy;
	}

	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public int getDetectionrange() {
		return detectionrange;
	}

	public void setDetectionrange(int detectionrange) {
		this.detectionrange = detectionrange;
	}

	public int getDamage() {
		return Damage;
	}

	public void setDamage(int damage) {
		Damage = damage;
	}

}
