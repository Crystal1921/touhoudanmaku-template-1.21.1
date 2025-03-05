package com.crystal;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(TouhouDanmaku.MODID)
public class TouhouDanmaku {
    public static final String MODID = "touhou_danmaku";

    public TouhouDanmaku(IEventBus modEventBus, ModContainer modContainer) {
    }

    public static String prefix(String string) {
        return MODID + ":" + string;
    }

    public static ResourceLocation resourceLocation(String name) {
        return ResourceLocation.parse(prefix(name));
    }
}
