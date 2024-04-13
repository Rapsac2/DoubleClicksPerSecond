package net.rapsac.doubleclickspersecond;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.rapsac.doubleclickspersecond.InputHandler;
import net.minecraft.client.util.Window;
//import net.minecraft.client.gui.screen.Screen;
import java.awt.Color;

public class RenderOverlay implements HudRenderCallback{

    MinecraftClient client = MinecraftClient.getInstance();
    public static int xOffset = 0;
    public static int yOffset = 0;

    @Override
    public void onHudRender(DrawContext context, float tickDelta){ // hier wird gerendert
        render(context);
    }

    public void render(DrawContext context){
        if(MinecraftClient.getInstance() != null){
        context.drawText(MinecraftClient.getInstance().textRenderer, Integer.toString(InputHandler.Cps()), XValue(), YValue(), color(), false);
        }
    }
    public int XValue(){
        return (int) ((float) client.getWindow().getWidth() / 2f) + xOffset;
    }
    public int YValue(){
        return (int) ((float) client.getWindow().getHeight() / 2f) + yOffset;
    }
    public int color(){
        if (InputHandler.WasLastDouble()){
            return 0xff00ff00; //grün
        }else{
            return 0x00000000; // schwarz oder rot 0xffff0000
        }
        
    }
}
