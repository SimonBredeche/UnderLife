package asset;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundManager {
	public static Music maintheme, deepcavetheme, flairontheme,CrystalCave,jungleboss;
	public static Sound pioche,blocbreak;
	public SoundManager() {
		// TODO Auto-generated constructor stub
	}
	public static void loadMusic() {
		try {
			CrystalCave = new Music("sound/CrystalCave.ogg");
			maintheme = new Music("sound/Nervous_Bot.ogg");
			pioche = new Sound("sound/pioche.ogg");
			blocbreak = new Sound("sound/blocbreak.ogg");
			deepcavetheme = new Music("sound/Necessary_Step_Back.ogg");
			flairontheme = new Music("sound/yaron.ogg");
			jungleboss = new Music("sound/Jungle-Boss.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
