package com.Denfop.utils;

import net.minecraft.entity.player.EntityPlayer;

public class ExperienceUtils {

    public static int getPlayerXP(EntityPlayer player) {
        return (int) ((float) getExperienceForLevel(player.experienceLevel) + player.experience * (float) player.xpBarCap());
    }

    public static void addPlayerXP(EntityPlayer player, int amount) {
        int experience = getPlayerXP(player) + amount;
        player.experienceTotal = experience;
        player.experienceLevel = getLevelForExperience(experience);
        int expForLevel = getExperienceForLevel(player.experienceLevel);
        player.experience = (float) (experience - expForLevel) / (float) player.xpBarCap();
    }

    public static int getExperienceForLevel(int level) {
        return level == 0 ? 0 : (level > 0 && level < 16 ? level * 17 : (level > 15 && level < 31 ? (int) (1.5D * Math.pow(level, 2.0D) - 29.5D * (double) level + 360.0D) : (int) (3.5D * Math.pow(level, 2.0D) - 151.5D * (double) level + 2220.0D)));
    }

    public static int getXpToNextLevel(int level) {
        int levelXP = getLevelForExperience(level);
        int nextXP = getExperienceForLevel(level + 1);
        return nextXP - levelXP;
    }

    public static int getLevelForExperience(int experience) {
        int i;
        for (i = 0; getExperienceForLevel(i) <= experience; ++i) {
        }

        return i - 1;
    }
}
