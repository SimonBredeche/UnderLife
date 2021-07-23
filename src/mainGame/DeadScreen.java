package mainGame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import asset.AssetManager;

public class DeadScreen {
	private Image image;
	private int runtime = 0;
	private boolean playerdead = false;
	public DeadScreen() {
		 this.image = AssetManager.loadImage("texture/deadscreen.png");
	}
	public void update(int delta) {
		runtime += delta;
		if(runtime > 5000) {
			WindowGame.player.updateSounds();
			WindowGame.player.setX(WindowGame.SPAWNX);
			WindowGame.player.setY(WindowGame.SPAWNY);
			WindowGame.player.setLife(WindowGame.player.getMaxlife());
			this.runtime = 0;
			this.playerdead = false;
		}
	}
	
	public void render(Graphics g) {
		g.resetTransform();
		g.drawImage(this.image, 0, 0);
		
	}
	public boolean isDead() {
		return playerdead;
	}
	public void setDead(boolean isFinish) {
		this.playerdead = isFinish;
	}

}
