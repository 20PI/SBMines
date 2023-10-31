package pye.twenty.sbmines;

import org.bukkit.plugin.java.JavaPlugin;

public final class SBMinesPlugin extends JavaPlugin {


    @Override
    public void onEnable() {
        SBMines.INSTANCE.initialize(this);
    }

    @Override
    public void onDisable() {
        SBMines.INSTANCE.disable();
    }
}
