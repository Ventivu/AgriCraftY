package com.InfinityRaider.AgriCraft.CleanRoom;

import com.InfinityRaider.AgriCraft.blocks.BlockModPlant;
import com.InfinityRaider.AgriCraft.compatibility.applecore.AppleCoreHelper;
import com.InfinityRaider.AgriCraft.farming.CropPlantHandler;
import com.InfinityRaider.AgriCraft.handler.ConfigurationHandler;
import com.InfinityRaider.AgriCraft.utility.LogHelper;
import com.InfinityRaider.AgriCraft.utility.exception.MissingArgumentsException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class BlockCRModPlant extends BlockModPlant {
    final boolean canBonemeal;

    private final int envreq;

    private final int dimLimitValue;

    private final int percent;

    private int[] dims;

    public BlockCRModPlant(Object... arguments) throws MissingArgumentsException {
        super(arguments);
        this.tier = 1;
        this.growthRequirement.setBrightnessRange(8, 16);
        this.percent = (Integer) arguments[5];
        this.canBonemeal = (Boolean) arguments[6];
        this.envreq = (Integer) arguments[7];
        this.dimLimitValue = (Integer) arguments[8];
        if (arguments[9] != null)
            this.dims = (int[]) arguments[9];
        ItemStack fruit = null;
        ItemStack shearable = null;
        for (Object arg : arguments) {
            if (arg instanceof ItemStack)
                if (fruit == null) {
                    fruit = (ItemStack) arg;
                } else {
                    shearable = (ItemStack) arg;
                }
        }
        try {
            if (shearable == null) {
                CropPlantHandler.registerPlant(new CropPlantCleanRoom(this));
            } else {
                CropPlantHandler.registerPlant(new CropPlantCleamRoomShearable(this, shearable));
            }
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
    }

    public int getSpreadChance() {
        return this.percent;
    }

    public int getEnvrequest() {
        return this.envreq;
    }

    public int getDimLimitValue() {
        return this.dimLimitValue;
    }

    public int[] getAvalivableDims() {
        return this.dims;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(this.seed, 1, 0));
        if (metadata == 7)
            list.add(getRandomFruit(world.rand));
        return list;
    }

    protected Item func_149866_i() {
        return this.seed;
    }

    public void updateTick(World world, int x, int y, int z, Random rnd) {
        int meta = getPlantMetadata(world, x, y, z);
        if (meta < 7 && isFertile(world, x, y, z)) {
            double bonus = 0.2D;
            float global = ConfigurationHandler.growthMultiplier;
            int newMeta = (rnd.nextDouble() > this.percent * bonus * global / 100.0D) ? meta : (meta + 1);
            if (newMeta != meta) {
                world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
                AppleCoreHelper.announceGrowthTick(this, world, x, y, z);
            }
        }
    }
}
