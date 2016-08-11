package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class InventoryScreen implements Screen {

	final Drop game;
	OrthographicCamera camera;
	
	Texture Inventory;
	Texture InventoryMark;
	
	Float InventoryMarkX;
	Float InventoryMarkY;
	
	Float MousePosX;
	Float MousePosY;
	
	int row = 0;
	int rowLength = 7;
	int maxItems = 10;
	
	int[][] inventoryPos;
	Item[] inventory;
	
	public InventoryScreen(final Drop gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		Inventory =  new Texture(Gdx.files.internal("inventory.png"));
		InventoryMark =  new Texture(Gdx.files.internal("inventoryMark.png"));
		
		inventoryPos = new int[row][rowLength]; // 0,0 = 250, 258
		inventory = new Item[maxItems];
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(Inventory, 200, 50);
		
		//if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			MousePosX = touchPos.x;
			MousePosY = touchPos.y;		
			
			if((MousePosX >= (float) 232 && MousePosX <= 261)
					&& (MousePosY >= 241 && MousePosY <= 273)){
				
				game.font.draw(game.batch, "Position 0.0 at X: 232, Y: 241", 100, 85);
				
			}
			
			game.font.draw(game.batch, MousePosX.toString() + " "
			+ MousePosY.toString() , 100, 100);
			
			if(Gdx.input.isTouched() && (MousePosX >= (float) 232 && MousePosX <= 261)
					&& (MousePosY >= 241 && MousePosY <= 273)){
				
				InventoryMarkX = (float) 232;
				InventoryMarkY = (float) 241;
				
				inventory[0] = new Item();
				
			}
			
			
			for(Item i : inventory ){
				if(i == null)
					continue;
			game.batch.draw(i.getImage(), InventoryMarkX, InventoryMarkY);
				
			
			}

			//InventoryMarkX = touchPos.x - 16;
			//InventoryMarkY = touchPos.y - 16;
			
			
			//game.batch.draw(InventoryMark, InventoryMarkX, InventoryMarkY);
		//}
		
		game.batch.end();

		if (Gdx.input.isKeyJustPressed(Keys.I)) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}



}
