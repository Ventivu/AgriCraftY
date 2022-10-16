package com.InfinityRaider.AgriCraft.gui;

import com.InfinityRaider.AgriCraft.handler.ConfigurationHandler;
import com.InfinityRaider.AgriCraft.reference.Reference;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;


@SideOnly(Side.CLIENT)
@SuppressWarnings("unchecked")
public class AgriCraftGuiConfig extends GuiConfig {

    public AgriCraftGuiConfig(GuiScreen guiScreen) {
        super(guiScreen, getConfigElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> configElements = new ArrayList<>();
        for (String string : ConfigurationHandler.config.getCategoryNames()) {
            ConfigCategory category = ConfigurationHandler.config.getCategory(string);
            String name = StatCollector.translateToLocal(category.getLanguagekey());
            configElements.add(new DummyConfigElement.DummyCategoryElement(name, category.getLanguagekey(), (new ConfigElement(category)).getChildElements()));
        }
        return configElements;
    }
}
