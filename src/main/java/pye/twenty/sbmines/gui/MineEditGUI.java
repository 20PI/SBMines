package pye.twenty.sbmines.gui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import pye.twenty.sbessentials.gui.GUI;
import pye.twenty.sbessentials.gui.Label;
import pye.twenty.sbessentials.gui.Slot;
import pye.twenty.sbessentials.gui.Slots;
import pye.twenty.sbessentials.util.GUIUtils;
import pye.twenty.sbessentials.util.ItemBuilder;
import pye.twenty.sbmines.SBMines;
import pye.twenty.sbmines.mine.Mine;

@Slots(slots = 9 * 6)
@Label(label = "Editing Mine")
public class MineEditGUI extends GUI { // TODO: warning function in GUI class

    private final Mine mine;

    public MineEditGUI(Mine mine) {
        this.mine = mine;
    }

    @Override
    protected void initialize() {
        GUIUtils.fillRow(inventory, 5, GUIUtils.blank());

        for (Material material : mine.getMaterials().keySet()) {
            int chance = mine.getMaterials().get(material);
            ItemStack entry = new ItemBuilder(material)
                    .lore(
                            "§7Chance: §a%.1f%%".formatted(chance / 10f),
                            "§7Max chance: §e%.1f%%".formatted((mine.remainingChance() + mine.getMaterials().get(material)) / 10f),
                            "",
                            "§8Drag in material to change type!",
                            "§eClick to edit chance!"
                    ).build();

            addSlot(entry, e -> {
                if (!e.getClickedInventory().equals(inventory)) {
                    return;
                }

                Material cursor = e.getCursor().getType();
                if (!cursor.isAir() && e.getCurrentItem() != null) {
                    if (e.getCursor().getType().isBlock()) {
                        if (mine.getMaterials().containsKey(cursor)) {
                            mine.getMaterials().remove(cursor);
                        } else {
                            mine.getMaterials().put(cursor, mine.getMaterials().get(e.getCurrentItem().getType()));
                            mine.getMaterials().remove(e.getCurrentItem().getType());
                        }
                    }
                    reopen();
                    return;
                }
                signInput(
                        new String[] {
                                "",
                                "^^^^^^^^^^^^^^^",
                                "enter new",
                                "chance"
                        }, input -> {
                            try {
                                int percent = (int) (Double.parseDouble(input) * 10);
                                if (mine.remainingChance() + mine.getMaterials().get(material) >= percent) {
                                    mine.getMaterials().put(material, percent);
                                } else {
                                    player.sendMessage("§cNot enough chance remaining!");
                                }
                            } catch (NumberFormatException exception) {
                                player.sendMessage("§cFailed to read number!");
                            }
                        }
                );
            });
        }

        addSlot(Slot.SIXTH.getCenter(), new ItemBuilder(Material.BARRIER).name("§cClose").build(), e -> {
            player.closeInventory();
        });

        ItemStack add = new ItemBuilder(Material.OAK_BUTTON).name("§eAdd ore").lore("§eClick to add ore!").build();
        addSlot(Slot.SIXTH.getCenter() + 1, add, e -> {
            mine.addMaterial(Material.BEDROCK, 0);
            reopen();
        });

        ItemStack info = new ItemBuilder(Material.PAPER).name("§eInformation")
                .lore("§7Remaining chance: %s%.1f%%".formatted(mine.remainingChance() == 0 ? "§a" : "§e", mine.remainingChance() / 10f))
                .lore("§7Reset time: §c5m")
                .build();
        addSlot(Slot.SIXTH.getCenter() - 1, info, e -> {
            mine.addMaterial(Material.BEDROCK, 0);
            reopen();
        });
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);
        if (event.getClickedInventory().equals(inventory)) {
            event.setCancelled(true);
        }
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent event) {
        super.onInventoryClose(event);
        SBMines.INSTANCE.getMineManager().saveMines();
    }
}
