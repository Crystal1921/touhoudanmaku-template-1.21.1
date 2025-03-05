package com.crystal.spell_card;

import com.crystal.spell_card.mover.DelayAccelerateRoundMover;
import dev.xkmc.danmakuapi.content.entity.DanmakuHelper;
import dev.xkmc.danmakuapi.content.spell.spellcard.ActualSpellCard;
import dev.xkmc.danmakuapi.content.spell.spellcard.CardHolder;
import dev.xkmc.danmakuapi.init.registrate.DanmakuItems;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.Vec3;

@SerialClass
public class WanderingSoulButterfly extends ActualSpellCard {
    private final int CARD_TIME = 56;
    private final int DANMAKU_LIFE = 100;

    @Override
    public void tick(CardHolder holder) {
        super.tick(holder);
        Vec3 target = holder.target();
        if (target != null) {
            double dist = holder.center().distanceTo(target);
            if (tick % CARD_TIME == 0) {
                delayAccelerateRoundDanmaku(holder, dist, 0, DanmakuItems.Bullet.BUBBLE, DyeColor.RED, 0.5, 20, 0, 0);
                delayAccelerateRoundDanmaku(holder, dist, 0, DanmakuItems.Bullet.BUBBLE, DyeColor.RED, 0.5, 20, 1, 0);
                delayAccelerateRoundDanmaku(holder, dist, 0, DanmakuItems.Bullet.BUBBLE, DyeColor.RED, 0.5, 20, 2, 0);
            }
            if (tick % CARD_TIME == 20) {
                releaseBlueButterfly(holder, dist);
            }
            if (tick % CARD_TIME == 22) {
                releasePinkButterfly(holder, dist);
            }
            if (tick % CARD_TIME == 24) {
                releaseBlueButterfly(holder, dist);
            }
            if (tick % CARD_TIME == 26) {
                releasePinkButterfly(holder, dist);
            }
            if (tick % CARD_TIME == 28) {
                releaseBlueButterfly(holder, dist);
            }
            if (tick % CARD_TIME == 30) {
                releasePinkButterfly(holder, dist);
            }

            if (tick % CARD_TIME == 40) {
                releaseRedButterFly(holder, dist,1);
            }
            if (tick % CARD_TIME == 42) {
                releaseRedButterFly(holder, dist,1);
            }
            if (tick % CARD_TIME == 44) {
                releaseRedButterFly(holder, dist,-1);
            }
            if (tick % CARD_TIME == 46) {
                releaseRedButterFly(holder, dist,-1);
            }
            if (tick % CARD_TIME == 48) {
                releaseRedButterFly(holder, dist,1);
            }
            if (tick % CARD_TIME == 50) {
                releaseRedButterFly(holder, dist,1);
            }
            if (tick % CARD_TIME == 52) {
                releaseRedButterFly(holder, dist,-1);
            }
            if (tick % CARD_TIME == 54) {
                releaseRedButterFly(holder, dist,-1);
            }
        }
    }

    private void releaseRedButterFly(CardHolder holder, double dist, int roundSpeed) {
        delayAccelerateRoundDanmaku(holder, dist,0, DanmakuItems.Bullet.BUTTERFLY, DyeColor.RED, 0.6, 15, 0, roundSpeed);
        delayAccelerateRoundDanmaku(holder, dist,0, DanmakuItems.Bullet.BUTTERFLY, DyeColor.RED, 0.6, 15, 1, roundSpeed);
        delayAccelerateRoundDanmaku(holder, dist,0, DanmakuItems.Bullet.BUTTERFLY, DyeColor.RED, 0.6, 15, 2, roundSpeed);
    }

    private void releasePinkButterfly(CardHolder holder, double dist) {
        delayAccelerateRoundDanmaku(holder, dist, 0, DanmakuItems.Bullet.BUTTERFLY, DyeColor.PINK, 0.5, 20, 0, 0);
        delayAccelerateRoundDanmaku(holder, dist, 0, DanmakuItems.Bullet.BUTTERFLY, DyeColor.PINK, 0.5, 20, 1, 0);
        delayAccelerateRoundDanmaku(holder, dist, 0, DanmakuItems.Bullet.BUTTERFLY, DyeColor.PINK, 0.5, 20, 2, 0);
    }

    private void releaseBlueButterfly(CardHolder holder, double dist) {
        delayAccelerateRoundDanmaku(holder, dist, 7.5, DanmakuItems.Bullet.BUTTERFLY, DyeColor.BLUE, 0.5, 20, 0, 0);
        delayAccelerateRoundDanmaku(holder, dist, 7.5, DanmakuItems.Bullet.BUTTERFLY, DyeColor.BLUE, 0.5, 20, 1, 0);
        delayAccelerateRoundDanmaku(holder, dist, 7.5, DanmakuItems.Bullet.BUTTERFLY, DyeColor.BLUE, 0.5, 20, 2, 0);
    }

    private void delayAccelerateRoundDanmaku(CardHolder holder, double dist, double deltaAngle, DanmakuItems.Bullet bullet, DyeColor dyeColor, double initSpeed, int delay, int accelerate, int roundSpeed) {
        double speed = Math.max(0.6, dist / 40);
        int n = 24;
        var o0 = DanmakuHelper.getOrientation(new Vec3(1, 0, 0));
        for (int i = 0; i < n; i++) {
            var startVec = o0.rotateDegrees(360d / n * i + deltaAngle).scale(speed);

            var e = holder.prepareDanmaku(DANMAKU_LIFE, startVec, bullet, dyeColor);
            e.mover = new DelayAccelerateRoundMover(initSpeed, delay, accelerate, roundSpeed);
            holder.shoot(e);
        }
    }
}
