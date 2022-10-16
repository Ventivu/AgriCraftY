package com.InfinityRaider.AgriCraft.CleanRoom;

import com.InfinityRaider.AgriCraft.api.CleanRoom.ICRRequired;
import com.InfinityRaider.AgriCraft.api.v1.IGrowthRequirement;
import com.InfinityRaider.AgriCraft.api.v3.ICrop;
import com.InfinityRaider.AgriCraft.farming.cropplant.CropPlant;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class CropPlantCleanRoom extends CropPlant implements ICRRequired {
    BlockCRModPlant plant;

    public CropPlantCleanRoom(BlockCRModPlant plant) {
        this.plant = plant;
        setGrowthRequirement(plant.getGrowthRequirement());
        setSpreadChance(plant.getSpreadChance());
    }

    public int tier() {
        return 1;
    }

    public ItemStack getSeed() {
        return this.plant.getSeedStack(1);
    }

    public Block getBlock() {
        return this.plant.getBlock();
    }

    public ArrayList<ItemStack> getAllFruits() {
        return this.plant.getAllFruits();
    }

    public ItemStack getRandomFruit(Random rand) {
        return this.plant.getRandomFruit(rand);
    }

    public ArrayList<ItemStack> getFruitsOnHarvest(int gain, Random rand) {
        int amount = (int) Math.ceil((gain + 0.0D) / 3.0D);
        return this.plant.getFruit(amount, rand);
    }

    public boolean canBonemeal() {
        return this.plant.canBonemeal;
    }

    protected IGrowthRequirement initGrowthRequirement() {
        return null;
    }

    public boolean onAllowedGrowthTick(World world, int x, int y, int z, int oldGrowthStage, ICrop crop) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public float getHeight(int meta) {
        return 0.8125F;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getPlantIcon(int growthStage) {
        return this.plant.getIcon(growthStage);
    }

    @SideOnly(Side.CLIENT)
    public boolean renderAsFlower() {
        return this.plant.renderAsFlower();
    }

    @SideOnly(Side.CLIENT)
    public String getInformation() {
        return this.plant.getSeed().getInformation();
    }

    public int getEnvironment() {
        return this.plant.getEnvrequest();
    }

    public int getDIMadaptability() {
        return this.plant.getDimLimitValue();
    }

    public int[] getDIMrequire() {
        return this.plant.getAvalivableDims();
    }

    public int getProbability() {
        return this.plant.getSpreadChance();
    }
}
