package mjaroslav.mcmods.realisticbrewingstand.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.etfuturum.recipes.BrewingFuelRegistry;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStandEtFuturum;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;

public class ContainerFixedStandEtFuturum extends Container {
	private TileFixedStandEtFuturum stand;
	private final Slot theSlot;
	private int brewTime;
	private int brewFuel;
	private int brewCurrentFuel;

	public ContainerFixedStandEtFuturum(InventoryPlayer player, TileFixedStandEtFuturum tile) {
		stand = tile;
		addSlotToContainer(new PotionSlot(tile, 0, 56, 51));
		addSlotToContainer(new PotionSlot(tile, 1, 79, 58));
		addSlotToContainer(new PotionSlot(tile, 2, 102, 51));
		theSlot = addSlotToContainer(new IngredientSlot(tile, 3, 79, 17));
		addSlotToContainer(new BlazePowderSlot(tile, 4, 17, 17));
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
			if (brewTime != stand.getBrewTime()) {
				iCrafting.sendProgressBarUpdate(this, 0, stand.getBrewTime());
			}
			if (brewFuel != stand.getFuel()) {
				iCrafting.sendProgressBarUpdate(this, 1, stand.getFuel());
			}
			if (brewCurrentFuel != stand.getCurrentFuel()) {
				iCrafting.sendProgressBarUpdate(this, 2, stand.getCurrentFuel());
			}
		}
		brewTime = stand.getBrewTime();
		brewFuel = stand.getFuel();
		brewCurrentFuel = stand.getCurrentFuel();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			stand.setBrewTime(value);
		}
		if (id == 1) {
			stand.setFuel(value);
		}
		if (id == 2) {
			stand.setCurrentFuel(value);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return stand.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(slotId);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if ((slotId < 0 || slotId > 2) && slotId != 3) {
				if (!theSlot.getHasStack() && theSlot.isItemValid(itemstack1)) {
					if (!mergeItemStack(itemstack1, 3, 4, false))
						return null;
				} else if (PotionSlot.canHoldPotion(itemstack)) {
					if (!mergeItemStack(itemstack1, 0, 3, false))
						return null;
				} else if (slotId >= 4 && slotId < 31) {
					if (!mergeItemStack(itemstack1, 31, 40, false))
						return null;
				} else if (slotId >= 31 && slotId < 40) {
					if (!mergeItemStack(itemstack1, 4, 31, false))
						return null;
				} else if (!mergeItemStack(itemstack1, 4, 40, false))
					return null;
			} else {
				if (!mergeItemStack(itemstack1, 4, 40, true))
					return null;
				slot.onSlotChange(itemstack1, itemstack);
			}
			if (itemstack1.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();
			if (itemstack1.stackSize == itemstack.stackSize)
				return null;
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}

	public static class BlazePowderSlot extends Slot {
		public BlazePowderSlot(IInventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return stack != null && BrewingFuelRegistry.isFuel(stack);
		}
	}

	public static class IngredientSlot extends Slot {
		public IngredientSlot(IInventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return stack != null && stack.getItem().isPotionIngredient(stack);
		}
	}

	public static class PotionSlot extends Slot {
		public PotionSlot(IInventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
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
			if (stack.getItem() instanceof ItemPotion && stack.getItemDamage() > 0)
				player.addStat(AchievementList.potion, 1);
			super.onPickupFromSlot(player, stack);
		}

		public static boolean canHoldPotion(ItemStack stack) {
			return stack != null && (stack.getItem() instanceof ItemPotion || stack.getItem() == Items.glass_bottle);
		}
	}
}