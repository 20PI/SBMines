package pye.twenty.sbmines.command.subcommand;

import org.bukkit.command.CommandSender;
import pye.twenty.sbessentials.command.SubCommand;
import pye.twenty.sbmines.SBMines;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> getAutocomplete() {
        return new ArrayList<>(SBMines.INSTANCE.getMineManager().getMines());
    }
}
