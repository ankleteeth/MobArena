package com.garbagemule.MobArena;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractArena implements Arenaz
{

    private String name;
    private World world;
    private MobArena plugin;
    
    private boolean enabled, setup, running;
    private Set<Player> arenaPlayers, lobbyPlayers, readyPlayers, specPlayers;
    private Map<Player,String> playerClassMap;
    private Map<Player,Location> locations;
    private Map<Player,Integer> healthMap;
    
    /**
     * Start the arena session.
     * This method should warp all players to their respective warp points, start all
     * needed timers, clear/populate all sets and lists, and flag all booleans.
     */
    public abstract void startArena();
    
    /**
     * Stop the arena session.
     * Distribute rewards, clean up arena floor and reset everything to how it was before
     * the arena session was started, false otherwise
     */
    public abstract void endArena();
    
    /**
     * Force the arena to start.
     * If some players are ready, this method will force all non-ready players to leave,
     * and the arena will start with only the currently ready players.
     * @return true, if the arena was successfully started, false otherwise
     */
    public abstract boolean forceStart();
    
    /**
     * Force the arena to end.
     * Returns all players to their entry locations, distributes rewards, cleans the arena
     * floor, as well as all lists, sets and maps. Calling this method will return the
     * arena to the state it would be in right after MobArena has loaded.
     * @return true, if the session was successfully ended.
     */
    public abstract boolean forceEnd();
    
    /**
     * Player joins the arena/lobby.
     * @param p A player
     * @precondition Calling canJoin(p) for the given player must return true.
     */
    public void playerJoin(Player p)
    {
        storePlayerData(p, p.getLocation());
        MAUtils.sitPets(p);
        p.setHealth(20);
        movePlayerToLobby(p);
    }
    
    /**
     * Player leaves the arena or lobby.
     * @param p A player
     * @precondition Calling canLeave(p) for the given player must return true.
     */
    /*
    public void playerLeave(Player p)
    {
        if (arenaPlayers.contains(p) || lobbyPlayers.contains(p))
            finishArenaPlayer(p);
        
        movePlayerToEntry(p);
        discardPlayer(p);
        
        // End arena if possible.
        endArena();
    }*/
    
    /**
     * Player joins the spectator area.
     * @param p A player
     * @precondition Calling canSpec(p) for the given player must return true.
     */
    public abstract void playerSpec(Player p);
    
    /**
     * Player dies in the arena.
     */
    public abstract void playerDeath(Player p);
    
    /**
     * Player signals that they are ready.
     */
    public abstract void playerReady(Player p);
    
    /**
     * Check if a player can join the arena.
     * @param p A player
     * @return true, if the player is eligible to join the arena.
     */
    public abstract boolean canJoin(Player p);
    
    /**
     * Check if a player can leave the arena.
     * @param p A player
     * @return true, if the player is eligible to leave the arena.
     */
    public abstract boolean canLeave(Player p);
    
    /**
     * Check if a player can spectate the arena.
     * @param p A player
     * @return true, if the player is eligible for spectating.
     */
    public abstract boolean canSpec(Player p);
    
    /**
     * Check if the arena is enabled.
     * @return true, if the arena is enabled.
     */
    public boolean isEnabled()
    {
        return enabled;
    }
    
    /**
     * Check if the arena is set up and ready for use.
     * @return true, if the arena is ready for use.
     */
    public boolean isSetup()
    {
        return setup;
    }
    
    /**
     * Check if the arena is running.
     * @return true, if the arena is running.
     */
    public boolean isRunning()
    {
        return running;
    }
    
    public void storePlayerData(Player p, Location loc)
    {
        // TODO: Get this sorted out
        //plugin.getAM().arenaMap.put(p, this);
        
        if (!locations.containsKey(p))
            locations.put(p, loc);

        if (!healthMap.containsKey(p))
            healthMap.put(p, p.getHealth());
    }
    
    public abstract void movePlayerToLobby(Player p);
    
    public abstract void movePlayerToSpec(Player p);
    
    public abstract void movePlayerToEntry(Player p);
    
    public abstract void restoreInvAndGiveRewards(final Player p);
}
