package es.iesoretania.dam2.hlc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Ut2_b03_02 extends ApplicationAdapter {
	Stage stage;
	OrthographicCamera camera;

	@Override
	public void create() {
		// Crear el stage y el actor de la nave
		stage = new Stage();
		Actor nave = new Nave();
		// Añadir el actor al stage y permitirle que gestione el teclado
		stage.addActor(nave);
		Gdx.input.setInputProcessor(stage);
		stage.setKeyboardFocus(nave);
		// Preparar la cámara
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		// Crear viewport para el stage asociado a la cámara
		Viewport viewport = new ScreenViewport(camera);
		stage.setViewport(viewport);
	}

	@Override
	public void render() {
		// Borrar pantalla
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Permitir que los actores hagan sus tareas
		stage.act(Gdx.graphics.getDeltaTime());
		// Dibujar el stage
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}