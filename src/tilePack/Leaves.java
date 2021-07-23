package tilePack;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import asset.AssetManager;
import mainGame.World;

public class Leaves extends BasicTile{

	public Leaves(String name, ArrayList<Image> Frames) {
		super(name, Frames);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void render(Graphics g,GameContainer container) {
		int lenght = Frames.size();
		Image temp = this.img;

		boolean remove= false;
		if(lenght > 4) {
			if(World.getTileFromChunk(this.getX()+1, this.getY()).getName().equals("Voidt") ||
			   World.getTileFromChunk(this.getX()-1, this.getY()).getName().equals("Voidt") ||
			   World.getTileFromChunk(this.getX(), this.getY()-1).getName().equals("Voidt") ||
			   World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt")) {
				

				//CAS QUADRUPLE
				if(World.getTileFromChunk(this.getX()+1, this.getY()).getName().equals("Voidt") &&
				   World.getTileFromChunk(this.getX()-1, this.getY()).getName().equals("Voidt") &&
				   World.getTileFromChunk(this.getX(), this.getY()-1).getName().equals("Voidt") &&
				   World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt")) {
					
					temp = this.Frames.get(4).getScaledCopy(1);
					
				}
				//CAS TRIPLE
				else if(World.getTileFromChunk(this.getX()+1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX()-1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt")) {
					
					temp = this.Frames.get(3).getScaledCopy(1);
					temp.rotate(180);
				}
				else if(World.getTileFromChunk(this.getX()+1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX()-1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()-1).getName().equals("Voidt")) {
					
					temp = this.Frames.get(3).getScaledCopy(1);
				}
				else if(World.getTileFromChunk(this.getX()+1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()-1).getName().equals("Voidt")) {
					
					temp = this.Frames.get(3).getScaledCopy(1);
					temp.rotate(90);
				}
				else if(World.getTileFromChunk(this.getX()-1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()-1).getName().equals("Voidt")) {
					
					temp = this.Frames.get(3).getScaledCopy(1);
					temp.rotate(270);
				}
				//CAS DOUBLE
				else if(World.getTileFromChunk(this.getX()-1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX()+1, this.getY()).getName().equals("Voidt")) {
					
					remove = true;
				}
				else if(World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()-1).getName().equals("Voidt")) {
					
					remove = true;
				}
				else if(World.getTileFromChunk(this.getX()+1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt")) {
					
					temp = this.Frames.get(2).getScaledCopy(1);
					temp.rotate(180);
				}
				else if(World.getTileFromChunk(this.getX()-1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt")) {
					
					temp = this.Frames.get(2).getScaledCopy(1);
					temp.rotate(270);
				}
				else if(World.getTileFromChunk(this.getX()-1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()-1).getName().equals("Voidt")) {
					
					temp = this.Frames.get(2).getScaledCopy(1);
					
				}
				else if(World.getTileFromChunk(this.getX()+1, this.getY()).getName().equals("Voidt") &&
						World.getTileFromChunk(this.getX(), this.getY()-1).getName().equals("Voidt")) {
					
					temp = this.Frames.get(2).getScaledCopy(1);
					temp.rotate(90);
				}
				//CAS SIMPLE
				else if(World.getTileFromChunk(this.getX(), this.getY()-1).getName().equals("Voidt")) {
					temp = this.Frames.get(1).getScaledCopy(1);
				}
				else if(World.getTileFromChunk(this.getX(), this.getY()+1).getName().equals("Voidt")) {
					temp = this.Frames.get(1).getScaledCopy(1);
					temp.rotate(180);
				}
				else if(World.getTileFromChunk(this.getX()+1, this.getY()).getName().equals("Voidt")) {
					temp = this.Frames.get(1).getScaledCopy(1);
					temp.rotate(90);
				}
				else if(World.getTileFromChunk(this.getX()-1, this.getY()).getName().equals("Voidt")) {
					temp = this.Frames.get(1).getScaledCopy(1);
					temp.rotate(270);
				}
					
			}
	
			
		}
		if(remove) {
			Tile tile = new BasicTile("Voidt",AssetManager.voidimg);
			tile.setTag("transparent");
			World.setTileFromIndex(this.x,this.y, tile);
		}
		else {
			g.drawImage(temp,this.xdraw,this.ydraw);
		}
		
		

	}
	



}
