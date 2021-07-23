package armorPack;

import org.newdawn.slick.Image;

public class ArmorSet {
	private Helmet helmet;
	private Chestplate chestplate;
	private Boots boots;
	public ArmorSet(String armorName,Image helmetimg, int helmvalue,Image chestimg, int chestvalue,Image bootsimg, int bootsvalue) {
		this.helmet = new Helmet(helmetimg,armorName+" helmet",helmvalue);
		this.chestplate = new Chestplate(chestimg,armorName+" chestplate",chestvalue);
		this.boots = new Boots(bootsimg,armorName+" boots",bootsvalue);
	}
	public Helmet getHelmet() {
		return helmet;
	}
	public void setHelmet(Helmet helmet) {
		this.helmet = helmet;
	}
	public Chestplate getChestplate() {
		return chestplate;
	}
	public void setChestplate(Chestplate chestplate) {
		this.chestplate = chestplate;
	}
	public Boots getBoots() {
		return boots;
	}
	public void setBoots(Boots boots) {
		this.boots = boots;
	}

}
