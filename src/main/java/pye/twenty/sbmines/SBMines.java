package pye.twenty.sbmines;

import lombok.Getter;
import pye.twenty.sbessentials.SBEssentials;
import pye.twenty.sbmines.command.MineCommand;
import pye.twenty.sbmines.manager.MineManager;

import java.util.logging.Level;

@Getter
public enum SBMines {

    INSTANCE;

    private SBMinesPlugin plugin;
    private MineManager mineManager;


    public void initialize(SBMinesPlugin plugin) {
        this.plugin = plugin;
        mineManager = new MineManager();
        registerCommands();
    }

    private void registerCommands() {
        SBEssentials.addCommand("mine", new MineCommand(), plugin);
    }

    public void disable() {
        mineManager.saveMines();
    }

    public void log(String string) {
        assert plugin != null;
        plugin.getLogger().log(Level.INFO, string);
    }
}
