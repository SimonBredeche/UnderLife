package weaponPack;

import org.newdawn.slick.Image;

import inventoryPack.Item;

public class Pickaxe extends Item{
	private int Efficiency = 1;
	public Pickaxe(Image image, String name, boolean place, int Efficiency) {
		super(image, name, place);
		this.setTag("Pickaxe");
		this.Efficiency = Efficiency;
	}
	public int getEfficiency() {
		return Efficiency;
	}
	public void setEfficiency(int efficiency) {
		Efficiency = efficiency;
	}


}
