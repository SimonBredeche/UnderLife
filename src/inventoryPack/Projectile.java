package inventoryPack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import asset.AssetManager;
import entityPack.Entity;
import mainGame.World;


public class Projectile extends Entity{
	private double angle;
	private double VelocityX;
	private double VelocityY;
	private int dispawn = 0;
	private double speed = 8;
	private int damage = 5;
	private float rotation;
	private boolean ally = true;
	private boolean piercing = false;
	private Animation anim;
	public Projectile(Animation anim, String name,Rectangle rect,double speed, int damage) {
		super(anim.getImage(0), name);
		this.anim = anim;
		this.setTag("Projectile");
		this.rect = AssetManager.rect3232;
		this.setHarvestable(false);
		this.rect = rect;		
		this.speed = speed;
		this.damage = damage;
		
	}
	
	public void initProjectile(int x, int y, int pointx, int pointy) {
		this.dispawn = 0;
		this.x = x;
		this.y = y;
		double dy = ( pointy- this.y);
		double dx = ( pointx - this.x);
		this.angle = Math.atan2(dy,dx);
		this.VelocityX = speed*Math.cos(angle);
		this.VelocityY = speed*Math.sin(angle);
		this.rotation = (float)(angle*(180/Math.PI));
	}




	@Override
	public void render(Graphics g) {
		//System.out.println(angle*(180/Math.PI));
		this.image.setRotation(this.rotation);
		for(int i = 0; i < this.anim.getFrameCount(); i++) {
			this.anim.getImage(i).setRotation(this.rotation);
		}
		g.drawAnimation(this.anim, this.x, this.y);
	}

	@Override
	public void update() {
		dispawn++;
		this.x += this.VelocityX;
		this.y += this.VelocityY;
		if(!this.piercing) {
			if(!World.getTileFromChunk(this.x/World.tileSize, this.y/World.tileSize).getTag().equals("transparent")) {
				this.destroy = true;
			}
		}
		if(dispawn > 200) {
			this.destroy = true;
		}
	}







	public int getDamage() {
		return damage;
	}







	public void setDamage(int damage) {
		this.damage = damage;
	}
	public boolean isAlly() {
		return ally;
	}
	public void setAlly(boolean ally) {
		this.ally = ally;
	}
	public boolean isPiercing() {
		return piercing;
	}
	public void setPiercing(boolean piercing) {
		this.piercing = piercing;
	}


}
