package io.github.Leonardo0013YT.UltraSkyWars.addons;

import com.grinderwolf.swm.api.SlimePlugin;
import com.grinderwolf.swm.api.exceptions.WorldAlreadyExistsException;
import com.grinderwolf.swm.api.loaders.SlimeLoader;
import com.grinderwolf.swm.api.world.properties.SlimeProperties;
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap;
import io.github.Leonardo0013YT.UltraSkyWars.UltraSkyWars;
import org.bukkit.Bukkit;

import java.io.IOException;

public class SlimeWorldManagerAddon {

    private UltraSkyWars plugin;
    private SlimePlugin slime;
    private SlimeLoader loader;

    public SlimeWorldManagerAddon(UltraSkyWars plugin) {
        this.plugin = plugin;
        this.slime = (SlimePlugin) Bukkit.getPluginManager().getPlugin("SlimeWorldManager");
        this.loader = slime.getLoader(plugin.getCm().getSlimeworldmanagerLoader());
    }

    public void createWorld(String name) {
        SlimePropertyMap slimeProperties = new SlimePropertyMap();
        slimeProperties.setInt(SlimeProperties.SPAWN_X, 0);
        slimeProperties.setInt(SlimeProperties.SPAWN_Y, 75);
        slimeProperties.setInt(SlimeProperties.SPAWN_Z, 0);
        slimeProperties.setBoolean(SlimeProperties.PVP, true);
        slimeProperties.setBoolean(SlimeProperties.ALLOW_MONSTERS, false);
        slimeProperties.setBoolean(SlimeProperties.ALLOW_ANIMALS, false);
        slimeProperties.setString(SlimeProperties.DIFFICULTY, "normal");
        slimeProperties.setString(SlimeProperties.ENVIRONMENT, "normal");
        try {
            slime.createEmptyWorld(loader, name, false, slimeProperties);
        } catch (WorldAlreadyExistsException | IOException ignored) {
        }
    }

}