package com.InfinityRaider.AgriCraft.farming;

import com.InfinityRaider.AgriCraft.api.v2.ISeedStats;
import com.InfinityRaider.AgriCraft.api.v2.ITrowel;
import com.InfinityRaider.AgriCraft.handler.ConfigurationHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PlantStats implements ISeedStats {
    private static final short MAX = (short) ConfigurationHandler.cropStatCap;

    private static final short MIN = 1;

    private short growth;

    private short gain;

    private short strength;

    private int DIMadaptability;

    private boolean analyzed;

    public PlantStats() {
        this(1, 1, 1);
    }

    public PlantStats(int growth, int gain, int strength, int dimAdaptAbility) {
        this(growth, gain, strength, false);
        this.DIMadaptability = moveIntoBounds(dimAdaptAbility);
    }

    public PlantStats(int growth, int gain, int strength) {
        this(growth, gain, strength, false);
    }

    public PlantStats(int growth, int gain, int strength, boolean analyzed) {
        setStats(growth, gain, strength);
        this.analyzed = analyzed;
    }

    public static PlantStats getStatsFromStack(ItemStack stack) {
        if (stack == null || stack.getItem() == null)
            return null;
        if (stack.getItem() instanceof ITrowel)
            ((ITrowel) stack.getItem()).getStats(stack);
        return readFromNBT(stack.getTagCompound());
    }

    public static PlantStats readFromNBT(NBTTagCompound tag) {
        if (tag != null && tag.hasKey("growth") && tag.hasKey("gain") && tag.hasKey("strength")) {
            PlantStats stats = new PlantStats();
            stats.setGrowth(tag.getShort("growth"));
            stats.setGain(tag.getShort("gain"));
            stats.setStrength(tag.getShort("strength"));
            stats.analyzed = (tag.hasKey("analyzed") && tag.getBoolean("analyzed"));
            if (ConfigurationHandler.enableBetaSys && tag.hasKey("DIMadaptability"))
                stats.setDIMadaptability(tag.getInteger("DIMadaptability"));
            return stats;
        }
        return null;
    }

    public void setStats(int growth, int gain, int strength) {
        setGrowth(growth);
        setGain(gain);
        setStrength(strength);
    }

    public int getDIMadaptability() {
        return this.DIMadaptability;
    }

    public void setDIMadaptability(int DIMadaptability) {
        this.DIMadaptability = moveIntoBounds(DIMadaptability);
    }

    public short getGrowth() {
        return this.growth;
    }

    public void setGrowth(int growth) {
        this.growth = moveIntoBounds(growth);
    }

    public short getGain() {
        return this.gain;
    }

    public void setGain(int gain) {
        this.gain = moveIntoBounds(gain);
    }

    public short getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = moveIntoBounds(strength);
    }

    public int getMAXDIMadaptability() {
        return MAX;
    }

    public short getMaxGrowth() {
        return MAX;
    }

    public short getMaxGain() {
        return MAX;
    }

    public short getMaxStrength() {
        return MAX;
    }

    private short moveIntoBounds(int stat) {
        int lowerLimit = Math.max(1, stat);
        return (short) Math.min(MAX, lowerLimit);
    }

    public PlantStats copy() {
        return new PlantStats(getGrowth(), getGain(), getStrength(), this.analyzed);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setShort("growth", this.growth);
        tag.setShort("gain", this.gain);
        tag.setShort("strength", this.strength);
        tag.setBoolean("analyzed", this.analyzed);
        if (ConfigurationHandler.enableBetaSys)
            tag.setInteger("DIMadaptability", this.DIMadaptability);
        return tag;
    }

    public boolean isAnalyzed() {
        return this.analyzed;
    }

    public void setAnalyzed(boolean value) {
        this.analyzed = value;
    }
}
