package net.rapsac.doubleclickspersecond;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.authlib.yggdrasil.request.TelemetryEventsRequest.Event;

import net.minecraft.client.Mouse;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.awt.event.InputEvent;

import org.lwjgl.glfw.GLFW;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;




public class DoubleClicksPerSecondClient implements ClientModInitializer {
	public static final String MOD_ID = "doubleclickspersecond";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static boolean shouldRenderOverlayBool = false;
	
	@Override
	public void onInitializeClient() {
		KeyBinding RenderCpsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("toggle CPS", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "DoubleClicksPerSecond"));
		
		
		HudRenderCallback.EVENT.register(new RenderOverlay()); // initializes render

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (RenderCpsKey.wasPressed()){
				if(shouldRenderOverlayBool){
					shouldRenderOverlayBool = false;
				}else{
					shouldRenderOverlayBool = true;
				}
			}
		});
	}
	
	
	public static boolean ShouldRenderOverlay(){
		return shouldRenderOverlayBool;
	}
}