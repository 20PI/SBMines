package pye.twenty.sbmines.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import pye.twenty.sbessentials.command.SBCommand;
import pye.twenty.sbmines.command.subcommand.MineCreateCommand;
import pye.twenty.sbmines.command.subcommand.MineEditCommand;
import pye.twenty.sbmines.command.subcommand.MineRegionCommand;

public class MineCommand extends SBCommand {

    public MineCommand() {
        super(
                new MineCreateCommand(),
                new MineEditCommand(),
                new MineRegionCommand()
        );
    }

    @Override
    public void execute(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
    }
}
