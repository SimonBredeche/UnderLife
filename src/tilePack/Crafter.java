package tilePack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;



public class Crafter extends Tile{

	public Crafter(String name, Image img) {
		super(name, img);
		this.setTag("Crafter");

	}

	@Override
	public void update(GameContainer container, int delta) {
		/*this.rect.setX(this.xdraw);
		this.rect.setY(this.ydraw);
		if(this.getRect().intersects(WindowGame.player.getRect())) {
			System.out.println("in crafting table");
		}*/
	}

}