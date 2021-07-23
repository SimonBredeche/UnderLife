package entityPack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Music;
import org.newdawn.slick.geom.Rectangle;

import mainGame.WindowGame;
import mainGame.World;

public class Boss extends LivingEntity{
	protected Music music;
	
	public Boss(Animation mobAnim, String name, Rectangle rect, int life, boolean pacific, float speed,Music music,int damage) {
		super(mobAnim, name, rect, life, pacific, speed,damage);
		this.x = this.x+this.mobAnim.getImage(0).getWidth()/2;
		this.y = this.y+this.mobAnim.getImage(0).getHeight()/2;
		this.music = music;
		
		
	}
	@Override
	public void update(int delta) {
		
		if(this.rect.intersects(WindowGame.player.getRect())) {
			WindowGame.player.dealDamage(this.Damage);
		}
		if(WindowGame.player.getLife() <= 0) {
			this.destroy = true;
		}
		if(!this.music.playing()) {
			this.music.loop();
		}
		double detx = this.getX()/World.tileSize - WindowGame.player.getX()/World.tileSize;
		double dety = this.getY()/World.tileSize - WindowGame.player.getY()/World.tileSize;
		double distance = Math.sqrt(Math.pow(detx, 2) +  Math.pow(dety, 2));
		if(this.actuallife <= 0) {
			this.OnKill();
			WindowGame.player.updateSounds();
		}
		this.aggroAI(delta,distance);
			
		
		
	}

	@Override
	public void updateposition(int delta) {
		float futurY = getFuturY(delta);
		float futurX = getFuturX(delta);
        this.setX(futurX);
        this.setY(futurY);
            
	}




}
