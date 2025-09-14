package com.rinko1231.majospellenchantment.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.rinko1231.majospellenchantment.init.ModEnchantments.RED_LOTUS_ENCHANT;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {


    protected AbstractArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "shoot", at = @At("HEAD"))
    private void onShoot(double x, double y, double z, float velocity, float inaccuracy, CallbackInfo ci) {
        if (this.getOwner() instanceof Player player) {
            ItemStack bow = player.getMainHandItem();
            if (!(bow.getItem() instanceof BowItem)) bow = player.getOffhandItem();
            if (!(bow.getItem() instanceof BowItem)) return;
            int enchantmentLevel = bow.getEnchantmentLevel(RED_LOTUS_ENCHANT.get());
            if (enchantmentLevel != 0) {
                    this.getPersistentData().putInt("RED_LOTUS_ENCHANTED_ARROW", enchantmentLevel);
            }
        }
    }
}