package com.InfinityRaider.AgriCraft.CleanRoom;

import com.InfinityRaider.AgriCraft.api.v3.ICrop;
import com.InfinityRaider.AgriCraft.tileentity.TileEntityCrop;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CropPlantCleamRoomShearable extends CropPlantCleanRoom {
    private final Item item;

    private final int meta;

    public CropPlantCleamRoomShearable(BlockCRModPlant plant, ItemStack shearingResult) {
        super(plant);
        this.item = shearingResult.getItem();
        this.meta = shearingResult.getItemDamage();
    }

    public boolean onHarvest(World world, int x, int y, int z, ICrop crop, EntityPlayer player) {
        if (player == null)
            return true;
        if (player.getCurrentEquippedItem() == null)
            return true;
        if (player.getCurrentEquippedItem().getItem() == null)
            return true;
        if (!(player.getCurrentEquippedItem().getItem() instanceof net.minecraft.item.ItemShears))
            return true;
        TileEntity tile = crop.getTileEntity();
        tile.getWorldObj().setBlockMetadataWithNotify(tile.xCoord, tile.yCoord, tile.zCoord, 2, 2);
        int amount = (int) Math.ceil((crop.getGain() + 0.0D) / 3.0D) / 2;
        if (amount > 0) {
            ItemStack drop = new ItemStack(this.item, amount, this.meta);
            if (world.getGameRules().getGameRuleBooleanValue("doTileDrops") && !world.restoringBlockSnapshots) {
                float f = 0.7F;
                double d0 = (world.rand.nextFloat() * f) + (1.0F - f) * 0.5D;
                double d1 = (world.rand.nextFloat() * f) + (1.0F - f) * 0.5D;
                double d2 = (world.rand.nextFloat() * f) + (1.0F - f) * 0.5D;
                EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, drop);
                entityitem.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(entityitem);
            }
        }
        player.getCurrentEquippedItem().damageItem(1, player);
        return false;
    }

    public boolean onHarvest(World world, int x, int y, int z, EntityPlayer player) {
        if (player == null)
            return true;
        if (player.getCurrentEquippedItem() == null)
            return true;
        if (player.getCurrentEquippedItem().getItem() == null)
            return true;
        if (!(player.getCurrentEquippedItem().getItem() instanceof net.minecraft.item.ItemShears))
            return true;
        TileEntityCrop crop = (TileEntityCrop) world.getTileEntity(x, y, z);
        crop.getWorldObj().setBlockMetadataWithNotify(crop.xCoord, crop.yCoord, crop.zCoord, 2, 2);
        int amount = (int) Math.ceil((crop.getGain() + 0.0D) / 3.0D) / 2;
        if (amount > 0) {
            ItemStack drop = new ItemStack(this.item, amount, this.meta);
            if (world.getGameRules().getGameRuleBooleanValue("doTileDrops") && !world.restoringBlockSnapshots) {
                float f = 0.7F;
                double d0 = (world.rand.nextFloat() * f) + (1.0F - f) * 0.5D;
                double d1 = (world.rand.nextFloat() * f) + (1.0F - f) * 0.5D;
                double d2 = (world.rand.nextFloat() * f) + (1.0F - f) * 0.5D;
                EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, drop);
                entityitem.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(entityitem);
            }
        }
        player.getCurrentEquippedItem().damageItem(1, player);
        return false;
    }
}
