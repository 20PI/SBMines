package pye.twenty.sbmines.mine;

import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;
import pye.twenty.sbmines.SBMines;

import java.util.HashMap;
import java.util.Map;
@SerializableAs("Mine")
public class Mine implements ConfigurationSerializable {

    private final Location minLocation;
    private final Location maxLocation;

    private final Map<Material, Integer> materials = new HashMap<>();

    public Mine(BlockVector3 minLocation, BlockVector3 maxLocation, String worldName) {
        World world = SBMines.INSTANCE.getPlugin().getServer().getWorld(worldName);
        this.minLocation = new Location(world, minLocation.getX(), minLocation.getY(), minLocation.getZ());
        this.maxLocation = new Location(world, maxLocation.getX(), maxLocation.getY(), maxLocation.getZ());
    }

    public Mine(Location minLocation, Location maxLocation) {
        this.minLocation = minLocation;
        this.maxLocation = maxLocation;
    }

    public int totalChance() {
        int total = 0;
        for (int chance : materials.values()) {
            total += chance;
        }
        return total;
    }

    public int remainingChance() {
        return 1000 - totalChance();
    }

    public boolean addMaterial(Material material, int chance) {
        int remainingChance = remainingChance() - materials.getOrDefault(material, 0);
        if (remainingChance <= chance) {
            this.materials.put(material, chance);
            return true;
        }
        return false;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> serializedData = new HashMap<>();
        serializedData.put("minLocation", minLocation.serialize());
        serializedData.put("maxLocation", maxLocation.serialize());
        return serializedData;
    }

    public static Mine deserialize(Map<String, Object> serializedData) {
        Location minLocation = Location.deserialize((Map<String, Object>) serializedData.get("minLocation"));
        Location maxLocation = Location.deserialize((Map<String, Object>) serializedData.get("maxLocation"));
        return new Mine(minLocation, maxLocation);
    }



}
