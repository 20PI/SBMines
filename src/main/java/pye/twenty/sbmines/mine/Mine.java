package pye.twenty.sbmines.mine;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
@SerializableAs("Mine")
public class Mine implements ConfigurationSerializable {
    private Location minLocation;
    private Location maxLocation;

    public Mine(Location minLocation, Location maxLocation) {
        this.minLocation = minLocation;
        this.maxLocation = maxLocation;
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
