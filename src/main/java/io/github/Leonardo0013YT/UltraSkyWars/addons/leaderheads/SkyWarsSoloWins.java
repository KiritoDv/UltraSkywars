package io.github.Leonardo0013YT.UltraSkyWars.addons.leaderheads;

import io.github.Leonardo0013YT.UltraSkyWars.UltraSkyWars;
import io.github.Leonardo0013YT.UltraSkyWars.data.SWPlayer;
import io.github.Leonardo0013YT.UltraSkyWars.enums.StatType;
import me.robin.leaderheads.datacollectors.OnlineDataCollector;
import me.robin.leaderheads.objects.BoardType;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SkyWarsSoloWins extends OnlineDataCollector {

    private UltraSkyWars plugin;

    public SkyWarsSoloWins(UltraSkyWars plugin) {
        super("usw-s-wins", "UltraSkyWars", BoardType.DEFAULT, plugin.getLang().get(null, "leaderheads.skywarsSwins.title"), "skywarssolowins", Arrays.asList(plugin.getLang().get(null, "leaderheads.skywarsSwins.lines.0"), plugin.getLang().get(null, "leaderheads.skywarsSwins.lines.1"), plugin.getLang().get(null, "leaderheads.skywarsSwins.lines.2"), plugin.getLang().get(null, "leaderheads.skywarsSwins.lines.3")));
        this.plugin = plugin;
    }

    @Override
    public Double getScore(Player p) {
        SWPlayer sw = plugin.getDb().getSWPlayer(p);
        if (sw == null) {
            return 0.0;
        }
        return (double) sw.getStat(StatType.WINS, "SOLO");
    }

}