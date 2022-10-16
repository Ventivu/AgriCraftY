package com.InfinityRaider.AgriCraft.CleanRoom;

import com.InfinityRaider.AgriCraft.api.v1.BlockWithMeta;
import com.InfinityRaider.AgriCraft.api.v1.RenderMethod;
import com.InfinityRaider.AgriCraft.api.v1.RequirementType;
import com.InfinityRaider.AgriCraft.handler.ConfigurationHandler;
import com.InfinityRaider.AgriCraft.items.ItemModSeed;
import com.InfinityRaider.AgriCraft.utility.IOHelper;
import com.InfinityRaider.AgriCraft.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.ItemStack;

public class CustomCleanRoomCrops {
    public static BlockCRModPlant[] customCrops;

    public static ItemModSeed[] customSeeds;

    public static void init() {
        if (ConfigurationHandler.customCrops && ConfigurationHandler.enableBetaSys) {
            String[] cropsRawData = IOHelper.getLinesArrayFromData(ConfigurationHandler.readCleanRoomCrops());
            customCrops = new BlockCRModPlant[cropsRawData.length];
            customSeeds = new ItemModSeed[cropsRawData.length];
            for (int i = 0; i < cropsRawData.length; i++) {
                String[] cropData = IOHelper.getData(cropsRawData[i]);
                boolean success = (cropData.length == 11 || cropData.length == 12);
                String errorMsg = "";
                LogHelper.debug((new StringBuffer()).append(cropsRawData[i]));
                if (success) {
                    ItemStack fruitStack = IOHelper.getStack(cropData[1], false);
                    errorMsg = "";
                    success = ((fruitStack != null && fruitStack.getItem() != null) || cropData[1].equals("null"));
                    if (success) {
                        String name = cropData[0];
                        BlockWithMeta soil = IOHelper.getBlock(cropData[2]);
                        BlockWithMeta base = IOHelper.getBlock(cropData[3]);
                        int percent = Integer.parseInt(cropData[4]);
                        boolean canBonemeal = Boolean.parseBoolean(cropData[5]);
                        int envreq = Integer.parseInt(cropData[6]);
                        int dimadaptability = Integer.parseInt(cropData[7]);
                        int[] avalivableDim = null;
                        if (!cropData[8].equals("null")) if (cropData[8].startsWith("[")) {
                            if (cropData[8].endsWith("]")) {
                                String[] ints = cropData[8].substring(1, cropData[8].length() - 1).split(",");
                                avalivableDim = new int[ints.length];
                                for (int k = 0; k < ints.length; k++)
                                    avalivableDim[k] = Integer.parseInt(ints[k]);
                            }
                        } else {
                            avalivableDim = new int[1];
                            avalivableDim[0] = Integer.parseInt(cropData[8]);
                        }
                        RenderMethod renderType = RenderMethod.getRenderMethod(Integer.parseInt(cropData[9]));
                        ItemStack shearable = (cropData.length > 11) ? IOHelper.getStack(cropData[11], false) : null;
                        shearable = (shearable != null && shearable.getItem() != null) ? shearable : null;
                        String info = cropData[10];
                        try {
                            customCrops[i] = new BlockCRModPlant(name, fruitStack, soil, RequirementType.BELOW, base, percent, canBonemeal, envreq, dimadaptability, avalivableDim, false, renderType, shearable);
                        } catch (Exception e) {
                            if (ConfigurationHandler.debug) LogHelper.printStackTrace(e);
                            break;
                        }
                        customSeeds[i] = customCrops[i].getSeed();
                        LanguageRegistry.addName(customCrops[i], Character.toUpperCase(name.charAt(0)) + name.substring(1));
                        LanguageRegistry.addName(customSeeds[i], Character.toUpperCase(name.charAt(0)) + name.substring(1) + " Seeds");
                        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
                            customSeeds[i].setInformation(info);
                    }
                }
                if (!success)
                    LogHelper.info((new StringBuffer("Error when adding custom crop: ")).append(errorMsg).append(" (line: ").append(cropsRawData[i]).append(")"));
            }
        }
    }
}
