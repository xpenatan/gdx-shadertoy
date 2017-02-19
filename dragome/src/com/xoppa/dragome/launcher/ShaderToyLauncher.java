package com.xoppa.dragome.launcher;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.dragome.DragomeApplication;
import com.badlogic.gdx.backends.dragome.DragomeApplicationConfiguration;
import com.badlogic.gdx.backends.dragome.DragomeWindow;
import com.badlogic.gdx.backends.dragome.preloader.AssetDownloader.AssetLoaderListener;
import com.badlogic.gdx.backends.dragome.preloader.AssetType;
import com.dragome.web.annotations.PageAlias;
import com.xoppa.gdx.shadertoy.GdxShaderToy;


@PageAlias(alias= "ShaderToy-Dragome")
public class ShaderToyLauncher extends DragomeApplication
{

	@Override
	public ApplicationListener createApplicationListener()
	{
		getPreloader().loadAsset("com/kotcrab/vis/ui/skin/x1/font-small.fnt", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/skin/x1/default.fnt", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/skin/x1/uiskin.json", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/skin/x1/uiskin.atlas", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/skin/x1/uiskin.png", AssetType.Image, null, new AssetLoaderListener<Object>());

		getPreloader().loadAsset("com/kotcrab/vis/ui/i18n/ButtonBar.properties", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/i18n/ColorPicker.properties", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/i18n/Dialogs.properties", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/i18n/FileChooser.properties", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/i18n/TabbedPane.properties", AssetType.Text, null, new AssetLoaderListener<Object>());

		getPreloader().loadAsset("com/kotcrab/vis/ui/widget/color/internal/checkerboard.frag", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/widget/color/internal/default.vert", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/widget/color/internal/hsv.frag", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/widget/color/internal/palette.frag", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/widget/color/internal/rgb.frag", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("com/kotcrab/vis/ui/widget/color/internal/verticalBar.frag", AssetType.Text, null, new AssetLoaderListener<Object>());

		getPreloader().loadAsset("shaders/default.fragment.glsl", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("shaders/default.vertex.glsl", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("shaders/water.fragment.glsl", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("shaders/water.vertex.glsl", AssetType.Text, null, new AssetLoaderListener<Object>());
		getPreloader().loadAsset("badlogic.jpg", AssetType.Image, null, new AssetLoaderListener<Object>());
		return new GdxShaderToy();
	}

	@Override
	public DragomeApplicationConfiguration getConfig()
	{
		return null;
	}

	@Override
	protected void onResize()
	{
		int clientWidth= DragomeWindow.getInnerWidth();
		int clientHeight= DragomeWindow.getInnerHeight();
		getCanvas().setWidth(clientWidth);
		getCanvas().setHeight(clientHeight);
		getCanvas().setCoordinateSpaceWidth(clientWidth);
		getCanvas().setCoordinateSpaceHeight(clientHeight);
		super.onResize();
	}
}
