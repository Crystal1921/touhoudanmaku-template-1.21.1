package com.crystal.mixin;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import dev.xkmc.danmakuapi.api.DanmakuBullet;
import dev.xkmc.danmakuapi.api.DanmakuLaser;
import dev.xkmc.danmakuapi.content.entity.ItemBulletEntity;
import dev.xkmc.danmakuapi.content.entity.ItemLaserEntity;
import dev.xkmc.danmakuapi.content.spell.spellcard.CardHolder;
import dev.xkmc.danmakuapi.init.registrate.DanmakuEntities;
import dev.xkmc.fastprojectileapi.entity.SimplifiedProjectile;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(EntityMaid.class)
public abstract class MaidDanmakuMixin implements CardHolder {

    @Shadow @javax.annotation.Nullable public abstract LivingEntity getTarget();

    @Override
    public Vec3 center() {
        return ((EntityMaid)(Object)(this)).position();
    }

    @Override
    public Vec3 forward() {
        Vec3 target = target();
        if (target != null) {
            return target.subtract(center()).normalize();
        } else return Vec3.ZERO;
    }

    @Override
    public @Nullable Vec3 target() {
        LivingEntity owner = ((EntityMaid) (Object) (this)).getOwner();
        if (owner != null) {return owner.position();}
        else {return null;}
    }

    @Override
    public RandomSource random() {
        return ((EntityMaid)(Object)(this)).getRandom();
    }

    @Override
    public ItemBulletEntity prepareDanmaku(int life, Vec3 vec, DanmakuBullet type, DyeColor color) {
        ItemBulletEntity danmaku = new ItemBulletEntity(DanmakuEntities.ITEM_DANMAKU.get(), (EntityMaid)(Object)(this), ((EntityMaid)(Object)(this)).level());
        danmaku.setItem(type.get(color).asStack());
        danmaku.setup((float)type.damage(), life, true, true, vec);
        danmaku.setPos(this.center());
        return danmaku;
    }

    @Override
    public ItemLaserEntity prepareLaser(int life, Vec3 pos, Vec3 vec, int len, DanmakuLaser type, DyeColor color) {
        ItemLaserEntity danmaku = new ItemLaserEntity(DanmakuEntities.ITEM_LASER.get(), (EntityMaid)(Object)(this), ((EntityMaid)(Object)(this)).level());
        danmaku.setItem(type.get(color).asStack());
        danmaku.setup((float)type.damage(), life, (float)len, true, vec);
        danmaku.setPos(pos);
        return danmaku;
    }

    @Override
    public void shoot(SimplifiedProjectile simplifiedProjectile) {
        ((EntityMaid)(Object)(this)).level().addFreshEntity(simplifiedProjectile);
    }

    @Override
    public LivingEntity self() {
        return (EntityMaid)(Object)(this);
    }

    @Override
    public @Nullable Vec3 targetVelocity() {
        return null;
    }
}
