package pye.twenty.sbmines.manager;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import pye.twenty.sbessentials.config.Config;
import pye.twenty.sbmines.SBMines;
import pye.twenty.sbmines.mine.Mine;

import java.io.File;
import java.util.*;

public class MineManager extends Config {

    private final Map<String, Mine> mines = new HashMap<>();

    public MineManager() {
        super(SBMines.INSTANCE.getPlugin().getDataFolder() + File.separator + "mine" + File.separator + "mines.yml");
        loadMines();
    }

    public void saveMines() {
        for (String key : mines.keySet()) {
            config.set("mines." + key, mines.get(key));
        }
        saveConfig();
    }

    private void loadMines() {
        if (config.contains("mines")) {
            ConfigurationSection section = config.getConfigurationSection("mines");
            for (String key : section.getKeys(false)) {
                Mine mine = section.getSerializable(key, Mine.class);
                mines.put(key, mine);


                SBMines.INSTANCE.log("Successfully loaded mine '%s'".formatted(key));
            }
        }
    }

    public void addMine(Mine mine, String name) {
        mines.put(name.toLowerCase(), mine);
    }

    public void removeMine(String key) {
        if (!mines.containsKey(key)) {
            return;
        }

        config.set("mines.%s".formatted(key), null);
        mines.remove(key);
        saveMines();
    }

    public Optional<Mine> getMine(String name) {
        return mines.keySet().stream()
                .filter(s -> s.equalsIgnoreCase(name))
                .findFirst()
                .map(mines::get);
    }

    public Set<String> getMines() {
        return mines.keySet();
    }
}
