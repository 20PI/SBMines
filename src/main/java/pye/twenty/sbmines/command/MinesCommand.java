package pye.twenty.sbmines.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import pye.twenty.sbessentials.command.SBCommand;
import pye.twenty.sbmines.command.subcommand.MineCreateCommand;

public class MinesCommand extends SBCommand {

    public MinesCommand() {
        super(new MineCreateCommand());
    }

    @Override
    public void execute(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
    }
}
