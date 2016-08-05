package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

 

public class Player {
	
	public enum Action{
		IDLE_LEFT, IDLE_RIGHT, IDLE_UP, IDLE_DOWN,
		WALK_LEFT, WALK_RIGHT, WALK_UP, WALK_DOWN,
		ATTACK_LEFT, ATTACK_RIGHT, ATTACK_UP, ATTACK_DOWN
	};
	
	
	private static int        frame_cols;         // #1
    private static int        frame_rows;         // #2

    Animation                       animation;          // #3
    Texture                         walkSheet;              // #4
    TextureRegion[]                 walkFrames;             // #5
    SpriteBatch                     spriteBatch;            // #6
    TextureRegion                   currentFrame;           // #7

    float stateTime;                                        // #8
	
	
	TextureRegion playerImage;
	Action action;
	Rectangle player;
	Boolean activeAnimationLeft = false;
	Boolean activeAnimationRight = false;
	
	public Player(){
		
		/*Attributes attributes = new Attributes();
		Equipment equipment = new Equipment();
		Inventory inventory = new Inventory();
		Skills skills = new Skills();*/
		
		action = Action.WALK_RIGHT;
		animationHandler(this.action);
		playerImage = getImage();
		
		player = new Rectangle();
		player.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		player.y = 20; // bottom left corner of the bucket is 20 pixels above
						// the bottom screen edge
		player.width = 64;
		player.height = 64;
		
	}
	
	public void animationHandler(Action act){
		if(act == Action.WALK_RIGHT && !activeAnimationRight){
			activeAnimationRight = true;
			activeAnimationLeft = false;
			walkRightAnimation();
		}
		if(act == Action.WALK_LEFT && !activeAnimationLeft ){
			activeAnimationLeft = true;
			activeAnimationRight = false;
			walkLeftAnimation();
		}
		
	}
	
	void walkRightAnimation(){
		frame_cols = 3;
		frame_rows = 2;
		
		walkSheet = new Texture(Gdx.files.internal("playerWalkingRight.png")); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/frame_cols, walkSheet.getHeight()/frame_rows);              // #10
        walkFrames = new TextureRegion[frame_cols * frame_rows];
        int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(0.125f, walkFrames);      // #11
        spriteBatch = new SpriteBatch();                // #12
        stateTime = 0f;   
		
	};
	
	void walkLeftAnimation(){
		
		frame_cols = 3;
		frame_rows = 2;
		
		walkSheet = new Texture(Gdx.files.internal("playerWalkingLeft.png")); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/frame_cols, walkSheet.getHeight()/frame_rows);              // #10
        walkFrames = new TextureRegion[frame_cols * frame_rows];
        int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(0.125f, walkFrames);      // #11
        spriteBatch = new SpriteBatch();                // #12
        stateTime = 0f;   
		
	}
	
	
	public TextureRegion getImage(){
		stateTime += Gdx.graphics.getDeltaTime();
		return playerImage = animation.getKeyFrame(stateTime, true);
	}
	
	
	
	
}
