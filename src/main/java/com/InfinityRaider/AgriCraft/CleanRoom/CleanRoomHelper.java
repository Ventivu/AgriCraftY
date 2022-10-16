package com.InfinityRaider.AgriCraft.CleanRoom;

import net.minecraft.nbt.NBTTagCompound;

public class CleanRoomHelper {
    public static NBTTagCompound setSeedNBT(NBTTagCompound tag, int dimadapa) {
        tag.setInteger("DIMadaptability", dimadapa);
        return tag;
    }

    public static boolean checkValidateDim(CropPlantCleanRoom plant, int dimid) {
        int[] i = plant.getDIMrequire();
        for (int array : i) {
            if (dimid == array)
                return true;
        }
        return false;
    }
}
