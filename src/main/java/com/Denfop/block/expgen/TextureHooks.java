package com.Denfop.block.expgen;

import com.Denfop.SSPItem;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;

@SideOnly(Side.CLIENT)
public class TextureHooks {

    @SubscribeEvent
    public void textureHook(TextureStitchEvent.Pre event) {
        if (event.map.getTextureType() == 0) {
            Icons.xpJuiceStill = event.map.registerIcon("supersolarpanel:xpliquid_still");
            Icons.xpJuiceFlowing = event.map.registerIcon("supersolarpanel:xpjuice_flowing");
            SSPItem.FluidXP.xpJuice.setIcons(Icons.xpJuiceStill, Icons.xpJuiceFlowing);


        }
    }

    public static class Icons {
        public static IIcon xpJuiceStill;
        public static IIcon xpJuiceFlowing;
    }


}
