package com.xoppa.gdx.shadertoy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GdxShaderToy extends ApplicationAdapter {
	Stage stage;
	Texture img;
	FullQuadToy toy;
	CollapsableTextWindow logWindow;
	CollapsableTextWindow vsWindow;
	CollapsableTextWindow fsWindow;
	float codeChangedTimer = -1f;

	@Override
	public void create () {
		ShaderProgram.pedantic = false;
		img = new Texture(Gdx.files.internal("badlogic.jpg"));
		img.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		VisUI.load();
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		toy = new FullQuadToy();
		toy.create(new Logger("TEST", Logger.INFO) {
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			private void add(String message) {
				logWindow.addText(df.format(new Date()) + " " + message + "\n");
			}

			@Override
			public void info(String message) {
				super.info(message);
				//add("[#ffff00]" + message + "[]");
				add("INFO: " + message);
			}

			@Override
			public void error(String message) {
				super.error(message);
				//add("[red]" + message + "[]");
				add("ERROR: " + message);
			}
		});
		toy.setTexture(img);

		ChangeListener codeChangeListener = new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				codeChangedTimer = 3f;
			}
		};

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		float hw = w * 0.5f;
		logWindow = new CollapsableTextWindow("Log", 0, 0, w, 100f);
		stage.addActor(logWindow);

        final String defaultVS = "";
		final String defaultFS = "";

		vsWindow = new CollapsableTextWindow("Vertex Shader", 0, 100f, hw, h - 100f);
		vsWindow.setText(defaultVS);
		vsWindow.addTextAreaListener(codeChangeListener);
		stage.addActor(vsWindow);
		fsWindow = new CollapsableTextWindow("Fragment Shader", hw, 100f, hw, h - 100f);
		fsWindow.setText(defaultFS);
		fsWindow.addTextAreaListener(codeChangeListener);
		stage.addActor(fsWindow);

		//toy.setShader(defaultVS, defaultFS);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
		toy.resize(width, height);
	}

	@Override
	public void render () {
        update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		toy.render();
		stage.act();
		stage.draw();
	}

    private void update() {
        if (codeChangedTimer > 0f) {
            codeChangedTimer -= Gdx.graphics.getDeltaTime();
            if (codeChangedTimer <= 0) {
				toy.setShader(vsWindow.getText(), fsWindow.getText());
            }
        }
    }

	@Override
	public void dispose() {
		VisUI.dispose();
		stage.dispose();
		img.dispose();
	}
}