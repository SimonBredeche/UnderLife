package inventoryPack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import asset.ProjectileManager;
import mainGame.WindowGame;
import mainGame.World;

public class FiringWeapon extends Item{
	protected int delay = 0;
	protected int runtime = 0;
	protected Projectile project;
	public FiringWeapon(Image image, String name, boolean place,int delay) {
		super(image, name, place);
		this.delay = delay;
		this.setTag("FiringWeapon");
	}
	public void assign() {
		if(WindowGame.player.getInventory().getProjectile().getCurrentitem() != null) {
			this.project = ProjectileManager.searchProjectileByName(WindowGame.player.getInventory().getProjectile().getCurrentitem().getName());
		}
	}
	public void consume() {
		if(WindowGame.player.getInventory().getProjectile() != null) {
			if(WindowGame.player.getInventory().getProjectile().getQuantity() > 1) {
				WindowGame.player.getInventory().getProjectile().setQuantity(WindowGame.player.getInventory().getProjectile().getQuantity()-1);
			}
			else {
				WindowGame.player.getInventory().getProjectile().setCurrentitem(null);
				this.project = null;
			}
		}
	}
	
	@Override
	public void update(GameContainer gc,int delta) {
		Input input = gc.getInput();
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			try {
				Projectile temp = (Projectile) ProjectileManager.arrow.clone();
				int x = (int)WindowGame.player.getX();
				int y = (int)WindowGame.player.getY();
				int pointx = WindowGame.cursor.getTileXIndex()*World.tileSize;
				int pointy = WindowGame.cursor.getTileYIndex()*World.tileSize;
				temp.initProjectile(x, y, pointx, pointy);
				World.allEntity.add(temp);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			//new Projectile(this.shootImg,"arrow",(int)WindowGame.player.getX(),(int)WindowGame.player.getY(),AssetManager.arrow716);
		}
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public Projectile getProject() {
		return project;
	}
	public void setProject(Projectile project) {
		this.project = project;
	}




}
