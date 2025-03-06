package com.crystal.touhou_little_maid;

import com.crystal.spell_card.WanderingSoulButterfly;
import com.github.tartaricacid.touhoulittlemaid.entity.ai.brain.task.MaidCheckRateTask;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.github.tartaricacid.touhoulittlemaid.init.InitEntities;
import com.google.common.collect.ImmutableMap;
import dev.xkmc.danmakuapi.content.spell.spellcard.CardHolder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import org.jetbrains.annotations.NotNull;

public class DanmakuTask extends MaidCheckRateTask {
    float speed;
    int closeEnoughDist;
    WanderingSoulButterfly dream = new WanderingSoulButterfly();

    public DanmakuTask(float movementSpeed, int closeEnoughDist) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT,
                InitEntities.TARGET_POS.get(), MemoryStatus.VALUE_ABSENT));
        this.closeEnoughDist = closeEnoughDist;
        this.speed = movementSpeed;
        this.setMaxCheckRate(1);
    }

    @Override
    protected void start(@NotNull ServerLevel level, EntityMaid maid, long gameTimeIn) {
        if (maid.isMaidInSittingPose()) {
            dream.tick((CardHolder) maid);
        }
    }
}
