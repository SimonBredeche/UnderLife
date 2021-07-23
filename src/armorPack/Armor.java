package armorPack;

import org.newdawn.slick.Image;

import inventoryPack.Item;

public abstract class Armor extends Item{
	
	protected int armorValue;
	
	public Armor(Image image, String name,int armorValue) {
		super(image, name, false);
		this.armorValue = armorValue;

	}
	

	public int getArmorValue() {
		return armorValue;
	}
	public void setArmorValue(int armorValue) {
		this.armorValue = armorValue;
	}

}
