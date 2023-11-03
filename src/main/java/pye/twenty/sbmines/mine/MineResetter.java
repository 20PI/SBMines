package pye.twenty.sbmines.mine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import pye.twenty.sbmines.SBMines;

public class MineResetter {

    private enum ResetType {
        AIR,
        RANDOM
    }

    private final Mine mine;
    private int y;

    public MineResetter(Mine mine) {
        this.mine = mine;
    }

    public void reset() {
        reset(ResetType.AIR);
    }
    public void reset(ResetType resetType) {
        boolean air = resetType == ResetType.AIR;
        y = air ? mine.getMaxLocation().getBlockY() : mine.getMinLocation().getBlockY();
        World world = mine.getMinLocation().getWorld();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int x = mine.getMinLocation().getBlockX(); x < mine.getMaxLocation().getBlockX(); x++) {
                    for (int z = mine.getMinLocation().getBlockZ(); z < mine.getMaxLocation().getBlockZ(); z++) {
                        Location location = new Location(world, x, y, z);
                        location.getBlock().setType(air ? Material.AIR : mine.randomMaterial());
                    }
                }


                if ((air && y > mine.getMinLocation().getBlockY()) || (!air && y < mine.getMaxLocation().getBlockY())) {
                    y = air ? y - 1 : y + 1;
                } else {
                    cancel();
                    if (air) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                mine.launchPlayers();
                                reset(ResetType.RANDOM);
                            }
                        }.runTaskLater(SBMines.INSTANCE.getPlugin(), 15);
                    }
                }
            }
        }.runTaskTimer(SBMines.INSTANCE.getPlugin(), 0, 1);
    }
}
