package pye.twenty.sbmines;

import org.bukkit.plugin.java.JavaPlugin;
import pye.twenty.sbmines.manager.MineManager;

public final class SBMinesPlugin extends JavaPlugin {

    private MineManager mineManager;

    @Override
    public void onEnable() {
        SBMines.INSTANCE.initialize(this);
        mineManager = new MineManager();
    }

    @Override
    public void onDisable() {
        mineManager.saveMines();
    }
}
