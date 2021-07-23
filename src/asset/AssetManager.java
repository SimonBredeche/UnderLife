package asset;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;




public class AssetManager {
	//public static Tile dirt, stone, voidt;
	public static ArrayList<Image> stoneimg,StoneReplace,dirtimg,dirtReplace,voidimg,potimg, jungleore, woodoor,
									ironore, blackstone,Crystal,amethyst;
	public static Image caveback, Cursor, player, slot, indexSelector, ironingot,crafter,stonebackground,stonebrick,
						furnace,emeraldpick, wood, vineback,Abyssalite, deepcaveback,enemyBar,slimeitem,junglebow,
						crystalback,crystaldeposite,ironhelmet,ironchestplate,ironboots,goldhelmet,goldchestplate,goldboots,
						anvil,shieldicon,lifebar,playerbar,chest,planks,planksback,manabar,IronStaff,
						healpotion,manapotion,heart,star,tintedrock,amestaff,junglestatue,totem,crystalball,
						archery,alchemy,amepick,blockbuilder,platform,crystalbrick,crystalbrickback,crystalpillar,
						yaronsummon,bluecrystal,spike;
	public static Rectangle rect1, rectEntity,rectslime,rect3232,arrow716,flaironrect,rect32_128,asmoraRect,
							flaironprojRect,rectjanga,rectbat,platformrect;
	public static Animation slime, flairon,arrow,flaironshoot,slash,asmora,shampi,amethystcrystal,ameshoot,batshoot,spawneranim;
	public static Animation[] jangaAnim,batanim;
	public AssetManager() {
		rect1 = new Rectangle(0,0,64,64); //64*64
		rectEntity = new Rectangle(0,0,16,16); //16*16
		rectslime = new Rectangle(0,0,64,64);
		rectjanga = new Rectangle(0,0,90,128);
		rect3232 = new Rectangle(0,0,32,32);
		arrow716 = new Rectangle(0,0,64,28);
		rectbat = new Rectangle(0,0,160,128);
		rect32_128 = new Rectangle(0,0,96*2,136*2);
		flaironprojRect = new Rectangle(0,0,63,63);
		asmoraRect = new Rectangle(0,0,335,417);
		flaironrect = new Rectangle(0,0,300,257);
		platformrect = new Rectangle(0,0,64,9);
		jangaAnim = new Animation[3];
		batanim = new Animation[4];
		jangaAnim[0] = AnimationManager.loadAnimation("texture/Janga.png", 90, 128, 0, 2, 0,1);
		jangaAnim[1] = AnimationManager.loadAnimation("texture/Janga.png", 90, 128, 0, 2, 1,1);
		jangaAnim[2] = AnimationManager.loadAnimation("texture/Janga.png", 90, 128, 0, 2, 2,1);
		batanim[0] = AnimationManager.loadAnimation("texture/bat.png", 160, 128, 0, 3, 0,1);
		batanim[1] = AnimationManager.loadAnimation("texture/bat.png", 160, 128, 0, 3, 1,1);
		batanim[2] = AnimationManager.loadAnimation("texture/bat.png", 160, 128, 0, 3, 2,1);
		batanim[3] = AnimationManager.loadAnimation("texture/bat.png", 160, 128, 0, 3, 3,1);
		batshoot = AnimationManager.loadAnimation("texture/batprojectile.png", 32, 22, 0, 1, 0, 1);
		ameshoot = AnimationManager.loadAnimation("texture/ameshoot.png", 28, 12, 0, 1, 0, 1);
		spawneranim = AnimationManager.loadAnimation("texture/spawner.png", 64, 64, 0, 4, 0, 1);
		heart = loadImage("texture/hearthCrystal.png");
		star = loadImage("texture/stars.png");
		totem = loadImage("texture/totem.png");
		platform = loadImage("texture/platform.png");
		bluecrystal = loadImage("texture/bluecrystal.png");
		blockbuilder = loadImage("texture/meuleuse.png");
		amepick = loadImage("texture/amepick.png");
		spike = loadImage("texture/spike.png");
		crystalball = loadImage("texture/crystalball.png");
		archery = loadImage("texture/archerystand.png");
		alchemy = loadImage("texture/alchemytable.png");
		crystalbrick = loadImage("texture/CrystalBrick.png");
		crystalpillar = loadImage("texture/crystalpillar.png");
		crystalbrickback = loadImage("texture/CrystalBrickBack.png");
		junglestatue = loadImage("texture/jungleStatue.png");
		tintedrock = loadImage("texture/tintedrock.png");
		healpotion = loadImage("texture/healpotion.png");
		manapotion = loadImage("texture/manapotion.png");
		planks = loadImage("texture/planks.png");
		amestaff = loadImage("texture/amestaff.png");
		planksback = loadImage("texture/planksback.png");
		IronStaff = loadImage("texture/IronStaff.png");
		yaronsummon = loadImage("texture/yaronsummon.png");
		amethystcrystal = AnimationManager.loadAnimation("texture/amethystcrystal.png", 64, 64, 0, 1, 0, 1);
		arrow = AnimationManager.loadAnimation("texture/arrow.png", 16, 7, 0, 1, 0, 2);
		shampi = AnimationManager.loadAnimation("texture/shampishoot.png", 32, 27, 0, 1, 0, 1);
		slime = AnimationManager.loadAnimation("texture/slime.png", 32, 32, 0, 2, 0,2);
		flaironshoot = AnimationManager.loadAnimation("texture/flaironshoot.png", 52, 54, 0, 4, 0,1);
		slash = AnimationManager.loadAnimation("texture/slash.png", 96, 136, 0, 1, 0, 2);
		asmora = AnimationManager.loadAnimation("texture/Asmora.png", 592, 500, 0, 1, 0, 1);
		flairon = AnimationManager.loadAnimation("texture/flairon.png", 300, 257, 0, 4, 0, 1);
		stoneimg = AnimationManager.loadFramesWithoutScale("texture/StoneSheet.png", 0, 6,0,64);
		amethyst = AnimationManager.loadFramesWithoutScale("texture/amethystsheet.png", 0, 6,0,64);
		dirtimg = AnimationManager.loadFramesWithoutScale("texture/DirtSheet.png", 0, 6,0,64);
		Crystal = AnimationManager.loadFramesWithoutScale("texture/crystalsheet.png", 0, 6,0,64);
		potimg = AnimationManager.loadFramesWithoutScale("texture/pot.png", 0, 1,0,64);
		voidimg = AnimationManager.loadFrames("texture/Voidt.png", 0, 1,0);
		jungleore  = AnimationManager.loadFramesWithoutScale("texture/jungleore.png", 0, 6, 0,64);
		ironore = AnimationManager.loadFramesWithoutScale("texture/ironore.png", 0, 6, 0,64);
		woodoor = AnimationManager.loadFrames("texture/doorsheet.png", 0, 2, 0);
		blackstone = AnimationManager.loadFramesWithoutScale("texture/blackstone.png", 0, 6, 0,64);
		caveback = loadImage("texture/caveback.png");
		anvil = loadImage("texture/anvil.png");
		shieldicon = loadImage("texture/shieldicon.png");
		lifebar = loadImage("texture/lifebar.png");
		playerbar = loadImage("texture/playerbar.png");
		chest = loadImage("texture/chest.png");
		slimeitem = loadImageWithScale("texture/slimeitem.png",4);
		ironhelmet = loadImageWithScale("texture/ironhelmet.png",4);
		ironchestplate = loadImageWithScale("texture/ironchestplate.png",4);
		ironboots = loadImageWithScale("texture/ironboots.png",4);
		goldhelmet = loadImageWithScale("texture/goldhelmet.png",4);
		goldchestplate = loadImageWithScale("texture/goldchestplate.png",4);
		goldboots = loadImageWithScale("texture/goldboots.png",4);
		
		manabar = loadImage("texture/manabar.png");
		enemyBar = loadImage("texture/enemyBar.png");
		deepcaveback = loadImage("texture/deepcaveback.png");
		crystalback = loadImage("texture/crystalback.png");
		crystaldeposite = loadImage("texture/crystaldeposite.png");
		Cursor = loadImageWithScale("texture/Cursor.png",2);
		//player = loadImage("texture/player.png");
		ironingot = loadImageWithScale("texture/jungleingot.png",1);
		Abyssalite = loadImageWithScale("texture/Abyssalite.png",2);
		emeraldpick = loadImageWithScale("texture/emeraldpick.png",1);
		junglebow = loadImageWithScale("texture/junglebow.png",1);
		indexSelector = loadImage("texture/indexSelector.png");
		slot = loadImage("texture/slot.png");
		crafter = loadImageWithScale("texture/crafter.png",1);
		stonebackground = loadImageWithScale("texture/stoneback.png",1);
		stonebrick = loadImageWithScale("texture/stonebrick.png",1);
		furnace = loadImageWithScale("texture/furnace.png",2);
		wood = loadImageWithScale("texture/wood.png",1);
		vineback = loadImage("texture/vineback.png");
		
		
		
		
		//dirtReplace = loadImage("texture/StoneReplace.png");
	}
	
	public static Image loadImageWithScale(String path, float Scale) {
	    BufferedImage bufferedImage = null;
	    Image image = null;
	    Texture texture = null;
		try {	
			bufferedImage = ImageIO.read(new File(path));
			texture = BufferedImageUtil.getTexture("", bufferedImage);
			image = new Image(texture.getImageWidth(), texture.getImageHeight());
			//image.setFilter(Image.FILTER_NEAREST);
			//image = image.getScaledCopy(2);
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
			
		}
		
	    image.setTexture(texture); 
	    image.setFilter(Image.FILTER_NEAREST);
	    image = image.getScaledCopy(Scale);
	    return image;
	}
	public static Image loadImage(String path) 
	{
	    BufferedImage bufferedImage = null;
	    Image image = null;
	    Texture texture = null;
		try {	
			bufferedImage = ImageIO.read(new File(path));
			texture = BufferedImageUtil.getTexture("", bufferedImage);
			image = new Image(texture.getImageWidth(), texture.getImageHeight());
			//image.setFilter(Image.FILTER_NEAREST);
			//image = image.getScaledCopy(2);
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
			
		}
		
	    image.setTexture(texture); 
	    image.setFilter(Image.FILTER_NEAREST);
	    return image;
	}

}
