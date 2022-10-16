package com.InfinityRaider.AgriCraft.CleanRoom;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public abstract class ACleanRoomStructure extends Block {
    protected ACleanRoomStructure() {
        super(Material.iron);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
    }
}
