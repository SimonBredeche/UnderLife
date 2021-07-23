package entityPack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;

import mainGame.WindowGame;
import mainGame.World;

public class FlyingSlime extends LivingEntity{

	public FlyingSlime(Animation mobAnim, String name, Rectangle rect, int life, boolean pacific, float speed,int damage) {
		super(mobAnim, name, rect, life, pacific, speed,damage);
		// TODO Auto-generated constructor stub
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



}
