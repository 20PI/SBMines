package pye.twenty.sbmines.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pye.twenty.sbessentials.SBEssentials;
import pye.twenty.sbessentials.command.SubCommand;
import pye.twenty.sbmines.SBMines;
import pye.twenty.sbmines.gui.MineEditGUI;

public class MineResetCommand extends SubCommand {
    public MineResetCommand() {
        super("reset", 1);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        SBMines.INSTANCE.getMineManager().getMine(args[0]).ifPresentOrElse(m -> {
            m.reset();
            sender.sendMessage("§aSuccessfully reset mine '%s'!".formatted(args[0]));
        }, () -> {
            sender.sendMessage("§cCould not find mine with name '%s'".formatted(args[0]));
        });
        return false;
    }
}
