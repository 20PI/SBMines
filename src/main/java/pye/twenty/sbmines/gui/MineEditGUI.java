package pye.twenty.sbmines.gui;

import org.bukkit.entity.Player;
import pye.twenty.sbessentials.gui.GUI;
import pye.twenty.sbessentials.gui.Label;
import pye.twenty.sbessentials.gui.Slots;
import pye.twenty.sbessentials.util.GUIUtils;
import pye.twenty.sbmines.mine.Mine;

@Slots(slots = 9 * 6)
@Label(label = "Editing Mine")
public class MineEditGUI extends GUI {

    private static final int CLOSE_SLOT = 49;
    private final Mine mine;

    public MineEditGUI(Player player, Mine mine) {
        this.mine = mine;
    }

    @Override
    protected void initialize() {
        GUIUtils.fillRow(inventory, 5, GUIUtils.blank());
        //addSlot(CLOSE_SLOT);
    }
}
