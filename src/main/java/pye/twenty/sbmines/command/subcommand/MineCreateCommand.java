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
import pye.twenty.sbmines.mine.Mine;

public class MineCreateCommand extends SubCommand {
    public MineCreateCommand() {
        super("create", 1);
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player player)) {
            return false;
        }

        WorldEdit worldEdit = WorldEdit.getInstance();
        LocalSession session = worldEdit.getSessionManager().findByName(player.getName());
        if (session != null) {
            World world = session.getSelectionWorld();
            try {
                Region selection = session.getSelection(world);
                Mine mine = new Mine(selection.getMinimumPoint(), selection.getMaximumPoint(), selection.getWorld().getName());
                SBMines.INSTANCE.getMineManager().addMine(mine, args[0]);
                SBMines.INSTANCE.log("Created mine '%s'".formatted(args[0]));
                player.sendMessage("§aSuccessfully created mine '%s'!".formatted(args[0]));
                return false;
            } catch (IncompleteRegionException e) {
                player.sendMessage(Component.text("§cYou must select a region!"));
                return false;
            }
        }

        player.sendMessage(Component.text("§cSomething went wrong!"));
        return false;
    }
}
