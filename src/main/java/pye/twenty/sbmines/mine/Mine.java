package pye.twenty.sbmines.mine;

import com.sk89q.worldedit.math.BlockVector3;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;
import pye.twenty.sbessentials.util.NumberUtils;
import pye.twenty.sbmines.SBMines;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

@SerializableAs("Mine")
public class Mine implements ConfigurationSerializable {

    private Location minLocation;
    private Location maxLocation;

    @Getter
    private final Map<Material, Integer> materials;

    public Mine(BlockVector3 minLocation, BlockVector3 maxLocation, String worldName) {
        updateRegion(minLocation, maxLocation, worldName);
        materials = new HashMap<>();
    }

    public Mine(Location minLocation, Location maxLocation, Map<Material, Integer> materials) {
        this.minLocation = minLocation;
        this.maxLocation = maxLocation;
        this.materials = materials;
    }

    public void updateRegion(BlockVector3 minLocation, BlockVector3 maxLocation, String worldName) {
        World world = SBMines.INSTANCE.getPlugin().getServer().getWorld(worldName);
        this.minLocation = new Location(world, minLocation.getX(), minLocation.getY(), minLocation.getZ());
        this.maxLocation = new Location(world, maxLocation.getX(), maxLocation.getY(), maxLocation.getZ());
    }

    public void reset() {
        World world = minLocation.getWorld();
        for (int x = minLocation.getBlockX(); x < maxLocation.getBlockX(); x++) {
            for (int y = minLocation.getBlockY(); y < maxLocation.getBlockY(); y++) {
                for (int z = minLocation.getBlockZ(); z < maxLocation.getBlockZ(); z++) {
                    new Location(world, x, y, z).getBlock().setType(randomMaterial());
                }
            }
        }
    }

    private Material randomMaterial() {


        float random = NumberUtils.randomInt(1000);

        float total = 0;
        for (Material material : materials.keySet()) {
            float chance = materials.get(material);
            total += chance;
            if (random <= total) {
                return material;
            }
        }
        return Material.BEDROCK;
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
        if (remainingChance >= chance) {
            this.materials.put(material, chance);
            return true;
        }
        return false;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> serializedData = new HashMap<>();
        serializedData.put("minLocation", minLocation);
        serializedData.put("maxLocation", maxLocation);
        Map<String, Integer> serializedMaterials = new HashMap<>();
        for (Map.Entry<Material, Integer> entry : materials.entrySet()) {
            serializedMaterials.put(entry.getKey().name(), entry.getValue());
        }
        serializedData.put("materials", serializedMaterials);
        return serializedData;
    }

    public static Mine deserialize(Map<String, Object> args) {
        if (args == null) {
            throw new IllegalArgumentException("The 'args' argument cannot be null.");
        }

        Location minLocation = (Location) args.get("minLocation");
        Location maxLocation = (Location) args.get("maxLocation");

        Map<Material, Integer> materials = new HashMap<>();
        Map<String, Integer> serializedMaterials = (Map<String, Integer>) args.get("materials");

        if (serializedMaterials != null) {
            for (Map.Entry<String, Integer> entry : serializedMaterials.entrySet()) {
                Material material = Material.getMaterial(entry.getKey());
                if (material != null) {
                    materials.put(material, entry.getValue());
                } else {
                    SBMines.INSTANCE.log(Level.WARNING, "Material not found in mine!");
                }
            }
        }

        return new Mine(minLocation, maxLocation, materials);
    }
}
