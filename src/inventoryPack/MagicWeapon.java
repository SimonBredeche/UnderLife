package inventoryPack;

import org.newdawn.slick.Image;

public class MagicWeapon extends FiringWeapon{
	protected int manacost = 1;
	public MagicWeapon(Image image, String name, boolean place, int delay, int manacost) {
		super(image, name, place, delay);
		//this.setTag("Magic weapon");
		this.manacost = manacost;
		// TODO Auto-generated constructor stub
	}
	public int getManacost() {
		return manacost;
	}
	public void setManacost(int manacost) {
		this.manacost = manacost;
	}



}
