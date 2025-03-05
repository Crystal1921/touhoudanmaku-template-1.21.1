package com.crystal.touhou_little_maid;

import com.github.tartaricacid.touhoulittlemaid.api.task.IMaidTask;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.github.tartaricacid.touhoulittlemaid.init.InitSounds;
import com.github.tartaricacid.touhoulittlemaid.util.SoundUtil;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.crystal.TouhouDanmaku.resourceLocation;

public class TouhouDanmakuTask implements IMaidTask {
    @Override
    public ResourceLocation getUid() {
        return resourceLocation("danmaku");
    }

    @Override
    public ItemStack getIcon() {
        return BuiltInRegistries.ITEM.get(ResourceLocation.parse("danmaku_api:red_circle_danmaku")).getDefaultInstance();
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound(EntityMaid entityMaid) {
        return SoundUtil.environmentSound(entityMaid, InitSounds.MAID_IDLE.get(), 0.5f);
    }

    @Override
    public List<Pair<Integer, BehaviorControl<? super EntityMaid>>> createBrainTasks(EntityMaid entityMaid) {
        return List.of();
    }

    @Override
    public List<Pair<Integer, BehaviorControl<? super EntityMaid>>> createRideBrainTasks(EntityMaid entityMaid) {
        Pair<Integer, BehaviorControl<? super EntityMaid>> maidMealTask = Pair.of(5, new DanmakuTask(0.6f, 2));
        return Lists.newArrayList(maidMealTask);
    }
}
