package es.iesoretania.dam2.hlc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Nave extends Actor {
    enum VerticalMovement { UP, NONE, DOWN };
    enum HorizontalMovement { LEFT, NONE, RIGHT };

    private static TextureRegion naveReposo = null;
    private static TextureRegion naveIzq = null;
    private static TextureRegion naveDcha = null;
    private static TextureRegion naveArriba = null;
    private static TextureRegion naveArribaIzq = null;
    private static TextureRegion naveArribaDcha = null;
    private static TextureRegion naveAbajo = null;
    private static TextureRegion naveAbajoIzq = null;
    private static TextureRegion naveAbajoDcha = null;

    HorizontalMovement horizontalMovement;
    VerticalMovement verticalMovement;

    TextureRegion regionActual;

    public Nave(float x, float y) {
        //Cargamos las diferentes texturas de esta manera para solo tenerlo que hacer una vez y que sea optimo
        if (naveAbajoIzq == null) {
            Texture completo = new Texture(Gdx.files.internal("spacetheme.png"));
            naveArribaIzq = new TextureRegion(completo, 0, 44, 39, 43);
            naveArriba = new TextureRegion(completo, 42, 44, 39, 43);
            naveArribaDcha = new TextureRegion(completo, 84, 44, 39, 43);
            naveIzq = new TextureRegion(completo, 0, 0, 39, 43);
            naveReposo = new TextureRegion(completo, 42, 0, 39, 43);
            naveDcha = new TextureRegion(completo, 84, 0, 39, 43);
            naveAbajoIzq = new TextureRegion(completo, 0, 88, 39, 43);
            naveAbajo = new TextureRegion(completo, 42, 88, 39, 43);
            naveAbajoDcha = new TextureRegion(completo, 84, 88, 39, 43);
        }
        regionActual = naveReposo;
        horizontalMovement = HorizontalMovement.NONE;
        verticalMovement = VerticalMovement.NONE;
        setSize(regionActual.getRegionWidth(), regionActual.getRegionHeight());
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
        addListener(new NaveInputListener());
    }

    //Indicamos el talla de la pantalla
    public Nave() {
        this(400, 240);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(regionActual, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //Indicamos al actor que tiene que hacer cuando pulsamos esa tecla
        if (verticalMovement == VerticalMovement.UP) {
            this.moveBy(0, 100 * delta);
        }
        if (verticalMovement == VerticalMovement.DOWN) {
            this.moveBy(0, -100 * delta);
        }
        if (horizontalMovement == HorizontalMovement.LEFT) {
            this.moveBy(-100 * delta, 0);
        }
        if (horizontalMovement == HorizontalMovement.RIGHT) {
            this.moveBy(100 * delta, 0);
        }

        //Indicamos al actor que no se salga de los limites de la pantalla en el eje x y en el eje y
        if (getX() < 0) {
            setX(0);
        }
        if (getY() < 0)
        {
            setY(0);
        }
        if (getX() >= 799 - getWidth()) {
            setX(799 - getWidth());
        }
        if (getY() >= 479 - getHeight()) {
            setY(479 - getHeight());
        }

        //Indicamos al actor cual es su textura cuando pulsamos esa conbinación de teclas
        if (verticalMovement == VerticalMovement.UP && horizontalMovement == HorizontalMovement.LEFT)
            regionActual = naveArribaIzq;
        if (verticalMovement == VerticalMovement.UP && horizontalMovement == HorizontalMovement.NONE)
            regionActual = naveArriba;
        if (verticalMovement == VerticalMovement.UP && horizontalMovement == HorizontalMovement.RIGHT)
            regionActual = naveArribaDcha;
        if (verticalMovement == VerticalMovement.NONE && horizontalMovement == HorizontalMovement.LEFT)
            regionActual = naveIzq;
        if (verticalMovement == VerticalMovement.NONE && horizontalMovement == HorizontalMovement.NONE)
            regionActual = naveReposo;
        if (verticalMovement == VerticalMovement.NONE && horizontalMovement == HorizontalMovement.RIGHT)
            regionActual = naveDcha;
        if (verticalMovement == VerticalMovement.DOWN && horizontalMovement == HorizontalMovement.LEFT)
            regionActual = naveAbajoIzq;
        if (verticalMovement == VerticalMovement.DOWN && horizontalMovement == HorizontalMovement.NONE)
            regionActual = naveAbajo;
        if (verticalMovement == VerticalMovement.DOWN && horizontalMovement == HorizontalMovement.RIGHT)
            regionActual = naveAbajoDcha;
    }

    //
    class NaveInputListener extends InputListener {
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.DOWN:
                    verticalMovement = VerticalMovement.DOWN;
                    break;
                case Input.Keys.UP:
                    verticalMovement = VerticalMovement.UP;
                    break;
                case Input.Keys.LEFT:
                    horizontalMovement = HorizontalMovement.LEFT;
                    break;
                case Input.Keys.RIGHT:
                    horizontalMovement = HorizontalMovement.RIGHT;
                    break;
            }
            return true;
        }

        //
        @Override
        public boolean keyUp(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.DOWN:
                    if (verticalMovement == VerticalMovement.DOWN) {
                        verticalMovement = VerticalMovement.NONE;
                    }
                    break;
                case Input.Keys.UP:
                    if (verticalMovement == VerticalMovement.UP) {
                        verticalMovement = VerticalMovement.NONE;
                    }
                    break;
                case Input.Keys.LEFT:
                    if (horizontalMovement == HorizontalMovement.LEFT) {
                        horizontalMovement = HorizontalMovement.NONE;
                    }
                    break;
                case Input.Keys.RIGHT:
                    if (horizontalMovement == HorizontalMovement.RIGHT) {
                        horizontalMovement = HorizontalMovement.NONE;
                    }
                    break;
            }
            return true;
        }
    }
}