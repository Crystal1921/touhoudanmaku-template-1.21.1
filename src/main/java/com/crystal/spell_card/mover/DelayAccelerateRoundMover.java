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
public class DelayAccelerateRoundMover extends DanmakuMover {
    @SerialField
    double initSpeed;
    @SerialField
    int delay;
    @SerialField
    int accelerate;
    @SerialField
    int roundSpeed;

    public DelayAccelerateRoundMover(double initSpeed,int delay, int accelerate, int roundSpeed) {
        this.initSpeed = initSpeed;
        this.delay = delay;
        this.accelerate = accelerate;
        this.roundSpeed = roundSpeed;
    }

    public DelayAccelerateRoundMover() {
        this(0,0, 0, 0);
    }

    @Override
    public @NotNull ProjectileMovement move(MoverInfo info) {
        Vec3 preVec = info.prevVel().normalize().scale(initSpeed);
        double tick = info.tick();
        var o0 = DanmakuHelper.getOrientation(preVec);
        ProjectileMovement projectileMovement = ProjectileMovement.of(preVec);
        if (tick > delay) {
            double v = (tick - this.delay) * accelerate / 100 + 1;
            return new ProjectileMovement(o0.rotateDegrees(roundSpeed).scale(v), projectileMovement.rot());
        } else {
            return new ProjectileMovement(o0.rotateDegrees(roundSpeed), projectileMovement.rot());
        }
    }
}
