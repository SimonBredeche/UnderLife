package mainGame;

import javax.swing.JFrame;


import org.newdawn.slick.BasicGame;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


import asset.AssetManager;
import asset.DungeonManager;
import asset.EntityManager;
import asset.ItemManager;
import asset.SoundManager;
import asset.TileManager;
import inventoryPack.CraftInventory;
import inventoryPack.Inventory;



public class WindowGame extends BasicGame {
	
	public static final int WIDTH = 1920, HEIGHT = 1080;
	private boolean leftclick = false;
	public static World world;
	AssetManager asset;
	private CraftInventory craftinventory;
	private BreakerAnimation breaker;
	public static Player player;
	public static Image background;
	public static Cursor cursor;
	public static Inventory currentInv;
	public static DeadScreen deadscreen;
	public static int SPAWNX; 
	public static int SPAWNY; 
    public WindowGame() {
        super("mainGame :: WindowGame");
    }
    
    
    @Override
    public void init(GameContainer container) throws SlickException {
    	container.setVSync(true);
    	asset = new AssetManager();
    	SoundManager.loadMusic();
    	EntityManager.loadEntity();
    	TileManager.loadTiles(container);
    	ItemManager.loadItems();
    	DungeonManager.loadDungeon();
    	deadscreen = new DeadScreen();
    	craftinventory = new CraftInventory();
    	craftinventory.loadCraft(container);
    	player = new Player(container);
    	

    	world = new World(container);
    	SPAWNX = (12*World.getTileSize())*world.getMapWidth()/2;
    	SPAWNY = (12*World.getTileSize())*world.getMapHeight()/2;
    	player.setX(SPAWNX);
		player.setY(SPAWNY);
		player.setChunkx(Math.round(player.getX()/World.tileSize/12));
		player.setChunky(Math.round(player.getY()/World.tileSize/12));
    	//container.setFullscreen(true);
		background = AssetManager.caveback;
		cursor = new Cursor(AssetManager.Cursor);
		this.breaker =  new BreakerAnimation();
		
		//GENERATE SAFE 3*3 AREA
		int tileX = Math.round(player.getX()/World.tileSize);
		int tileY = Math.round(player.getY()/World.tileSize); 
		SoundManager.maintheme.loop();
		for(int i = -9; i < 9; i++) {
			for(int j = -9; j < 9; j++) {
				World.setTileFromIndex(tileX+i,tileY+j, TileManager.voidt);
			}
		}
		for(int i = 0; i < 99; i++)
			player.getInventory().addItem(TileManager.woodplat);
		//DungeonManager.tree1.setDungeonFromTileIndex(tileX-1, tileY-6);
		//container.setSoundVolume(0);
		//container.setMusicVolume(0);
		//WindowGame.world.spawnEntity(player.getX(), player.getY(), EntityManager.janga);
		///WindowGame.world.spawnEntity(player.getX(), player.getY(), EntityManager.asmora);
		
		
    }
    
    


	@Override
    public void render(GameContainer container, Graphics g) throws SlickException {
		g.clear();
		g.resetTransform();
		g.drawImage(background, 0, 0);
		g.setBackground(Color.darkGray);
		g.translate(container.getWidth() / 2 - (int) player.getX(), 
                container.getHeight() / 2 - (int) player.getY());
		
		
		
    	world.render(g,container,3,2, Math.round(player.getX()/World.getTileSize())/12, Math.round((player.getY()/World.getTileSize())/12));
    	if(!deadscreen.isDead())
    		player.render(g);
    	
    	if(this.leftclick) {
    		this.breaker.Draw(g, cursor.getTileXIndex()*World.tileSize, cursor.getTileYIndex()*World.tileSize);
    	}
    	
    	world.renderEntity(g);
    	world.renderLivingEntity(g,container);
    	if(!player.getInventory().isOpen())
    		cursor.Draw(g);
    	player.getInventory().render(g,container);
    	if(currentInv != null) {
    		currentInv.render(g, container);
    	}
    	this.craftinventory.render(g, container);
    	String chunkinfo = "\nChunk : " + player.getChunkx() + " , " + player.getChunky();
    	String entityinf = "\nEntity : " + World.allLivingEntity.size();
    	g.drawString("X : " + player.getX()/World.tileSize + " Y : " +player.getY()/World.tileSize + chunkinfo + entityinf , 1450, 1000);
    	if(player.getInventory().isOpen()) {
    		cursor.drawInnerCursor(g,container);
    	}
    	
    	if(WindowGame.deadscreen.isDead()) {
    		WindowGame.deadscreen.render(g);
    	}

    	
    	
    	
    	//this.world.Dummyrender(g);



    	
    	

    }


    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    		Input input = container.getInput();
			double detx = cursor.getTileXIndex() - WindowGame.player.getX()/World.tileSize;
			double dety = cursor.getTileYIndex() - WindowGame.player.getY()/World.tileSize;
			double distance = Math.sqrt(Math.pow(detx, 2) +  Math.pow(dety, 2));
    		world.updateEntity();
    		world.updateLivingEntity(delta,distance,container);
    	 	//player.updateCam(world);
    		world.UpdateWorlMap(3, 3, Math.round(player.getX()/World.getTileSize())/12, Math.round((player.getY()/World.getTileSize())/12),delta,container);
    		this.updateBreakAnimation(distance);
    		if(deadscreen.isDead()) {
    			deadscreen.update(delta);

    		}
    		else {
    			player.updatePlayer(delta);
    			this.detectedInput(input, delta);
    		}

            cursor.updateCursor(container,distance,delta);
            
    		
    }
    

    
    

  
	@Override
	public void mousePressed(int button, int x, int y) {
		switch (button) {
			case Input.MOUSE_RIGHT_BUTTON:
				cursor.setPlacing(true);
				break;
			case Input.MOUSE_LEFT_BUTTON:
				this.leftclick = true;
				break;
		}
	}
	@Override
	public void mouseReleased(int button, int x, int y){
		cursor.setBreaking(false);
		cursor.setPlacing(false);
		this.leftclick = false;
		
		
	}
	@Override
	public void mouseWheelMoved(int change) {
		if(!player.getInventory().isOpen()) {
			if(change < 0) {
				if(player.getInventory().getSelectedX() < 8)
					player.getInventory().setSelectedX(player.getInventory().getSelectedX()+1);
				else
					player.getInventory().setSelectedX(0);
			}
			else {
				if(player.getInventory().getSelectedX() > 0 )
					player.getInventory().setSelectedX(player.getInventory().getSelectedX()-1);
				else
					player.getInventory().setSelectedX(8);
			}
		}
	}
    public void updateBreakAnimation(double distance) {
    	if(distance <2) {
    		cursor.getImage().setImageColor(0, 255, 0);
	    	if(this.leftclick && World.getTileFromChunk(cursor.getTileXIndex(), cursor.getTileYIndex()).getName() != "Voidt") {
	    		
	    		if(!SoundManager.pioche.playing())
	    			SoundManager.pioche.play();
	    		
	    		if(World.getTileFromChunk(cursor.getTileXIndex(), cursor.getTileYIndex()).getHarvestLevel() <= cursor.getCurrentEfficiency()) {
			    	this.breaker.setMaxTick(World.getTileFromChunk(cursor.getTileXIndex(), cursor.getTileYIndex()).getDurability());
			    	this.breaker.setTick(this.breaker.getTick()	+ cursor.getCurrentEfficiency());
					if(this.breaker.isReady()) {
						cursor.setBreaking(true);
						breaker.resetbar();
					}
					this.breaker.setUpdate(true);
					this.breaker.update();
	    		}
	    	}
	    	else {
	    		SoundManager.pioche.stop();
	    	}
    	}
    	else {
    		cursor.getImage().setImageColor(255, 0, 0);
    	}
    }
    
    public static void main(String[] args) throws SlickException {
    	JFrame frame = new JFrame();
 	    CanvasGameContainer app = new CanvasGameContainer(new WindowGame());
 	    frame.setUndecorated(true);
 	    frame.setVisible(true);
 	    frame.add(app);
 	    frame.setSize(1920, 1080);
 	    app.start();

      

        
    }
    
    @Override
    public void keyReleased(int key, char c) {
    	player.east = false;
    	//player.north = false;
    	player.south = false;
    	player.west = false;
    	if(key == Input.KEY_Q || key == Input.KEY_S || key == Input.KEY_Z || key == Input.KEY_D) {
    		player.setMoving(false);
    		
    		
    	}
    	if(key == Input.KEY_Z || key == Input.KEY_S) {
    		player.setFacing("none");
    	}
    	
    }

    @Override
    public void keyPressed(int key, char c){
    		/*if(key == Input.KEY_D) {
				player.setDirection(3);
				player.east = true;
				player.setMoving(true); 
    		}
    		if(key == Input.KEY_Q) {
				player.setDirection(1);
				player.west = true;
				player.setMoving(true);
    		}
    		if(key == Input.KEY_S) {
				player.setDirection(2);
				player.south = true;
				player.setMoving(true);
    		}
    		if(key == Input.KEY_Z) {
				player.setDirection(0);
				player.north = true;
				player.setMoving(true);
    		}*/
    		
	    	switch (key) {
			/*case Input.KEY_D: 
				player.setDirection(3);
				player.east = true;
				player.setMoving(true); 
				break;
			case Input.KEY_Q: 
				player.setDirection(1);
				player.west = true;
				player.setMoving(true);
				break;
			case Input.KEY_S:
				player.setDirection(2);
				player.south = true;
				player.setMoving(true);
				break;
			case Input.KEY_Z: 
				player.setDirection(0);
				player.north = true;
				player.setMoving(true);
				break;*/
			case Input.KEY_E:
				if(currentInv == null) {
					player.getInventory().setOpen(!player.getInventory().isOpen());
					if(player.getInventory().isOpen()) {
						this.craftinventory.setOpen(true);
					}
					else {
						this.craftinventory.setOpen(false);
					}
				}
				break;
			case Input.KEY_F:
				player.setFly(!player.getFly());
				break;
		}
	    switch(key) {
		case Input.KEY_1:
			if(!player.getInventory().isOpen()) {
				player.getInventory().setSelectedX(0);
			}
			break;
		case Input.KEY_2:
			if(!player.getInventory().isOpen()) {
				player.getInventory().setSelectedX(1);
			}
			break;
		case Input.KEY_3:
			if(!player.getInventory().isOpen()) {
				player.getInventory().setSelectedX(2);
			}
			break;
		case Input.KEY_4:
			if(!player.getInventory().isOpen()) {
				player.getInventory().setSelectedX(3);
			}
			break;
		case Input.KEY_5:
			if(!player.getInventory().isOpen()) {
				player.getInventory().setSelectedX(4);
			}
			break;
		case Input.KEY_6:
			if(!player.getInventory().isOpen()) {
				player.getInventory().setSelectedX(5);
			}
			break;
		case Input.KEY_7:
			if(!player.getInventory().isOpen()) {
				player.getInventory().setSelectedX(6);
			}
			break;
		case Input.KEY_8:
			if(!player.getInventory().isOpen()) {
				player.getInventory().setSelectedX(7);
			}
			break;
		case Input.KEY_9:
			if(!player.getInventory().isOpen()) {
				player.getInventory().setSelectedX(8);
			}
			break;
			
	    }
    }
	public void detectedInput(Input input,int delta) {
		if(input.isKeyDown(Input.KEY_D)) {
			player.setDirection(3);
			player.east = true;
			player.setMoving(true); 
			player.setFacing("vertical");
			player.updatePosition(delta);
		}
		if(input.isKeyDown(Input.KEY_Q)) {
			player.setDirection(1);
			player.west = true;
			player.setMoving(true);
			player.setFacing("vertical");
			player.updatePosition(delta);
		}
		if(input.isKeyDown(Input.KEY_S)) {
			player.setDirection(2);
			player.south = true;
			player.setMoving(true);
			player.setFacing("down");
			player.updatePosition(delta);
		}
		if(input.isKeyDown(Input.KEY_Z)){
			if(player.isOnGround())
				player.setJumpheight(29);
			player.setDirection(0);
			player.north = true;
			player.setFacing("up");
			player.setMoving(true);
			player.updatePosition(delta);
		}
		player.setDirection(5);
		player.updatePosition(delta);
	}

}