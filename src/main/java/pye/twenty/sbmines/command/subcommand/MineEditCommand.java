package pye.twenty.sbmines.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pye.twenty.sbessentials.command.SubCommand;
import pye.twenty.sbmines.SBMines;

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
            
        }, () -> {

        });
        return false;
    }
}
