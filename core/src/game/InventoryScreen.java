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

public class InventoryScreen implements Screen {

	final Drop game;
	OrthographicCamera camera;
	
	Texture Inventory;
	Texture InventoryMark;
	
	Float InventoryMarkX;
	Float InventoryMarkY;
	
	Float MousePosX;
	Float MousePosY;
	
	public InventoryScreen(final Drop gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		Inventory =  new Texture(Gdx.files.internal("inventory.png"));
		InventoryMark =  new Texture(Gdx.files.internal("inventoryMark.png"));
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
			
			game.font.draw(game.batch, MousePosX.toString() + " "
			+ MousePosY.toString() , 100, 100);

			InventoryMarkX = touchPos.x - 16;
			InventoryMarkY = touchPos.y - 16;
			game.batch.draw(InventoryMark, InventoryMarkX, InventoryMarkY);
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
