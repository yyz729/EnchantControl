package com.yyz.mixin;


import com.yyz.EnchantControl;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Unique
    Enchantment enchantment = (Enchantment) (Object) this;

    @Inject(method = "isAvailableForRandomSelection", at = @At("RETURN"), cancellable = true)
    private void injectIsAvailableForRandomSelection(CallbackInfoReturnable<Boolean> cir) {
        if(isDisabled()) {
            cir.setReturnValue(false);
        }
    }
    @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("RETURN"), cancellable = true)
    private void injectIsAvailableForEnchantedBookOffer(CallbackInfoReturnable<Boolean> cir) {
        if(isDisabled()) {
            cir.setReturnValue(false);
        }
    }
    @Unique
    private boolean isDisabled() {
        Identifier id = Registries.ENCHANTMENT.getId(enchantment);
        return convertStringSetToIdentifierSet(EnchantControl.getConfig().DISABLED_ENCHANTMENTS).contains(id);
    }

    @Unique
    private static Set<Identifier> convertStringSetToIdentifierSet(Set<String> stringSet) {
        return stringSet.stream()
                .map(s -> {
                    try {
                        return new Identifier(s);
                    } catch (InvalidIdentifierException e) {
                        System.err.println("Invalid Identifier: " + s);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

}
