package io.github.Leonardo0013YT.UltraSkyWars.addons;

import com.alessiodp.parties.api.Parties;
import com.alessiodp.parties.api.interfaces.PartiesAPI;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PartiesAddon {

    private PartiesAPI api;

    public PartiesAddon() {
        api = Parties.getApi();
    }

    public boolean isInParty(Player p) {
        return !api.getPartyPlayer(p.getUniqueId()).getPartyName().isEmpty();
    }

    public boolean isPartyLeader(Player p) {
        PartyPlayer pp = api.getPartyPlayer(p.getUniqueId());
        if (pp == null) {
            return false;
        }
        Party party = api.getParty(pp.getPartyName());
        if (party == null || party.getLeader() == null) {
            return false;
        }
        return party.getLeader().equals(p.getUniqueId());
    }

    public List<Player> getPlayersParty(Player leader) {
        PartyPlayer pp = api.getPartyPlayer(leader.getUniqueId());
        Party party = api.getParty(pp.getPartyName());
        List<Player> online = new ArrayList<>();
        party.getOnlineMembers(true).forEach(o -> online.add(Bukkit.getPlayer(o.getPlayerUUID())));
        return online;
    }

}