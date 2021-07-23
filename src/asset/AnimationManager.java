package asset;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;



public class AnimationManager {
	public AnimationManager() {
		// TODO Auto-generated constructor stub
	}
	
	
    public static Animation loadAnimation(String path,int  widht,int height,int startX, int endX, int y,int scale) {
    	SpriteSheet spriteSheet = null;
		try {
			spriteSheet = new SpriteSheet(path,widht,height);
		} catch (SlickException e) {
			e.printStackTrace();
		}
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
        	Image img = spriteSheet.getSprite(x, y).getScaledCopy(scale);
            animation.addFrame(img, 100);
        }
        animation.setSpeed((float)0.5);
        return animation;
    }
    public static ArrayList<Image> loadFramesWithoutScale(String path, int startX, int endX, int y, int size) {
    	SpriteSheet spriteSheet = null;
		try {
			spriteSheet = new SpriteSheet(path,size,size);
		} catch (SlickException e) {
			e.printStackTrace();
		}
    	ArrayList<Image> temp = new ArrayList<>();
        for (int x = startX; x < endX; x++) {
        	Image img = spriteSheet.getSprite(x, y);
        	img.setFilter(Image.FILTER_NEAREST);
        	temp.add(img);
        }
        return temp;
    }
    
    public static ArrayList<Image> loadFrames(String path, int startX, int endX, int y) {
    	SpriteSheet spriteSheet = null;
		try {
			spriteSheet = new SpriteSheet(path,32,32);
		} catch (SlickException e) {
			e.printStackTrace();
		}
    	ArrayList<Image> temp = new ArrayList<>();
        for (int x = startX; x < endX; x++) {
        	Image img = spriteSheet.getSprite(x, y);
        	
        	img.setFilter(Image.FILTER_NEAREST);
        	img = img.getScaledCopy(2);
        	temp.add(img);
        }
        return temp;
    }


}
