package net.rapsac.doubleclickspersecond.mixin;

import net.rapsac.doubleclickspersecond.InputHandler;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class KeyClientMixin {
	@Inject(at = @At(value = "HEAD"), method = "doAttack")
    private void onAttack(CallbackInfoReturnable<Boolean> cir) // eventuell nicht callbackinfo sondern das andere
    {
        InputHandler.Clicking();
    }

    @Inject(at = @At(value = "HEAD"), method = "doItemUse")
    private void onItemUse(CallbackInfo ci)
    {
        InputHandler.Clicking();
    }
}