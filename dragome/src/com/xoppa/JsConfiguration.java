package com.xoppa;

import java.io.File;

import com.badlogic.gdx.backends.dragome.DragomeConfiguration;
import com.badlogic.gdx.utils.Array;
import com.dragome.commons.DragomeConfiguratorImplementor;

@DragomeConfiguratorImplementor(priority = 11)
public class JsConfiguration extends DragomeConfiguration{

	@Override
	public boolean projectClassPathFilter(String projectPath) {
		boolean include = false;
		include |= projectPath.contains("/UI/bin");
		include |= projectPath.contains("ui.jar") || projectPath.contains("ui\\bin") || projectPath.contains("vis-");
		include |= projectPath.contains("shadertoy");
		if(projectPath.contains("resources"))
			include = false;
		return include;
	}

	@Override
	public boolean skipAssetCopy() {
		return false;
	}

	@Override
	public boolean isRemoveUnusedCode() {
		// True will make proguard use addicitonal-code-keep to reduce javascript size.
		// Got to be carefull with the configuration because it can rip out needed class !!!
		return false; // Dont work now. Require some adjustments
	}

	@Override
	public void assetsPath(Array<File> paths) {
		boolean flag = new File("." + File.separatorChar + "webapp").exists();
		String path = "." + File.separatorChar + "android" + File.separatorChar + "assets" + File.separatorChar;
		if(flag)
			path = "." + path;
		paths.add(new File(path));
	}

	@Override
	public void assetsClasspath(Array<String> filePath) {
		filePath.add("com/kotcrab/vis/ui/i18n/");
		filePath.add("com/kotcrab/vis/ui/skin/x1/");
		filePath.add("com/kotcrab/vis/ui/widget/color/internal/");
	}

	@Override
	public String getCompiledPath() {
		String compiledPath = super.getCompiledPath();
		if(compiledPath == null)
			compiledPath = new File("").getAbsolutePath() + File.separatorChar + "dragome" + File.separatorChar + "webapp";
		return compiledPath;
	}

}
