package net.rapsac.doubleclickspersecond;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
//import net.minecraft.client.gui.DrawContext;
import net.rapsac.doubleclickspersecond.InputHandler;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;


//import net.minecraft.client.gui.screen.Screen;
import java.awt.Color;


public class RenderOverlay implements HudRenderCallback {

    //MinecraftClient client = MinecraftClient.getInstance();
    public static int xOffset = 0;
    public static int yOffset = 15;
    public static int cps = 0;

    //@Override
    //public void onHudRender(DrawContext context, float tickDelta){ // hier wird gerendert in 1.20.4
     //   render(context);
   // }
    
    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) { // hier wird in der 1.19.4 gerendert
        //DoubleClicksPerSecondClient.LOGGER.info("render");
        cps = InputHandler.Cps();
        if(DoubleClicksPerSecondClient.ShouldRenderOverlay()){
            render(matrixStack);
        }
        return;
        
    }
    

    public void render(MatrixStack matrixStack){//DrawContext context){
        MinecraftClient client = MinecraftClient.getInstance();
        if(client != null){
            //context.drawText(MinecraftClient.getInstance().textRenderer, Integer.toString(InputHandler.Cps()), XValue(), YValue(), color(), false);
            TextRenderer renderer = client.textRenderer;
            renderer.draw(matrixStack, Integer.toString(cps), XValue(client) , YValue(client), color());
        }
    }
    public float XValue(MinecraftClient client){
       // return (int) ((float) client.getWindow().getScaledWidth() / 2f) + xOffset;
        //DoubleClicksPerSecondClient.LOGGER.info(Float.toString(((float) MinecraftClient.getInstance().getWindow().getScaledWidth() / 2f) + xOffset));
        return ((float) client.getWindow().getScaledWidth() / 2f) + xOffset- (client.textRenderer.getWidth(Integer.toString(InputHandler.Cps()))/2f);
    }
    public float YValue(MinecraftClient client){
        //return (int) ((float) client.getWindow().getScaledHeight() / 2f) + yOffset;
        return ((float) client.getWindow().getScaledHeight() / 2f) + yOffset;
    }
    public int color(){
        if (InputHandler.WasLastDouble()){
            return (int) 0xff00ff00; //grün
        }else{
            return (int) 0x00000000; // schwarz oder rot 0xffff0000
        }
        
    }

    
}
