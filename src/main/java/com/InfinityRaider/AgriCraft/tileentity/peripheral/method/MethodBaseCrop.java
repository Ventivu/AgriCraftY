package com.InfinityRaider.AgriCraft.tileentity.peripheral.method;

import com.InfinityRaider.AgriCraft.tileentity.peripheral.TileEntityPeripheral;

import java.util.ArrayList;

public abstract class MethodBaseCrop extends MethodBase {
    public MethodBaseCrop(String name) {
        super(name);
    }

    @Override
    protected boolean appliesToCrop() {
        return true;
    }

    @Override
    protected boolean appliesToPeripheral() {
        return false;
    }

    @Override
    protected Object[] onMethodCalled(TileEntityPeripheral peripheral) {
        return new Object[0];
    }

    @Override
    protected boolean requiresJournal() {
        return false;
    }

    @Override
    protected ArrayList<MethodParameter> getParameters() {
        ArrayList<MethodParameter> pars = new ArrayList<>();
        pars.add(MethodParameter.DIRECTION);
        return pars;
    }
}
