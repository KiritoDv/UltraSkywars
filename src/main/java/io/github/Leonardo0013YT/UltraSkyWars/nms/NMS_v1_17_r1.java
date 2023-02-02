package io.github.Leonardo0013YT.UltraSkyWars.nms;

import com.mojang.datafixers.util.Pair;
import io.github.Leonardo0013YT.UltraSkyWars.interfaces.NMS;
import net.minecraft.network.protocol.game.PacketPlayOutAttachEntity;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectList;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.entity.ambient.EntityBat;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import net.minecraft.world.entity.monster.EntityGiantZombie;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class NMS_v1_17_r1 implements NMS {

    @Override
    public Vehicle spawnHorse(Location loc, Player p) {
        SkeletonHorse horse = loc.getWorld().spawn(loc, SkeletonHorse.class);
        AbstractHorseInventory inv = (AbstractHorseInventory) horse.getInventory();
        inv.setSaddle(new ItemStack(Material.SADDLE));
        horse.setOwner(p);
        horse.setDomestication(horse.getMaxDomestication());
        horse.setAdult();
        return horse;
    }

    @Override
    public Collection<Integer> spawn(Player p, Location loc, ItemStack head) {
        EntityGiantZombie ev = new EntityGiantZombie(EntityTypes.G, ((CraftWorld) loc.getWorld()).getHandle());
        EntityArmorStand ar = new EntityArmorStand(EntityTypes.c, ((CraftWorld) loc.getWorld()).getHandle());
        EntityBat bat = new EntityBat(EntityTypes.f, ((CraftWorld) loc.getWorld()).getHandle());
        Location l = loc.clone().add(-2, 9, 3.5);
        ar.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
        ev.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
        bat.setLocation(l.getX(), l.getY(), l.getZ(), 0, 0);
        ar.setInvisible(true);
        ev.setInvisible(true);
        ev.addEffect(new MobEffect(MobEffectList.fromId(14), 1000, 1000));
        bat.setInvisible(true);
        List<Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>> equipmentList = new ArrayList<>();
        PacketPlayOutEntityEquipment equipment = new PacketPlayOutEntityEquipment(ev.getId(), equipmentList);
        PacketPlayOutSpawnEntityLiving ent = new PacketPlayOutSpawnEntityLiving(ev);
        PacketPlayOutSpawnEntityLiving ent2 = new PacketPlayOutSpawnEntityLiving(ar);
        PacketPlayOutSpawnEntityLiving ent3 = new PacketPlayOutSpawnEntityLiving(bat);
        PacketPlayOutAttachEntity attach = new PacketPlayOutAttachEntity(bat, ar);
        ServerPlayerConnection pc = ((CraftPlayer) p).getHandle().b;
        pc.sendPacket(ent);
        pc.sendPacket(ent2);
        pc.sendPacket(ent3);
        pc.sendPacket(attach);
        pc.sendPacket(equipment);
        return Arrays.asList(ev.getId(), ar.getId(), bat.getId());
    }

    @Override
    public void destroy(Player p, Collection<Integer> id) {
        for (int i : id) {
            PacketPlayOutEntityDestroy ent = new PacketPlayOutEntityDestroy(i);
            ((CraftPlayer) p).getHandle().b.sendPacket(ent);
        }
    }

    @Override
    public void followPlayer(Player player, LivingEntity entity, double d) {
        float f = (float) d;
        ((EntityInsentient) ((CraftEntity) entity).getHandle()).getNavigation().a(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), f);
    }

    @Override
    public void displayParticle(Player p, Location location, float offsetX, float offsetY, float offsetZ, int speed, String enumParticle, int amount) {
        if (location.getWorld() == null) return;
        location.getWorld().spawnParticle(Particle.valueOf(enumParticle), location, amount, offsetX, offsetY, offsetZ, speed);
    }

    @Override
    public void broadcastParticle(Location location, float offsetX, float offsetY, float offsetZ, int speed, String enumParticle, int amount, double range) {
        if (location.getWorld() == null) return;
        location.getWorld().spawnParticle(Particle.valueOf(enumParticle), location, amount, offsetX, offsetY, offsetZ, speed);
    }

    @Override
    public boolean isParticle(String particle) {
        try {
            Particle.valueOf(particle);
        } catch (EnumConstantNotPresentException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}