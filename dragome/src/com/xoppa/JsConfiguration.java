package com.xoppa;

import com.badlogic.gdx.backends.dragome.DragomeConfiguration;
import com.dragome.commons.DragomeConfiguratorImplementor;

@DragomeConfiguratorImplementor(priority = 11)
public class JsConfiguration extends DragomeConfiguration{

	@Override
	public boolean projectClassPathFilter(String projectPath) {
		boolean include = false;
		include |= projectPath.contains("ui.jar") || projectPath.contains("ui\\bin") || projectPath.contains("vis-");
		include |= projectPath.contains("shadertoy");
		return include;
	}
	
	@Override
	public boolean isRemoveUnusedCode() {
		// True will make proguard use addicitonal-code-keep to reduce javascript size. 
		// Got to be carefull with the configuration because it can rip out needed class !!!
		return false; // Dont work now. Require some adjustments 
	}
	
}
