package inventoryPack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import mainGame.WindowGame;

public class Up extends Item{
	private static final int LIFE = 10;
	private static final int MANA = 10;
	private boolean mana = false;
	public Up(Image image, String name, boolean mana) {
		super(image, name, false);
		this.mana = mana;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void update(GameContainer gc,int delta) {
		Input input = gc.getInput();
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			if(this.mana) {
				WindowGame.player.setMaxmana(WindowGame.player.getMaxmana()+MANA);
				WindowGame.player.getInventory().RemoveItem(this.getName(), 1);
			}
			else {
				WindowGame.player.setMaxlife(WindowGame.player.getMaxlife()+LIFE);
				WindowGame.player.getInventory().RemoveItem(this.getName(), 1);
			}
			
		}
		
	}
	public boolean isMana() {
		return mana;
	}
	public void setMana(boolean mana) {
		this.mana = mana;
	}

}
