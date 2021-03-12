package com.Denfop.events;

import com.Denfop.handler.EntityStreak;
import com.Denfop.utils.StreakLocation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EventDarkQuantumSuitEffect {
    public static final int streakMaxTime = 20;

    private static final Map<String, ArrayList<StreakLocation>> playerLoc = new HashMap<String, ArrayList<StreakLocation>>();

    private static final HashMap<String, EntityStreak> streaks = new HashMap<String, EntityStreak>();

    private WorldClient worldInstance;

    private float renderTick;

    public static ArrayList<StreakLocation> getPlayerStreakLocationInfo(EntityPlayer player) {
        ArrayList<StreakLocation> loc = playerLoc.get(player.getCommandSenderName());
        if (loc == null) {
            loc = new ArrayList<StreakLocation>();
            playerLoc.put(player.getCommandSenderName(), loc);
        }
        if (loc.size() < 20) {
            for (int i = 0; i < 20 - loc.size(); i++)
                loc.add(0, new StreakLocation(player));
        } else if (loc.size() > 20) {
            loc.remove(0);
        }
        return loc;
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            this.renderTick = event.renderTickTime;

            Iterator<Map.Entry<String, EntityStreak>> iterator = streaks.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, EntityStreak> e = iterator.next();
                EntityStreak streak = e.getValue();
                if (streak.parent != null) {
                    if (streak.parent.isDead) {
                        streak.setDead();
                        iterator.remove();
                        continue;
                    }
                    updatePos(streak, streak.parent);
                }
            }
        }
    }

    @SubscribeEvent
    public void worldTick(TickEvent.ClientTickEvent event) {
        WorldClient world = (Minecraft.getMinecraft()).theWorld;
        if (event.phase == TickEvent.Phase.END && world != null) {
            if (this.worldInstance != world) {
                this.worldInstance = world;
                streaks.clear();
            }
            Iterator<Map.Entry<String, EntityStreak>> ite = streaks.entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry<String, EntityStreak> e = ite.next();
                EntityStreak streak = e.getValue();
                if (streak.worldObj.provider.dimensionId != world.provider.dimensionId || world.getWorldTime() - streak.lastUpdate > 10L) {
                    streak.setDead();
                    ite.remove();
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;
            Minecraft mc = Minecraft.getMinecraft();
            if (player.worldObj.getPlayerEntityByName(player.getCommandSenderName()) != player)
                return;
            WorldClient world = mc.theWorld;
            EntityStreak hat = streaks.get(player.getCommandSenderName());
            if (hat == null || hat.isDead) {
                if (player.getCommandSenderName().equalsIgnoreCase(mc.thePlayer.getCommandSenderName()))
                    for (Map.Entry<String, EntityStreak> e : streaks.entrySet())
                        e.getValue().setDead();
                hat = new EntityStreak(world, player);
                streaks.put(player.getCommandSenderName(), hat);
                world.spawnEntityInWorld(hat);
            }
            ArrayList<StreakLocation> loc = getPlayerStreakLocationInfo(player);
            StreakLocation oldest = loc.get(0);
            loc.remove(0);
            loc.add(oldest);
            oldest.update(player);
            StreakLocation newest = loc.get(loc.size() - 2);
            double distX = newest.posX - oldest.posX;
            double distZ = newest.posZ - oldest.posZ;
            for (newest.startU += Math.sqrt(distX * distX + distZ * distZ) / newest.height; oldest.startU > 1.0D; oldest.startU--)
                ;
        }
    }

    private void updatePos(EntityStreak streak, EntityLivingBase parent) {
        streak.lastTickPosX = streak.parent.lastTickPosX;
        streak.lastTickPosY = streak.parent.lastTickPosY;
        streak.lastTickPosZ = streak.parent.lastTickPosZ;
        streak.prevPosX = streak.parent.prevPosX;
        streak.prevPosY = streak.parent.prevPosY;
        streak.prevPosZ = streak.parent.prevPosZ;
        streak.posX = streak.parent.posX;
        streak.posY = streak.parent.posY;
        streak.posZ = streak.parent.posZ;
    }
}
