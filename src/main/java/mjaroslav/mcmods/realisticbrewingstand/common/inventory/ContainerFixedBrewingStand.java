package mjaroslav.mcmods.realisticbrewingstand.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;

public class ContainerFixedBrewingStand extends Container {
	private TileFixedStand stand;
	private final Slot theSlot;
	private int brewTime;

	public ContainerFixedBrewingStand(InventoryPlayer player, TileFixedStand tile) {
		stand = tile;
		addSlotToContainer(new ContainerFixedBrewingStand.FixedPotion(player.player, tile, 0, 56, 46));
		addSlotToContainer(new ContainerFixedBrewingStand.FixedPotion(player.player, tile, 1, 79, 53));
		addSlotToContainer(new ContainerFixedBrewingStand.FixedPotion(player.player, tile, 2, 102, 46));
		theSlot = addSlotToContainer(new ContainerFixedBrewingStand.FixedIngredient(tile, 3, 79, 17));
		for (int line = 0; line < 3; ++line) {
			for (int column = 0; column < 9; ++column) {
				addSlotToContainer(new Slot(player, column + line * 9 + 9, 8 + column * 18, 84 + line * 18));
			}
		}
		for (int line = 0; line < 9; ++line) {
			addSlotToContainer(new Slot(player, line, 8 + line * 18, 142));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, stand.getBrewTime());
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int id = 0; id < crafters.size(); ++id) {
			ICrafting iCrafting = (ICrafting) crafters.get(id);
			if (this.brewTime != stand.getBrewTime()) {
				iCrafting.sendProgressBarUpdate(this, 0, stand.getBrewTime());
			}
		}
		brewTime = stand.getBrewTime();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			stand.setBrewTime(value);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return stand.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		ItemStack itemStack = null;
		Slot slot = (Slot) inventorySlots.get(slotId);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemStack1 = slot.getStack();
			itemStack = itemStack1.copy();
			if ((slotId < 0 || slotId > 2) && slotId != 3) {
				if (!theSlot.getHasStack() && theSlot.isItemValid(itemStack1)) {
					if (!mergeItemStack(itemStack1, 3, 4, false)) {
						return null;
					}
				} else if (ContainerFixedBrewingStand.FixedPotion.canHoldPotion(itemStack)) {
					if (!mergeItemStack(itemStack1, 0, 3, false)) {
						return null;
					}
				} else if (slotId >= 4 && slotId < 31) {
					if (!mergeItemStack(itemStack1, 31, 40, false)) {
						return null;
					}
				} else if (slotId >= 31 && slotId < 40) {
					if (!mergeItemStack(itemStack1, 4, 31, false)) {
						return null;
					}
				} else if (!mergeItemStack(itemStack1, 4, 40, false)) {
					return null;
				}
			} else {
				if (!mergeItemStack(itemStack1, 4, 40, true)) {
					return null;
				}
				slot.onSlotChange(itemStack1, itemStack);
			}
			if (itemStack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			if (itemStack1.stackSize == itemStack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(player, itemStack1);
		}
		return itemStack;
	}

	class FixedIngredient extends Slot {
		public FixedIngredient(TileFixedStand inventory, int id, int x, int y) {
			super(inventory, id, x, y);
		}

		@Override
		public boolean isItemValid(ItemStack item) {
			return item != null ? item.getItem().isPotionIngredient(item) : false;
		}

		@Override
		public int getSlotStackLimit() {
			return 64;
		}
	}

	static class FixedPotion extends Slot {
		private EntityPlayer player;

		public FixedPotion(EntityPlayer player, TileFixedStand inventory, int id, int x, int y) {
			super(inventory, id, x, y);
			this.player = player;
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return canHoldPotion(stack);
		}

		@Override
		public int getSlotStackLimit() {
			return 1;
		}

		@Override
		public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
			if (stack.getItem() instanceof ItemPotion && stack.getItemDamage() > 0) {
				player.addStat(AchievementList.potion, 1);
			}
			super.onPickupFromSlot(player, stack);
		}

		public static boolean canHoldPotion(ItemStack stack) {
			return stack != null && (stack.getItem() instanceof ItemPotion || stack.getItem() == Items.glass_bottle);
		}
	}
}
