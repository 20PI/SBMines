package pye.twenty.sbmines.command.subcommand;

import org.bukkit.command.CommandSender;
import pye.twenty.sbessentials.command.SubCommand;
import pye.twenty.sbmines.SBMines;

import java.util.Set;

public class MineListCommand extends SubCommand {
    public MineListCommand() {
        super("list", 0);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Set<String> mines = SBMines.INSTANCE.getMineManager().getMines();
        sender.sendMessage("§7Total mines %d".formatted(mines.size()));
        sender.sendMessage("§7Mines:".formatted(mines.size()));
        for (String key : mines) {
            sender.sendMessage("§7 - §e%s".formatted(key));
        }
        return false;
    }
}
