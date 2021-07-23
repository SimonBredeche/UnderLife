package tilePack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Spawner extends Tile{
	private Animation anim;
	public Spawner(String name, Animation anim) {
		super(name, anim.getImage(0));
		this.anim = anim;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void render(Graphics g, GameContainer container) {
		g.drawAnimation(this.anim, this.xdraw,this.ydraw);
	}


}
