package com.crystal.spell_card.mover;

import dev.xkmc.danmakuapi.content.entity.DanmakuHelper;
import dev.xkmc.danmakuapi.content.spell.mover.DanmakuMover;
import dev.xkmc.danmakuapi.content.spell.mover.MoverInfo;
import dev.xkmc.fastprojectileapi.entity.ProjectileMovement;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

@SerialClass
public class RoundMover extends DanmakuMover {
    @SerialField
    int roundSpeed;

    public RoundMover() {
        this(1);
    }

    public RoundMover(int roundSpeed) {
        this.roundSpeed = roundSpeed;
    }

    @Override
    public @NotNull ProjectileMovement move(MoverInfo info) {
        Vec3 vec3 = info.prevVel();
        var o0 = DanmakuHelper.getOrientation(vec3);
        ProjectileMovement projectileMovement = ProjectileMovement.of(vec3);
        return new ProjectileMovement(o0.rotateDegrees(roundSpeed), projectileMovement.rot());
    }
}
