package structurePack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import asset.TileManager;
import mainGame.World;
import tilePack.Tile;

public class Dungeon {
	private Tile[][] allTilesFront;
	private Tile[][] allTilesBack;
	private int width;
	private int height;
	public Dungeon(String name, int width, int height) {
		// TODO Auto-generated constructor stub
		this.allTilesFront = new Tile[width][height];
		this.allTilesBack = new Tile[width][height];
		
		this.loadLayerFront("dungeon/" + name + "front.tme");
		this.loadLayerBack("dungeon/" + name + "back.tme");
		this.width = width;
		this.height = height;
		/*for(int y = 0; y < height; y++) {
			for(int x = 0; x < width;x++) {
				if(allTilesFront[x][y] != null)
					System.out.print(allTilesFront[x][y].getID() + ",");
			}
			System.out.println();
		}*/
		
	}
	public void setDungeonFromTileIndex(int gridx, int gridy) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width;x++) {
				if(allTilesFront[x][y] != null) {
					if(!(allTilesFront[x][y].getName().equals("Voidt") && allTilesBack[x][y].getName().equals("Voidt"))) {
						try {
							World.setTileFromIndex(gridx+x, gridy+y, (Tile) allTilesFront[x][y].clone());
							World.setTileFromIndexBack(gridx+x, gridy+y, (Tile) allTilesBack[x][y].clone());
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		}
	}
	public void generateDungeonIfSurrounded(int gridx, int gridy) {
		boolean submerged = true;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width;x++) {
				if(World.getTileFromChunk(gridx + x, gridy + y).getName().equals("Voidt"))
				{
					submerged = false;
				}
			}

		}
		if(submerged) {
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width;x++) {
					if(allTilesFront[x][y] != null) {	
						try {
							World.setTileFromIndex(gridx+x, gridy+y, (Tile) allTilesFront[x][y].clone());
							World.setTileFromIndexBack(gridx+x, gridy+y, (Tile) allTilesBack[x][y].clone());
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}
		
	}
	public void setDungeonFromTileIndexWithAIR(int gridx, int gridy) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width;x++) {
				if(allTilesFront[x][y] != null) {	
					try {
						World.setTileFromIndex(gridx+x, gridy+y, (Tile) allTilesFront[x][y].clone());
						World.setTileFromIndexBack(gridx+x, gridy+y, (Tile) allTilesBack[x][y].clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}
	public void loadLayerFront(String path) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line = "";
			int x = 0,y = 0;
			String ID = "";
			while ((line = in.readLine()) != null)
			{	
			    for(int i = 0; i < line.length(); i++) { 	
					if(line.charAt(i) == ',') {
						this.allTilesFront[x][y] = TileManager.dictionnary[Integer.parseInt(ID)];
						ID = "";
						x++;
					}
				    else {
				    	ID += line.charAt(i);
				    }
				}
			    x=0;
			    y++;

			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadLayerBack(String path) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line = "";
			int x = 0,y = 0;
			String ID = "";
			while ((line = in.readLine()) != null)
			{	
			    for(int i = 0; i < line.length(); i++) { 	
					if(line.charAt(i) == ',') {
						this.allTilesBack[x][y] = TileManager.dictionnary[Integer.parseInt(ID)];
						ID = "";
						x++;
					}
				    else {
				    	ID += line.charAt(i);
				    }
				}
			    x=0;
			    y++;

			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
