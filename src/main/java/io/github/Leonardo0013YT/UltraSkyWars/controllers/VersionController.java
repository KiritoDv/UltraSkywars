package io.github.Leonardo0013YT.UltraSkyWars.controllers;

import io.github.Leonardo0013YT.UltraSkyWars.UltraSkyWars;
import io.github.Leonardo0013YT.UltraSkyWars.interfaces.NMS;
import io.github.Leonardo0013YT.UltraSkyWars.nms.*;
import io.github.Leonardo0013YT.UltraSkyWars.nms.nametags.Nametags;
import io.github.Leonardo0013YT.UltraSkyWars.superclass.NMSReflection;
import org.bukkit.Bukkit;

public class VersionController {

    private final UltraSkyWars plugin;
    private final NMSReflection reflection;
    private String version;
    private NMS nms;
    private boolean is1_13to17 = false;
    private boolean is1_9to17 = false;
    private boolean is1_12 = false;

    public VersionController(UltraSkyWars plugin) {
        this.plugin = plugin;
        setupVersion();
        if (version.equals("v1_17_R1") || version.equals("v1_18_R1")) {
            this.reflection = new NMSReflectionNew();
        } else {
            this.reflection = new NMSReflectionOld();
        }
    }

    private void setupVersion() {
        try {
            version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
            switch (version) {
                case "v1_8_R3":
                    nms = new NMS_v1_8_r3();
                    break;
                case "v1_9_R1":
                    plugin.sendLogMessage("§cYou have an outdated version §e1.9§c, please use version §a1.9.4§c.");
                    disable();
                    break;
                case "v1_9_R2":
                    nms = new NMS_v1_9_r2();
                    is1_9to17 = true;
                    break;
                case "v1_10_R1":
                    nms = new NMS_v1_10_r1();
                    is1_9to17 = true;
                    break;
                case "v1_11_R1":
                    nms = new NMS_v1_11_r1();
                    is1_9to17 = true;
                    break;
                case "v1_12_R1":
                    nms = new NMS_v1_12_r1();
                    is1_9to17 = true;
                    is1_12 = true;
                    break;
                case "v1_13_R1":
                    plugin.sendLogMessage("§cYou have an outdated version §e1.13.1§c, please use version §a1.13.2§c.");
                    disable();
                    break;
                case "v1_13_R2":
                    nms = new NMS_v1_13_r2();
                    is1_9to17 = true;
                    is1_13to17 = true;
                    break;
                case "v1_14_R1":
                    nms = new NMS_v1_14_r1();
                    is1_9to17 = true;
                    is1_13to17 = true;
                    break;
                case "v1_15_R1":
                    nms = new NMS_v1_15_r1();
                    is1_9to17 = true;
                    is1_13to17 = true;
                    break;
                case "v1_16_R1":
                    nms = new NMS_v1_16_r1();
                    is1_9to17 = true;
                    is1_13to17 = true;
                    break;
                case "v1_16_R2":
                    nms = new NMS_v1_16_r2();
                    is1_9to17 = true;
                    is1_13to17 = true;
                    break;
                case "v1_16_R3":
                    nms = new NMS_v1_16_r3();
                    is1_9to17 = true;
                    is1_13to17 = true;
                    break;
                case "v1_17_R1":
                    nms = new NMS_v1_17_r1();
                    is1_9to17 = true;
                    is1_13to17 = true;
                    break;
                case "v1_18_R1":
                    nms = new NMS_v1_18_r1();
                    is1_9to17 = true;
                    is1_13to17 = true;
                    break;
                default:
                    plugin.sendLogMessage("§cYou have an outdated version §e1.8§c, please use version §a1.8.8§c.");
                    disable();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public void disable() {
        Bukkit.getScheduler().cancelTasks(plugin);
        Bukkit.getPluginManager().disablePlugin(plugin);
    }

    public NMS getNMS() {
        return nms;
    }

    public Nametags getNameTag(String name, String display, String prefix) {
        return new Nametags(name, display, prefix);
    }

    public NMSReflection getReflection() {
        return reflection;
    }

    public String getVersion() {
        return version;
    }

    public boolean is1_12() {
        return is1_12;
    }

    public boolean is1_9to17() {
        return is1_9to17;
    }

    public boolean is1_13to17() {
        return is1_13to17;
    }
}