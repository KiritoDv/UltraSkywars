package io.github.Leonardo0013YT.UltraSkyWars.addons.leaderheads;

import io.github.Leonardo0013YT.UltraSkyWars.UltraSkyWars;
import io.github.Leonardo0013YT.UltraSkyWars.data.SWPlayer;
import me.robin.leaderheads.datacollectors.OnlineDataCollector;
import me.robin.leaderheads.objects.BoardType;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SkyWarsCoins extends OnlineDataCollector {

    private UltraSkyWars plugin;

    public SkyWarsCoins(UltraSkyWars plugin) {
        super("usw-g-coins", "UltraSkyWars", BoardType.DEFAULT, plugin.getLang().get(null, "leaderheads.skywarscoins.title"), "skywarscoins", Arrays.asList(plugin.getLang().get(null, "leaderheads.skywarscoins.lines.0"), plugin.getLang().get(null, "leaderheads.skywarscoins.lines.1"), plugin.getLang().get(null, "leaderheads.skywarscoins.lines.2"), plugin.getLang().get(null, "leaderheads.skywarscoins.lines.3")));
        this.plugin = plugin;
    }

    @Override
    public Double getScore(Player p) {
        SWPlayer sw = plugin.getDb().getSWPlayer(p);
        if (sw == null) {
            return 0.0;
        }
        return plugin.getAdm().getCoins(p);
    }
}