package pye.twenty.sbmines.command.subcommand;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pye.twenty.sbessentials.command.SubCommand;

public class MineCreateCommand extends SubCommand {
    public MineCreateCommand() {
        super("create", 1);
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof Player player)) {
            return false;
        }

        WorldEdit worldEdit = WorldEdit.getInstance();
        LocalSession session = worldEdit.getSessionManager().findByName(player.getName());
        if (session != null) {
            World world = session.getSelectionWorld();
            try {
                Region selection = session.getSelection(world);
                // TODO: Finish this
            } catch (IncompleteRegionException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
