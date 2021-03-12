package com.Denfop.utils;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

public final class ModUtils {
    public static void sendContainerInt(Container container, ICrafting player, int firstDataId, int secondDataId, int value) {
        player.sendProgressBarUpdate(container, firstDataId, value & 65535);
        player.sendProgressBarUpdate(container, secondDataId, value >>> 16);
    }

    public static int recieveContainerInt(int firstDataId, int secondDataId, int dataId, int value, int fieldValue) {
        if (dataId == firstDataId)
            fieldValue = fieldValue & -65536 | value;
        else if (dataId == secondDataId)
            fieldValue = fieldValue & 65535 | value << 16;
        return fieldValue;
    }
}
