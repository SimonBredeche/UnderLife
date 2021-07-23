package asset;

import java.util.ArrayList;

import inventoryPack.Projectile;

public class ProjectileManager {
	public static Projectile arrow, flaironshoot,slash,shampi,ameshoot,batshoot;
	public static ArrayList<Projectile> allProject = new ArrayList<>();
	
	public ProjectileManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static Projectile searchProjectileByName(String name) {
		for(Projectile p : allProject) {
			if(p.getName().equals(name))
				return p;
		}
		return null;
	}
	
	public static void loadProjectile() {
		batshoot = new Projectile(AssetManager.batshoot," batshoot", AssetManager.rect3232,8,5);
		ameshoot = new Projectile(AssetManager.ameshoot,"ameshoot", AssetManager.arrow716,30,5);
		ameshoot.setPiercing(true);
		shampi = new Projectile(AssetManager.shampi,"Shampi", AssetManager.arrow716,8,5);
		slash = new Projectile(AssetManager.slash,"Slash", AssetManager.rect32_128,8,25);
		slash.setPiercing(true);
		arrow = new Projectile(AssetManager.arrow,"arrow",AssetManager.arrow716,15,10);allProject.add(arrow);
		flaironshoot = new Projectile(AssetManager.flaironshoot,"flairon shoot",AssetManager.flaironprojRect,8,16);
		flaironshoot.setPiercing(true);
	}

}
