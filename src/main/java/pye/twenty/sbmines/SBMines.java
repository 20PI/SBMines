package pye.twenty.sbmines;

import lombok.Getter;
import pye.twenty.sbessentials.SBEssentials;
import pye.twenty.sbmines.command.MinesCommand;

import java.util.logging.Level;

@Getter
public enum SBMines {

    INSTANCE;

    private SBMinesPlugin plugin;

    public void initialize(SBMinesPlugin plugin) {
        this.plugin = plugin;
        registerCommands();
    }

    private void registerCommands() {
        SBEssentials.addCommand("mines", new MinesCommand(), plugin);
    }

    public void log(String string) {
        assert plugin != null;
        plugin.getLogger().log(Level.INFO, string);
    }
}
