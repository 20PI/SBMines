package pye.twenty.sbmines.command.subcommand;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pye.twenty.sbessentials.command.SubCommand;
import pye.twenty.sbmines.SBMines;

public class MineRegionCommand extends SubCommand {
    public MineRegionCommand() {
        super("region", 1);
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player player)) {
            return false;
        }
        SBMines.INSTANCE.getMineManager().getMine(args[0]).ifPresentOrElse(m -> {
            WorldEdit worldEdit = WorldEdit.getInstance();
            LocalSession session = worldEdit.getSessionManager().findByName(player.getName());
            if (session != null) {
                World world = session.getSelectionWorld();
                try {
                    Region selection = session.getSelection(world);
                    m.updateRegion(selection.getMinimumPoint(), selection.getMaximumPoint(), selection.getWorld().getName());
                    player.sendMessage("§aSuccessfully changed region for mine '%s'!".formatted(args[0]));
                    return;
                } catch (IncompleteRegionException e) {
                    player.sendMessage("§cYou must select a region!");
                    return;
                }
            }

            player.sendMessage(Component.text("§cSomething went wrong!"));
        }, () -> {
            player.sendMessage("§cCould not find mine with name '%s'".formatted(args[0]));
        });



        return false;
    }
}
