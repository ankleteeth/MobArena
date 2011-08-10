package com.garbagemule.MobArena;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Arenaz
{    
    /**
     * Start the arena session.
     * This method should warp all players to their respective warp points, start all
     * needed timers, clear/populate all sets and lists, and flag all booleans.
     */
    public void startArena();
    
    /**
     * Stop the arena session.
     * Distribute rewards, clean up arena floor and reset everything to how it was before
     * the arena session was started, false otherwise
     */
    public void endArena();
    
    /**
     * Force the arena to start.
     * If some players are ready, this method will force all non-ready players to leave,
     * and the arena will start with only the currently ready players.
     * @return true, if the arena was successfully started, false otherwise
     */
    public boolean forceStart();
    
    /**
     * Force the arena to end.
     * Returns all players to their entry locations, distributes rewards, cleans the arena
     * floor, as well as all lists, sets and maps. Calling this method will return the
     * arena to the state it would be in right after MobArena has loaded.
     * @return true, if the session was successfully ended.
     */
    public boolean forceEnd();
    
    /**
     * Player joins the arena/lobby.
     * @param p A player
     * @precondition Calling canJoin(p) for the given player must return true.
     */
    public void playerJoin(Player p);
    
    /**
     * Player leaves the arena or lobby.
     * @param p A player
     * @precondition Calling canLeave(p) for the given player must return true.
     */
    public void playerLeave(Player p);
    
    /**
     * Player joins the spectator area.
     * @param p A player
     * @precondition Calling canSpec(p) for the given player must return true.
     */
    public void playerSpec(Player p);
    
    /**
     * Player dies in the arena.
     */
    public void playerDeath(Player p);
    
    /**
     * Player signals that they are ready.
     */
    public void playerReady(Player p);
    
    /**
     * Check if a player can join the arena.
     * @param p A player
     * @return true, if the player is eligible to join the arena.
     */
    public boolean canJoin(Player p);
    
    /**
     * Check if a player can leave the arena.
     * @param p A player
     * @return true, if the player is eligible to leave the arena.
     */
    public boolean canLeave(Player p);
    
    /**
     * Check if a player can spectate the arena.
     * @param p A player
     * @return true, if the player is eligible for spectating.
     */
    public boolean canSpec(Player p);
    
    /**
     * Check if the arena is enabled.
     * @return true, if the arena is enabled.
     */
    public boolean isEnabled();
    
    /**
     * Check if the arena is set up and ready for use.
     * @return true, if the arena is ready for use.
     */
    public boolean isSetup();
    
    /**
     * Check if the arena is running.
     * @return true, if the arena is running.
     */
    public boolean isRunning();
}
