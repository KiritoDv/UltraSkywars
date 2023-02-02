package io.github.Leonardo0013YT.UltraSkyWars.cosmetics.killeffects;

import io.github.Leonardo0013YT.UltraSkyWars.UltraSkyWars;
import io.github.Leonardo0013YT.UltraSkyWars.enums.CustomSound;
import io.github.Leonardo0013YT.UltraSkyWars.interfaces.KillEffect;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.scheduler.BukkitRunnable;

public class KillEffectTNT implements KillEffect, Cloneable {

    private static boolean loaded = false;
    private static int fuseTicks;

    @Override
    public void loadCustoms(UltraSkyWars plugin, String path) {
        if (!loaded) {
            fuseTicks = plugin.getKilleffect().getIntOrDefault(path + ".fuseTicks", 4);
            loaded = true;
        }
    }

    @Override
    public void start(Player p, Player death, Location loc) {
        if (p == null || !p.isOnline()) {
            return;
        }
        if (death == null || !death.isOnline()) {
            return;
        }
        TNTPrimed primed = loc.getWorld().spawn(loc, TNTPrimed.class);
        primed.setFuseTicks(fuseTicks);
        UltraSkyWars plugin = UltraSkyWars.get();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (plugin.getVc().is1_12() || plugin.getVc().is1_13to17()) {
                    loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 1);
                } else {
                    loc.getWorld().playEffect(loc, Effect.EXPLOSION_LARGE, 1);
                }
                CustomSound.KILLEFFECTS_TNT.reproduce(p);
                primed.remove();
            }
        }.runTaskLater(plugin, fuseTicks);
    }

    @Override
    public void stop() {
    }

    @Override
    public KillEffect clone() {
        return new KillEffectSquid();
    }

}
