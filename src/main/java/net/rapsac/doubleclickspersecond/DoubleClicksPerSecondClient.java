package net.rapsac.doubleclickspersecond;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;


public class DoubleClicksPerSecondClient implements ClientModInitializer {
	public static final String MOD_ID = "doubleclickspersecond";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static boolean renderOverlayBool = true;
	
	@Override
	public void onInitializeClient() {
		KeyBinding RenderCpsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(MOD_ID, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.tiptapshow.main"));
		
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (RenderCpsKey.wasPressed()){
				if(renderOverlayBool){
					renderOverlayBool = false;
				}else{
					renderOverlayBool = true;
				}
			}
		});
	}

	public static boolean RenderOverlay(){
		return renderOverlayBool;
	}
}