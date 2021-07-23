package mainGame;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import asset.DungeonManager;
import entityPack.Entity;
import entityPack.LivingEntity;
import inventoryPack.Projectile;
import tilePack.Tile;






public class World {
	private Random r = new Random();
	private static Chunk[][] chunks = new Chunk[100][100];
	public static  ArrayList<Entity> allEntity = new ArrayList<>();
	public static ArrayList<LivingEntity> allLivingEntity = new ArrayList<>();
	private ArrayList<Entity> trashCan = new ArrayList<>(); 
	private ArrayList<LivingEntity> entityCramming = new ArrayList<>();
	static int mapWidth;
	static int mapHeight;
	public static int tileSize;
	private long seed;
	public World(GameContainer container) {
		mapWidth = chunks.length;
		mapHeight = chunks[0].length;
		
		this.seed = (long) (Math.random()*(100000-100)+1)+100;
		Noise2D[] noises = new Noise2D[5];
		Noise2D noise = new Noise2D(this.seed);
		noises[0] = noise;
		long caveSeed = seed-25;
		Noise2D cavenoise = new Noise2D(caveSeed);
		long oreSeed = caveSeed-25;
		Noise2D  orenoise = new Noise2D(oreSeed);
		long AmethystSeed = oreSeed-25;
		Noise2D  amenoise = new Noise2D(AmethystSeed);
		noises[1] = cavenoise;
		noises[2] = orenoise;
		noises[3] = amenoise;
		generateWorld(noises);
		generateCave(noises);
		generateOre(noises);
		generateStructure();
		this.UpdateAfterGen(0,container);
		tileSize = chunks[0][0].getTiles()[0][0].getWidth();
		
	}
	public void spawnEntity(float x, float y,LivingEntity entity) {
		try {
			LivingEntity temp = (LivingEntity) entity.clone();
			temp.setX(x);
			temp.setY(y);
			allLivingEntity.add(temp);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	public static void addEntity(Entity e) {
		allEntity.add(e);
	}
	
	public void generateWorld(Noise2D[] noise) {
		for(int i = 0; i < chunks.length; i++) {
			for(int j = 0; j < chunks[i].length; j++) {
				chunks[i][j] = new Chunk(i,j);
				chunks[i][j].generateChunk(noise);
			}
		}
	}
	public void generateCave(Noise2D[] noise) {
		for(int i = 0; i < chunks.length; i++) {
			for(int j = 0; j < chunks[i].length; j++) {
				chunks[i][j].generateCave(noise);
			}
		}
	}
	public void generateStructure() {
		
		for(int i = 0; i < mapWidth*12; i++) {
			for(int j = 0; j < mapHeight*12; j++) {
				if(i == 1050 && j == 1050) {
					DungeonManager.crystaltemple.setDungeonFromTileIndex(i, j);
				}
				if(i > 100 && i < 1100 && j > 100 && j < 1100) {
					int chunkx = i/12;
					int chunky = j/12;
					double rng = r.nextInt(4000);
					if(rng < 1) {
						if(!(chunkx < 95 && chunkx > 75 && chunky < 95 && chunky > 75)) {
							rng = r.nextInt(2);
							if(rng >= 1)
								DungeonManager.Map1.setDungeonFromTileIndex(i, j);
							else
								DungeonManager.Dungeon1.setDungeonFromTileIndex(i, j);
						}
						
					}
					else if(rng < 13) {
						if(chunky < 60 && chunky > 40 && chunkx <60 && chunkx >40) {
							rng = r.nextInt(2);
							if( rng >= 1) {
								DungeonManager.tree1.generateDungeonIfSurrounded(i, j);
							}
							else {
								DungeonManager.tree2.generateDungeonIfSurrounded(i, j);
							}
						}
					}
				}
			}
		}
	}
	public void generateOre(Noise2D[] noise) {
		for(int i = 0; i < chunks.length; i++) {
			for(int j = 0; j < chunks[i].length; j++) {
				chunks[i][j].generateOre(noise);
			}
		}
	}
	public void renderEntity(Graphics g) {
		for(Entity e : allEntity) {
			e.render(g);
		}
	}

	public void updateEntity() {
		for(Entity e : allEntity) {
			e.update();
			if(e.isHarvestable()) {
				if(e.getRect().intersects(WindowGame.player.getRect())) {
					
					WindowGame.player.getInventory().addItem(e);
					this.trashCan.add(e);
				}
			}
			else {
				if(e.getRect().intersects(WindowGame.player.getRect()) && e.getTag().equals("Projectile")) {
					Projectile temp = (Projectile)e;
					if(!temp.isAlly()) {
						WindowGame.player.dealDamage(temp.getDamage());
						e.setDestroy(true);
					}

				}
				for(LivingEntity lv : allLivingEntity) {
					lv.getRect().setX(lv.getX());
					lv.getRect().setY(lv.getY());
					if(e.getRect().intersects(lv.getRect())) {
						if(e instanceof Projectile) {
							Projectile temp = (Projectile)e;
							if(temp.isAlly()) {
								lv.setLife(lv.getActualLife()-temp.getDamage());
								e.setDestroy(true);
							}
						}
						
					}
				}
			}
			if(e.isDestroy()) {
				this.trashCan.add(e);
			}
		}
		for(Entity e : this.trashCan) {
			allEntity.remove(e);
		}
	}
	public void renderLivingEntity(Graphics g,GameContainer gc) {
		
		for(LivingEntity le : allLivingEntity) {
			le.render(g);
		}
	}
	public void updateLivingEntity(int delta, double distance, GameContainer gc) {
		for(LivingEntity le : allLivingEntity) {
			if(distance < 2) {
				Input input = gc.getInput();
				Rectangle temp = new Rectangle(WindowGame.cursor.x,WindowGame.cursor.y,64,64);
				le.getRect().setX(le.getX());
				le.getRect().setY(le.getY());
				if(temp.intersects(le.getRect())) {
					if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
						le.setLife(le.getActualLife()-25);
					}
				}
			}
			
			le.update(delta);
			
			if(le.isDestroy()) {
				this.entityCramming.add(le);
			}
		}
		for(LivingEntity le : entityCramming) {
			allLivingEntity.remove(le);
		}
		
	}
	
	public void Dummyrender(Graphics g, GameContainer container) {
		for(int i = 0; i < chunks.length; i++) {
			for(int j = 0; j < chunks[i].length; j++) {
				chunks[i][j].render(g,container);
			}
		}

	}
	public void render(Graphics g,GameContainer container,int renderx, int rendery, int posX, int posY) {
		for(int y = -rendery ; y < rendery; y++) {
			for (int x = -renderx; x < renderx; x++) {
				if(posX+x >= 0  && posY+y >= 0 && posX+x < mapWidth && posY+y < mapHeight ) {
					if(chunks[posX+x][posY+y] != null) {
						chunks[posX+x][posY+y].render(g,container);
						
					}
				}
			}
		}
	}
	public void UpdateAfterGen(int delta,GameContainer container) {
		for(int i = 0; i < chunks.length; i++) {
			for(int j = 0; j < chunks[i].length; j++) {
				chunks[i][j].update(delta,container);
			}
		}
	}
	
	public void UpdateWorlMap(int renderx, int rendery, int posX, int posY, int delta,GameContainer container) {
		for(int y = -rendery ; y < rendery; y++) {
			for (int x = -renderx; x < renderx; x++) {
				if(posX+x > 0  && posY+y > 0 && posX+x < mapWidth && posY+y < mapHeight ) {
					if(chunks[posX+x][posY+y] != null) {
						chunks[posX+x][posY+y].update(delta,container);
						if(posX+x > WindowGame.player.getChunkx()+1 || 
						   posX+x < WindowGame.player.getChunkx()-1 ||
						   posY+y > WindowGame.player.getChunky()+1 || 
						   posY+y < WindowGame.player.getChunky()-1) {
							chunks[posX+x][posY+y].spawnEntity();
						}
						
					}
				}
			}
		}
		
	}

	public boolean isCollision(int renderx, int rendery, int posX, int posY, int delta, float futurX, float futurY,Rectangle rect) {
		for(int y = -rendery ; y < rendery; y++) {
			for (int x = -renderx; x < renderx; x++) {
				if(posX+x > 0  && posY+y > 0 && posX+x < mapWidth && posY+y < mapHeight ) {
					if(chunks[posX+x][posY+y] != null) {
						if(chunks[posX+x][posY+y].isCollision(futurX,futurY,rect)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	
	
	public Chunk getChunkFromIndex(int x, int y, Chunk[][] globalMap) {
		return chunks[x][y];
	}
	
	public static Tile getTileFromChunk(int x, int y) {
		if(Math.round(x/12) >= mapWidth || Math.round(y/12) >= mapHeight ) {
			return null;
		}
		else
			return chunks[Math.round(x/12)][Math.round(y/12)].getTileFromIndex(x, y);

	}
	public static Tile getTileFromChunkBack(int x, int y) {
		if(Math.round(x/12) >= mapWidth || Math.round(y/12) >= mapHeight ) {
			return null;
		}
		else
			return chunks[Math.round(x/12)][Math.round(y/12)].getTileFromIndexback(x, y);

	}
	
	public static void setTileFromIndex(int x, int y, Tile tile) {
		chunks[Math.round(x/12)][Math.round(y/12)].SetTileFromIndex(x, y, tile);
	}
	public static void setTileFromIndexBack(int x, int y, Tile tile) {
		chunks[Math.round(x/12)][Math.round(y/12)].SetTileFromIndexback(x, y, tile);
	}


	
	public int getMapHeight() {
		return mapHeight;
	}
	public int getMapWidth() {
		return mapWidth;
	}
	
	public Chunk[][] getChunks() {
		return chunks;
	}
	public void setChunks(Chunk[][] chunks2) {
		chunks = chunks2;
	}

	public static int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize2) {
		tileSize = tileSize2;
	}

	public ArrayList<Entity> getAllEntity() {
		return allEntity;
	}

	public void setAllEntity(ArrayList<Entity> allEntity2) {
		allEntity = allEntity2;
	}

}
