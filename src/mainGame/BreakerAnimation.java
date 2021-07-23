package mainGame;

import org.newdawn.slick.Animation;

import org.newdawn.slick.Graphics;


import asset.AnimationManager;

public class BreakerAnimation {

	private boolean ready = false;
	private boolean update = false;
	private int tick = 0;
	private Animation animation;
	private int maxTick = 100;

	public BreakerAnimation() {
		this.animation = AnimationManager.loadAnimation("texture/breakingAnimation.png", 64, 64, 0, 10, 0,1);
	}
	public void resetbar() {
		this.tick = 0;
		this.ready = false;
	}
	
	public void Draw(Graphics g,int x, int y) {
		if(this.update) {
			int imageindex = (int)Math.floor(this.tick/(maxTick/10));
			if(!(imageindex > 9)) {
				g.drawImage(this.animation.getImage(imageindex),x,y);
			}
		}
	}
	
	public void update() {
		if(this.update) {
			tick++;
			if(this.tick > maxTick) {
				this.tick = 0;
				this.ready = true;
			}
		}
	}
	
	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
		
	}
	public int getMaxTick() {
		return maxTick;
	}
	public void setMaxTick(int maxTick) {
		this.maxTick = maxTick;
	}
	public Animation getAnimation() {
		return animation;
	}
	public int getTick() {
		return this.tick;
	}
	public void setTick(int tick) {
		this.tick = tick;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

}
