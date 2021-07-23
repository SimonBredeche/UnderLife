package mainGame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import asset.AnimationManager;
import asset.AssetManager;
import asset.SoundManager;
import inventoryPack.Inventory;

public class Player {

	private float x = 300, y = 300;
	private float prevX = 300, prevY = 300;
	private float speed = 0.25f;
	private int chunkx = 0, chunky = 0;
	private boolean ignorecollision = false;
	private Image image;
	private Rectangle rect;
	private boolean onGround = false;
	private int jumpheight = 0;
	private int direction;
	private boolean collision;
	private Animation animation[] = new Animation[3];
	private boolean moving = false;
	private int yvelocity = 10;
	private Inventory inventory;
	private int life = 500;
	private int maxlife = 500;
	private int mana = 10;
	private int maxmana = 10;
	private String facing = "down";
	private String currentStation = "none";
	private int runningtime = 0, runningtime2 = 0, runningtime3 = 0, healcooldown = 0;
	private String currentbiome = "jungle";
	private int widht, height;

	private int Armor = 1;
	private boolean immune = false;
	boolean north, south , west, east;
	public boolean getFly() {
		return this.ignorecollision;
	}
	public void setFly(boolean fly) {
		this.ignorecollision = fly;
		if(this.ignorecollision) {
			this.speed = 2f;
		}
		else {
			this.speed = 0.25f;
		}
	}
	public void updateBiome() {
		this.chunkx = Math.round(this.x/World.tileSize/12);
		this.chunky = Math.round(this.y/World.tileSize/12);
		String newbiome = "";
		if(this.chunky < 60 && chunky > 40 && chunkx <60 && chunkx >40) {
			newbiome = "jungle";
		}
		else if(this.chunkx < 95 && this.chunkx > 75 && this.chunky < 95 && this.chunky > 75) {
			newbiome = "crystal";
		}
		else {
			newbiome = "deepcave";
		}
		if(!newbiome.equals(this.currentbiome)) {
			this.currentbiome = newbiome;
			switch(currentbiome) {
			case "jungle": 
				SoundManager.maintheme.loop(); 
				WindowGame.background = AssetManager.caveback;
				break;
			case "deepcave": 
				SoundManager.deepcavetheme.loop();
				WindowGame.background = AssetManager.deepcaveback;
				break;
			case "crystal": 
				SoundManager.CrystalCave.loop();
				WindowGame.background = AssetManager.crystalback;
				break;
			}
		}

		
	}
	public void updateSounds() {
		if(this.chunky < 60 && chunky > 40 && chunkx <60 && chunkx >40) {
			SoundManager.maintheme.loop(); 
		}
		else {
			SoundManager.deepcavetheme.loop();
		}
	}
	public void updatePosition(int delta) {    

		if(WindowGame.player.isIgnorecollision()) {
			float futurY = getFuturYFly(delta);
			float futurX = getFuturX(delta);
            this.setX(futurX);
            this.setY(futurY);
		}
		else {
			float futurY = this.y;
			float futurX = getFuturX(delta);
			
	        boolean collision = WindowGame.world.isCollision(1, 1, Math.round(futurX/World.getTileSize())/12, Math.round((futurY/World.getTileSize())/12),delta,futurX,futurY,this.getRect());
	        if (collision) {
	            
	        } else {
	            this.setX(futurX);
	        }
	        futurY = getFuturY(delta);
	        futurX  = this.x;
	        boolean collision2 = WindowGame.world.isCollision(1, 1, Math.round(futurX/World.getTileSize())/12, Math.round((futurY/World.getTileSize())/12),delta,futurX,futurY,this.getRect());
	        if (collision2) {
	        	if(jumpheight == 0)
	        		this.setOnGround(true);
	        } else {
	        	this.setOnGround(false);
	            this.setY(futurY);
	        }
		}
        
	}
    private float getFuturX(int delta){
        float futurX = WindowGame.player.getX();
        switch (WindowGame.player.getDirection()) {
        case 1: futurX = (WindowGame.player.getX()) - WindowGame.player.getSpeed() * delta; break;
        case 3: futurX = (WindowGame.player.getX()) + WindowGame.player.getSpeed() * delta; break;
        }
        return futurX;
    }
    private float getFuturYFly(int delta) {
        float futurY = WindowGame.player.getY();
        switch (WindowGame.player.getDirection()) {
        case 0: futurY = (WindowGame.player.getY()) - WindowGame.player.getSpeed() * delta; break;
        case 2: futurY = (WindowGame.player.getY()) + WindowGame.player.getSpeed() * delta; break;
        }
        return futurY;
    }

    private float getFuturY(int delta) {
        float futurY = WindowGame.player.getY();
        if(jumpheight == 0) {
        	futurY = (WindowGame.player.getY()) + 0.4f * delta;
        }
        else {
        	futurY = (WindowGame.player.getY()) - 0.4f * delta;
        	
        	jumpheight--;
        }
        return futurY;
    }

	public Player(GameContainer container) {
		this.animation[0] = AnimationManager.loadAnimation("texture/playerspritesheet.png", 59, 49, 0, 3, 0,1);
		this.animation[1] = AnimationManager.loadAnimation("texture/playerspritesheet.png", 59, 49, 0, 3, 1,1);
		this.animation[2] = AnimationManager.loadAnimation("texture/playerspritesheet.png", 59, 49, 0, 3, 2,1);
		this.image = this.animation[0].getImage(0);
		this.inventory = new Inventory(container,false,9,9);
		this.setRect(new Rectangle(this.x,this.y,image.getWidth(),image.getHeight()));
		this.widht = image.getWidth();
		this.height = image.getHeight();
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getSpeed() {
		return this.speed;
	}

	public void render(Graphics g) {
		
		if(this.west)
			g.drawAnimation(this.animation[1], this.x, this.y);
		else if(this.east) {
			g.drawAnimation(this.animation[2], this.x, this.y);
		}
		else
			g.drawAnimation(this.animation[0], this.x, this.y);
		
		if(WindowGame.cursor.getItem()!= null) {
			if(this.east) {
				int widht2 =WindowGame.cursor.getItem().getImage().getWidth()/2;
				int height2 = WindowGame.cursor.getItem().getImage().getHeight()/2;
				g.drawImage(WindowGame.cursor.getItem().getImage().getScaledCopy(0.5f), x+(this.widht/2)-(widht2/2), y+(this.height/2)-(height2/2));
			}
			else if(this.west) {
				int widht2 =WindowGame.cursor.getItem().getImage().getWidth()/2;
				int height2 = WindowGame.cursor.getItem().getImage().getHeight()/2;
				Image temp = WindowGame.cursor.getItem().getImage().getScaledCopy(0.5f);
				g.drawImage(temp.getFlippedCopy(true, false), x+(this.widht/2)-(widht2/2), y+(this.height/2)-(height2/2));
			}
		}

		//g.draw(this.rect);
	}
	public void updatePlayer(int delta) {
		this.setRect(new Rectangle(this.x,this.y,image.getWidth(),image.getHeight()));
		if(World.getTileFromChunk(Math.round(this.getX()/World.tileSize), Math.round(this.getY()/World.tileSize)).getTag().equals("Crafter")) {
			this.setCurrentStation(World.getTileFromChunk(Math.round(this.getX()/World.tileSize), Math.round(this.getY()/World.tileSize)).getName());
		}else {
			this.setCurrentStation("none");
		}
		this.runningtime += delta;
		
		if(this.runningtime > 500) {
			this.immune = false;
			this.runningtime = 0;
		}
		
		if(this.life < this.maxlife) { //HEALTH REGEN
			this.runningtime2 += delta;
			if(this.runningtime2 > 2000) {
				this.life++;
				this.runningtime2 = 0;
			}
		}
		if(this.mana < this.maxmana) { //MANA REGEN
			this.runningtime3 += delta;
			if(this.isMoving()) {
				if(this.runningtime3 > 1000) {
					this.mana++;
					this.runningtime3 = 0;
				}
			}
			else {
				if(this.runningtime3 > 500) {
					this.mana++;
					this.runningtime3 = 0;
				}
			}
		}
		if(this.healcooldown > 0) {
			this.healcooldown -= delta;
			if(this.healcooldown < 0) {
				this.healcooldown = 0;
			}
		}
		
		updateBiome();
	}

	public float getPrevX() {
		return prevX;
	}
	public void healPlayer(int healvalue) {
		if(this.life < this.maxlife-healvalue) {
			this.life += healvalue;
		}
		else {
			this.life = this.maxlife;
		}
		this.setHealcooldown(50000);
	}
	public void restoreMana(int manaValue) {
		if(this.mana < this.maxmana-manaValue) {
			this.mana += manaValue;
		}
		else {
			this.mana = this.maxmana;
		}
	
	}

	public void setPrevX(float prevX) {
		this.prevX = prevX;
	}

	public float getPrevY() {
		return prevY;
	}

	public void setPrevY(float prevY) {
		this.prevY = prevY;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		//this.setRect(new Rectangle(this.x,this.y,image.getWidth(),image.getHeight()));
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		//this.setRect(new Rectangle(this.x,this.y,image.getWidth(),image.getHeight()));
	}

	public Rectangle getRect() {
		this.rect.setX(this.x);
		this.rect.setY(this.y);
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public boolean isCollision() {
		return collision;
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	public int getYvelocity() {
		return yvelocity;
	}
	public void setYvelocity(int yvelocity) {
		this.yvelocity = yvelocity;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public String getCurrentStation() {
		return currentStation;
	}
	public void setCurrentStation(String currentStation) {
		this.currentStation = currentStation;
	}
	public boolean isIgnorecollision() {
		return ignorecollision;
	}
	public void setIgnorecollision(boolean ignorecollision) {
		this.ignorecollision = ignorecollision;
	}
	public int getChunkx() {
		return chunkx;
	}
	public void setChunkx(int chunkx) {
		this.chunkx = chunkx;
	}
	public int getChunky() {
		return chunky;
	}
	public void setChunky(int chunky) {
		this.chunky = chunky;
	}
	public int getLife() {
		return life;
	}
	public void dealDamage(int damage) {
		if(this.Armor > 30) {
			damage = Math.round((float)damage/((this.Armor/30)+1));
		}
		if(!this.immune) {
			this.setLife(this.getLife()-damage);
			this.immune = true;
		}
	}
	public void setLife(int life) {
			this.life = life;
			if(this.life <= 0) {
				this.life = 0;
				WindowGame.deadscreen.setDead(true);
			}

	}
	public int getMaxlife() {
		return maxlife;
	}
	public void setMaxlife(int maxlife) {
		this.maxlife = maxlife;
	}
	public int getArmor() {
		return Armor;
	}
	public void setArmor(int armor) {	
		Armor = armor;

	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getMaxmana() {
		return maxmana;
	}
	public void setMaxmana(int maxmana) {
		this.maxmana = maxmana;
	}
	public int getHealcooldown() {
		return healcooldown;
	}
	public void setHealcooldown(int healcooldown) {
		this.healcooldown = healcooldown;
	}
	public int getJumpheight() {
		return jumpheight;
	}
	public void setJumpheight(int jumpheight) {
		this.jumpheight = jumpheight;
	}
	public boolean isOnGround() {
		return onGround;
	}
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	public String getFacing() {
		return facing;
	}
	public void setFacing(String facing) {
		this.facing = facing;
	}


}
