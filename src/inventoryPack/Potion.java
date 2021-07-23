package inventoryPack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import mainGame.WindowGame;

public class Potion extends Item{
	private int value = 0;
	private boolean mana = false;
	public Potion(Image image, String name,int value,boolean mana) {
		super(image, name, false);
		this.value = value;
		this.setMana(mana);
		this.setTag("potion");
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void update(GameContainer gc,int delta) {
		Input input = gc.getInput();
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			if(this.mana) {
				WindowGame.player.restoreMana(this.value);
				WindowGame.player.getInventory().RemoveItem(this.getName(), 1);
			}
			else {
				if(WindowGame.player.getHealcooldown() <= 0)
				{
					WindowGame.player.healPlayer(value);
					WindowGame.player.getInventory().RemoveItem(this.getName(), 1);
				}
			}
			
		}
		
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isMana() {
		return mana;
	}
	public void setMana(boolean mana) {
		this.mana = mana;
	}



}
