package pye.twenty.sbmines.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pye.twenty.sbessentials.SBEssentials;
import pye.twenty.sbessentials.command.SubCommand;
import pye.twenty.sbmines.SBMines;
import pye.twenty.sbmines.gui.MineEditGUI;

import java.util.ArrayList;
import java.util.List;

public class MineEditCommand extends SubCommand {
    public MineEditCommand() {
        super("edit", 1);
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player player)) {
            return false;
        }

        SBMines.INSTANCE.getMineManager().getMine(args[0]).ifPresentOrElse(m -> {
            SBEssentials.openGUI(player, new MineEditGUI(m));
        }, () -> {
            player.sendMessage("§cCould not find mine with name '%s'".formatted(args[0]));
        });
        return false;
    }

    @Override
    public List<String> getAutocomplete() {
        return new ArrayList<>(SBMines.INSTANCE.getMineManager().getMines());
    }
}
