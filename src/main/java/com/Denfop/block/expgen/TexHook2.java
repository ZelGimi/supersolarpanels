package com.Denfop.block.expgen;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

public class TexHook2 {


    public static IIcon[][] icons;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new TexHook2());

    }


    @SubscribeEvent
    public void textureHook2(TextureStitchEvent.Pre event) {

        if (event.map.getTextureType() == 0) {


        }


    }
}
