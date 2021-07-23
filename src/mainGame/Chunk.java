package mainGame;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import asset.AssetManager;
import asset.EntityManager;
import asset.TileManager;
import entityPack.LivingEntity;
import tilePack.BasicTile;
import tilePack.Tile;

public class Chunk {
	private Random r = new Random();
	private int x,y;
	private int entitynumber;
	private Tile[][] tiles = new Tile[12][12];
	private Tile[][] tiles2 = new Tile[12][12];

	public Tile[][] getTilesFront() {
		return this.tiles;
	}
	public Tile[][] getTilesBack() {
		return this.tiles2;
	}
	public Chunk(int x,int y){
		this.x = x;
		this.y = y;
		
	}
	
	
	public void render(Graphics g,GameContainer container) {
		
		for(int i = 0; i < tiles.length;i++) {
			for(int j = 0 ; j < tiles[i].length;j++){
				//g.drawImage(tiles[i][j].getImg(), ((this.x*12)*tiles[i][j].getWidth()) + i*tiles[i][j].getWidth(), ((this.y*12)*tiles[i][j].getWidth()) + j*tiles[i][j].getHeight());
				if(tiles2[i][j] != null) {
					tiles2[i][j].render(g,container);
				}
				tiles[i][j].render(g,container);
				if(tiles[i][j].isRenderRect()) {
					g.draw(tiles[i][j].getRect());
				}

			}
		}

	}
	public boolean check3x3(int tilex, int tiley) {
		for(int i = -1; i < 2;i++) {
			for(int j = -1; j < 2;j++) {
				if(!World.getTileFromChunk(tilex+i,tiley+j).getName().equals("Voidt")) {
					return false;
				}
			}
		}
		return true;
	}
	public void spawnEntity() {
		if(World.allLivingEntity.size() < 10) {
			for(int i = 0; i < tiles.length;i++) {
				for(int j = 0 ; j < tiles[i].length;j++){
					int tilex = i+(this.x*12);
					int tiley = j+(this.y*12);
					int rng = r.nextInt(1000000); 
					if(rng < 2) {
					    LivingEntity tospawn = EntityManager.junglemobs.get(r.nextInt(EntityManager.junglemobs.size()));
					    if(tospawn.spawnConditions(tilex, tiley)) {
								WindowGame.world.spawnEntity(((x*12)+i)*World.tileSize, ((y*12)+j)*World.tileSize, tospawn);
					    }
					}
				}
			}
		}
	}
	public void update(int delta,GameContainer container) {
		for(int i = 0; i < tiles.length;i++) {
			for(int j = 0 ; j < tiles[i].length;j++){
				tiles[i][j].update(container,delta);
				
				
			}
		}
	}
	public boolean isCollision(float futurX, float futurY,Rectangle rect) {
		for(int i = 0; i < tiles.length;i++) {
			for(int j = 0 ; j < tiles[i].length;j++){
				if(tiles[i][j].getTag().equals("solid")) {
					Rectangle temp = new Rectangle(futurX,futurY, rect.getWidth(), rect.getHeight());
					if(temp.intersects(tiles[i][j].getRect())) {
						//tiles[i][j].setRenderRect(true);
						return true;
					}
					

				}
				else if(tiles[i][j].getTag().equals("platform")) {
					Rectangle temp = new Rectangle(futurX,futurY, rect.getWidth(), rect.getHeight());
					if(temp.intersects(tiles[i][j].getRect())) {
						if(WindowGame.player.getFacing().equals("none") ||
						   WindowGame.player.getFacing().equals("vertical")) {
							return true;
						}
						

					}
				}

			}
		}
		return false;
	}

	
	public void generateChunk(Noise2D[] noise) {
		for(int i = 0; i < tiles.length;i++) {
			for(int j = 0 ; j < tiles[i].length;j++){
				double noisevalue = noise[0].eval((i+(x*12)) * 0.1 , (j+(y*12)) * 0.1 );
				//double cavevalue = noise[1].eval((i+(x*12)) * 0.1 , (j+(y*12)) * 0.1 );
				generateBasicLayer(noisevalue,i,j);
				//generateCave(cavevalue,i,j);
				tiles[i][j].setX(this.x*12+i);
				tiles[i][j].setY(this.y*12+j);
				int tempxdraw = ((this.x*12)*tiles[i][j].getWidth()) + i*tiles[i][j].getWidth();
				int tempydraw = ((this.y*12)*tiles[i][j].getWidth()) + j*tiles[i][j].getWidth();
				tiles[i][j].setXdraw(tempxdraw);
				tiles[i][j].setYdraw(tempydraw);
				
				//tiles[i][j] = AssetManager.dirt;
			}
		}
	}
	public void generateCave(Noise2D[] noise) {

		if(this.x < 95 && this.y < 95 && this.x > 5 && this.y > 5) {
			for(int i = 0; i < tiles.length;i++) {
				for(int j = 0 ; j < tiles[i].length;j++){
					double cavevalue = noise[1].eval((i+(x*12)) * 0.05 , (j+(y*12)) * 0.05 );
					generateCave(cavevalue,i,j);
					tiles[i][j].setX(this.x*12+i);
					tiles[i][j].setY(this.y*12+j);
					int tempxdraw = ((this.x*12)*tiles[i][j].getWidth()) + i*tiles[i][j].getWidth();
					int tempydraw = ((this.y*12)*tiles[i][j].getWidth()) + j*tiles[i][j].getWidth();
					tiles[i][j].setXdraw(tempxdraw);
					tiles[i][j].setYdraw(tempydraw);
				}
			}
		}
		
	}
	
	public void generateOre(Noise2D[] noise) {
		if(this.x < 95 && this.y < 95 && this.x > 5 && this.y > 5) {
			for(int i = 0; i < tiles.length;i++) {
				for(int j = 0 ; j < tiles[i].length;j++){
					double orevalue = noise[2].eval((i+(x*12)) * 0.1 , (j+(y*12)) * 0.1 );
					double amethystvalue = noise[3].eval((i+(x*12)) * 0.1 , (j+(y*12)) * 0.1 );
					generateOre(orevalue,i,j);
					generateAmethyst(amethystvalue,i,j);
					tiles[i][j].setX(this.x*12+i);
					tiles[i][j].setY(this.y*12+j);
					int tempxdraw = ((this.x*12)*tiles[i][j].getWidth()) + i*tiles[i][j].getWidth();
					int tempydraw = ((this.y*12)*tiles[i][j].getWidth()) + j*tiles[i][j].getWidth();
					tiles[i][j].setXdraw(tempxdraw);
					tiles[i][j].setYdraw(tempydraw);
				}
			}
		}
	}
	
	
	public void generateBasicLayer(double noisevalue,int x, int y) {

		if(this.x < 95 && this.y < 95 && this.x > 5 && this.y > 5) {
			if(this.y < 60 && this.y > 40 && this.x <60 && this.x >40) {
				if(noisevalue <= 0.2) {
					try {
						tiles[x][y] = (Tile)TileManager.stone.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					
					
				}
				else {
		
					try {
						tiles[x][y] = (Tile)TileManager.dirt.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
				int rng = r.nextInt(500);
				if(rng < 2) {
					try {
						tiles[x][y] = (Tile)TileManager.tintedrock.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
						
			}
			else if (this.x < 95 && this.x > 75 && this.y < 95 && this.y > 75) {
				try {
					tiles[x][y] = (Tile)TileManager.crystal.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
			else {
				try {
					tiles[x][y] = (Tile)TileManager.blackstone.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			try {
				tiles[x][y] = (Tile)TileManager.Abyssalite.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		
	}
	public void generateOre(double noisevalue,int x, int y) {
		if (noisevalue >= 0.7 ){
			if(tiles[x][y] != null) {
				if(!tiles[x][y].getName().equals("Voidt")) {
					try {
						tiles[x][y]= (Tile)TileManager.ironore.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		
	}
	public void generateAmethyst(double noisevalue,int x, int y) {
		if (noisevalue >= 0.8 ){
			if(tiles[x][y] != null) {
				if(!tiles[x][y].getName().equals("Voidt")) {
					try {
						tiles[x][y]= (Tile)TileManager.amethyst.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		
	}
	public void generateCave(double noisevalue,int x, int y) {
		double rng = r.nextInt(100);
		if (noisevalue >= 0.4 ){
			BasicTile temp =  new BasicTile("Voidt",AssetManager.voidimg);
			temp.setTag("transparent");
			tiles[x][y] = temp;
			if(rng < 20) {
				if (this.x < 95 && this.x > 75 && this.y < 95 && this.y > 75) {
					try {
						tiles[x][y] = (Tile)TileManager.crystaldeposite.clone();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					try {
						tiles[x][y] = (Tile)TileManager.pot.clone();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}


				
			}
			
		}
		
	}
	
	
	public Tile getTileFromIndex(int x, int y) {
		int tx = x-this.x*12;
		int ty = y-this.y*12;
		return this.tiles[tx][ty];

	}
	public Tile getTileFromIndexback(int x, int y) {
		int tx = x-this.x*12;
		int ty = y-this.y*12;
		return this.tiles2[tx][ty];

	}
	
	public void SetTileFromIndex(int x, int y, Tile tile) {
		
		int i = x-this.x*12;
		int j = y-this.y*12;
		tiles[i][j].setX(this.x*12+i);
		tiles[i][j].setY(this.y*12+j);
		int tempxdraw = ((this.x*12)*tiles[i][j].getWidth()) + i*tiles[i][j].getWidth();
		int tempydraw = ((this.y*12)*tiles[i][j].getWidth()) + j*tiles[i][j].getWidth();
		tile.setXdraw(tempxdraw);
		tile.setYdraw(tempydraw);
		
		this.tiles[i][j] = tile;
	}
	public void SetTileFromIndexback(int x, int y, Tile tile) {
		
		int i = x-this.x*12;
		int j = y-this.y*12;
		tiles[i][j].setX(this.x*12+i);
		tiles[i][j].setY(this.y*12+j);
		int tempxdraw = ((this.x*12)*tiles[i][j].getWidth()) + i*tiles[i][j].getWidth();
		int tempydraw = ((this.y*12)*tiles[i][j].getWidth()) + j*tiles[i][j].getWidth();
		tile.setXdraw(tempxdraw);
		tile.setYdraw(tempydraw);
		
		this.tiles2[i][j] = tile;
	}
	
	
	public Tile[][] getTiles() {
		return tiles;
	}
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getEntitynumber() {
		return entitynumber;
	}
	public void setEntitynumber(int entitynumber) {
		this.entitynumber = entitynumber;
	}




}
