package com.yyz.mixin;


import com.yyz.EnchantControl;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Unique
    Enchantment enchantment = (Enchantment) (Object) this;

    @Inject(method = "isAvailableForRandomSelection", at = @At("RETURN"), cancellable = true)
    private void injectIsAvailableForRandomSelection(CallbackInfoReturnable<Boolean> cir) {
        if(EnchantControl.enchant_control$isAvailableForRandomSelection(enchantment)) {
            cir.setReturnValue(!cir.getReturnValue());
        }
    }
    @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("RETURN"), cancellable = true)
    private void injectIsAvailableForEnchantedBookOffer(CallbackInfoReturnable<Boolean> cir) {
        if(EnchantControl.enchant_control$isAvailableForEnchantedBookOffer(enchantment)) {
            cir.setReturnValue(!cir.getReturnValue());
        }
    }

    @Inject(method = "isCursed", at = @At("RETURN"), cancellable = true)
    private void injectIsCursed(CallbackInfoReturnable<Boolean> cir) {
        if(EnchantControl.enchant_control$isCursed(enchantment)) {
            cir.setReturnValue(!cir.getReturnValue());
        }
    }
    @Inject(method = "isTreasure", at = @At("RETURN"), cancellable = true)
    private void injectIsTreasure(CallbackInfoReturnable<Boolean> cir) {
        if(EnchantControl.enchant_control$isTreasure(enchantment)) {
            cir.setReturnValue(!cir.getReturnValue());
        }
    }



}
