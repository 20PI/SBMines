package pye.twenty.sbmines;

import lombok.Getter;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import pye.twenty.sbessentials.SBEssentials;
import pye.twenty.sbmines.command.MineCommand;
import pye.twenty.sbmines.manager.MineManager;
import pye.twenty.sbmines.mine.Mine;

import java.util.logging.Level;

@Getter
public enum SBMines {

    INSTANCE;

    private SBMinesPlugin plugin;
    private MineManager mineManager;


    public void initialize(SBMinesPlugin plugin) {
        this.plugin = plugin;
        registerSerializables();
        registerManagers();
        registerCommands();
    }


    private void registerManagers() {
        mineManager = new MineManager();
    }
    private void registerSerializables() {
        ConfigurationSerialization.registerClass(Mine.class);
    }
    private void registerCommands() {
        SBEssentials.addCommand("mine", new MineCommand(), plugin);
    }

    public void disable() {
        mineManager.saveMines();
    }

    public void log(String string) {
        assert plugin != null;
        log(Level.INFO, string);
    }

    public void log(Level level, String string) {
        assert plugin != null;
        plugin.getLogger().log(level, string);
    }
}
