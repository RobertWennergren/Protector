package player;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Demo extends ApplicationAdapter {
	Texture slimeImage;
	Texture fighterImage;
	Texture swordImage;
	private Sound deadSilmeSound;
	private Music adventureMusic;
	private Sound swingSound;
	private OrthographicCamera camera;
	public SpriteBatch batch;
	private Rectangle fighter;
	private Rectangle sword;
	private Array<Rectangle> slimes;
	private long lastDropTime;
	private boolean swordSlash;
	
	@Override
	public void create () {
		// load the images for the droplet and the bucket, 64x64 pixels each
	      slimeImage = new Texture(Gdx.files.internal("slime.png"));
	      fighterImage = new Texture(Gdx.files.internal("fighter.jpg"));
	      swordImage =  new Texture(Gdx.files.internal("sword.png"));
	    		  
	      // load the drop sound effect and the rain background "music"
	      deadSilmeSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	      adventureMusic = Gdx.audio.newMusic(Gdx.files.internal("Adventure.mp3"));

	    
	      
	      // start the playback of the background music immediately
	      adventureMusic.setLooping(true);
	      adventureMusic.play();
	      
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 800);
	      batch = new SpriteBatch();
	      
	      fighter = new Rectangle();
	      fighter.x = 800 / 2 - 64 / 2;
	      fighter.y = 200;
	      fighter.width = 64;
	      fighter.height = 64;      
	      
	      slimes = new Array<Rectangle>();
	      spawnSlime();
	      
	      sword = new Rectangle();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    camera.update();
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    batch.draw(fighterImage, fighter.x, fighter.y);
	   // spawnSword();
	    for(Rectangle slime: slimes) {
	        batch.draw(slimeImage, slime.x, slime.y);
	     }
	    //if(Gdx.input.isTouched()){
	    if(swordSlash){	   
	    	batch.draw(swordImage, fighter.x + 16, fighter.y + 64);    
	    }
	   // }
	    batch.end();
	    /*if(Gdx.input.isTouched()) {
	        Vector3 touchPos = new Vector3();
	        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	        camera.unproject(touchPos);
	        fighter.x = touchPos.x - 64 / 2;
	     }*/
	    if(Gdx.input.isKeyPressed(Keys.SPACE)) spawnSwordRectangle();
	    if(!Gdx.input.isKeyPressed(Keys.SPACE)) killSword();
	    if(Gdx.input.isKeyPressed(Keys.LEFT)) fighter.x -= 300 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.RIGHT)) fighter.x += 300 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.DOWN)) fighter.y -= 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Keys.UP)) fighter.y += 200 * Gdx.graphics.getDeltaTime();
	    
	    if(fighter.x < 0) fighter.x = 0;
	    if(fighter.x > 800 - 64) fighter.x = 800 - 64;
	    if(fighter.y < 0) fighter.y = 0;
	    if(fighter.y > 800 - 64) fighter.y = 800 - 64;
	    
	    
	    
	    if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnSlime();
	    Iterator<Rectangle> iter = slimes.iterator();
	    while(iter.hasNext()) {
	       Rectangle slime = iter.next();
	       slime.y -= 200 * Gdx.graphics.getDeltaTime();
	       if(slime.y + 64 < 0) iter.remove();
	       if(slime.overlaps(sword) && swordSlash) {
	           deadSilmeSound.play();
	           iter.remove();
	        }
	    }
	}
	
	private void spawnSwordRectangle(){
		
	    sword.x = fighter.x + 32;
	    sword.y = fighter.y + 64;
	    sword.width = 32;
	    sword.height = 32;	
	    swordSlash = true;
	    
	   }
	
	private void killSword(){
		swordSlash = false;
	}
	
	private void spawnSlime() {
	      Rectangle slime = new Rectangle();
	      slime.x = MathUtils.random(0, 800-64);
	      slime.y = 800;
	      slime.width = 64;
	      slime.height = 64;
	      slimes.add(slime);
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
	@Override
	   public void dispose() {
	      slimeImage.dispose();
	      fighterImage.dispose();
	      deadSilmeSound.dispose();
	      adventureMusic.dispose();
	      batch.dispose();
	      swordImage.dispose();
	   }
	
}
