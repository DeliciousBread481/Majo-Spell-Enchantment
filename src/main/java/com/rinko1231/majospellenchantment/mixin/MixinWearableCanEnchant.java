package com.rinko1231.majospellenchantment.mixin;

import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.item.enchantment.EnchantmentCategory$12")
public abstract class MixinWearableCanEnchant {

    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    private void injectCurioSupport(Item item, CallbackInfoReturnable<Boolean> cir) {
        if (item instanceof CurioBaseItem) {
            cir.setReturnValue(true);
        }
    }
}
