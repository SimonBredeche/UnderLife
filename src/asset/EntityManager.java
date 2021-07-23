package asset;

import java.util.ArrayList;

import entityPack.Asmora;
import entityPack.Bat;
import entityPack.Boss;
import entityPack.FlyingSlime;
import entityPack.Janga;
import entityPack.LivingEntity;
import entityPack.Yaron;

public class EntityManager {
	public static LivingEntity slime, janga,bat;
	public static Boss flairon,asmora;
	public static ArrayList<LivingEntity> junglemobs;
	
	public EntityManager() {
		
	}
	public static void loadEntity() {
		bat = new Bat(AssetManager.batanim,"Bat",AssetManager.rectbat,100,false,0.2f,5);
		janga = new Janga(AssetManager.jangaAnim,"Janga",AssetManager.rectjanga,100,false,0.2f,5);
		janga.setDetectionrange(5);
		slime = new FlyingSlime(AssetManager.slime,"Slime",AssetManager.rectslime,100,false,0.2f,5);
		slime.setDetectionrange(6);
		asmora = new Asmora(AssetManager.asmora,"ASMORA",AssetManager.asmoraRect,2000,false,0.2f,SoundManager.jungleboss,10);
		flairon = new Yaron(AssetManager.flairon,"FLAIRON",AssetManager.flaironrect,5000,false,0.2f,SoundManager.flairontheme,10);
		flairon.setDetectionrange(15);
		loadJungleMobs();
	}
	
	public static void loadJungleMobs() {
		junglemobs = new ArrayList<>();
		junglemobs.add(janga);
		junglemobs.add(bat);
	}

}
