package mjaroslav.mcmods.realisticbrewingstand.common.tileentity;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.PotionHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.ForgeEventFactory;

public class TileFixedStand extends TileEntity implements ISidedInventory {
	private static final int[] upSlots = new int[] { 3 };
	private static final int[] sideSlots = new int[] { 0, 1, 2 };
	private ItemStack[] brewingItemStacks = new ItemStack[4];
	private int brewTime;
	private int filledSlots;
	private Item ingredientID;
	private String customName;

	@Override
	public String getInventoryName() {
		return hasCustomInventoryName() ? customName : "container.brewing";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return customName != null && customName.length() > 0;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public int getSizeInventory() {
		return brewingItemStacks.length;
	}

	@Override
	public void updateEntity() {
		if (brewTime > 0) {
			--brewTime;
			if (brewTime == 0) {
				brewPotions();
				markDirty();
			} else if (!canBrew()) {
				brewTime = 0;
				markDirty();
			} else if (ingredientID != brewingItemStacks[3].getItem()) {
				brewTime = 0;
				markDirty();
			}
		} else if (canBrew()) {
			brewTime = 400;
			ingredientID = brewingItemStacks[3].getItem();
		}
		if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) > 0)
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
		super.updateEntity();
	}

	public int getBrewTime() {
		return brewTime;
	}

	private boolean canBrew() {
		if (brewingItemStacks[3] != null && brewingItemStacks[3].stackSize > 0) {
			ItemStack itemStack = brewingItemStacks[3];
			if (!itemStack.getItem().isPotionIngredient(itemStack)) {
				return false;
			} else {
				boolean flag = false;
				for (int slot = 0; slot < 3; ++slot) {
					if (brewingItemStacks[slot] != null && brewingItemStacks[slot].getItem() instanceof ItemPotion) {
						int meta = brewingItemStacks[slot].getItemDamage();
						int newMeta = getMetaAddedIng(meta, itemStack);
						if (!ItemPotion.isSplash(meta) && ItemPotion.isSplash(newMeta)) {
							flag = true;
							break;
						}
						List list = Items.potionitem.getEffects(meta);
						List list1 = Items.potionitem.getEffects(newMeta);
						if ((meta <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)
						    && meta != newMeta) {
							flag = true;
							break;
						}
					}
				}
				return flag;
			}
		} else {
			return false;
		}
	}

	private void brewPotions() {
		if (ForgeEventFactory.onPotionAttemptBreaw(brewingItemStacks))
			return;
		if (canBrew()) {
			ItemStack itemStack = brewingItemStacks[3];
			for (int slot = 0; slot < 3; ++slot) {
				if (brewingItemStacks[slot] != null && brewingItemStacks[slot].getItem() instanceof ItemPotion) {
					int meta = brewingItemStacks[slot].getItemDamage();
					int newMeta = getMetaAddedIng(meta, itemStack);
					List list = Items.potionitem.getEffects(meta);
					List list1 = Items.potionitem.getEffects(newMeta);
					if ((meta <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) {
						if (meta != newMeta) {
							brewingItemStacks[slot].setItemDamage(newMeta);
						}
					} else if (!ItemPotion.isSplash(meta) && ItemPotion.isSplash(newMeta)) {
						brewingItemStacks[slot].setItemDamage(newMeta);
					}
				}
			}
			if (itemStack.getItem().hasContainerItem(itemStack)) {
				brewingItemStacks[3] = itemStack.getItem().getContainerItem(itemStack);
			} else {
				--brewingItemStacks[3].stackSize;
				if (brewingItemStacks[3].stackSize <= 0) {
					brewingItemStacks[3] = null;
				}
			}
			ForgeEventFactory.onPotionBrewed(brewingItemStacks);
		}
	}

	private int getMetaAddedIng(int ingId, ItemStack stack) {
		return stack == null ? ingId
		    : (stack.getItem().isPotionIngredient(stack)
		        ? PotionHelper.applyIngredient(ingId, stack.getItem().getPotionEffect(stack)) : ingId);
	}

	public void readFromNBTS(NBTTagCompound nbtTagCompound) {
		NBTTagList nbtTagList = nbtTagCompound.getTagList("Items", 10);
		brewingItemStacks = new ItemStack[getSizeInventory()];
		for (int slot = 0; slot < nbtTagList.tagCount(); ++slot) {
			NBTTagCompound nbtTagCompound1 = nbtTagList.getCompoundTagAt(slot);
			byte slotByte = nbtTagCompound1.getByte("Slot");
			if (slotByte >= 0 && slotByte < brewingItemStacks.length) {
				brewingItemStacks[slotByte] = ItemStack.loadItemStackFromNBT(nbtTagCompound1);
			}
		}
		this.brewTime = nbtTagCompound.getShort("BrewTime");
		if (nbtTagCompound.hasKey("CustomName", 8)) {
			customName = nbtTagCompound.getString("CustomName");
		}
	}

	public void writeToNBTS(NBTTagCompound nbtTagCompound) {
		nbtTagCompound.setShort("BrewTime", (short) brewTime);
		NBTTagList nbtTagList = new NBTTagList();
		for (int slot = 0; slot < this.brewingItemStacks.length; ++slot) {
			if (brewingItemStacks[slot] != null) {
				NBTTagCompound nbtTagCompound1 = new NBTTagCompound();
				nbtTagCompound1.setByte("Slot", (byte) slot);
				brewingItemStacks[slot].writeToNBT(nbtTagCompound1);
				nbtTagList.appendTag(nbtTagCompound1);
			}
		}
		nbtTagCompound.setTag("Items", nbtTagList);
		if (hasCustomInventoryName()) {
			nbtTagCompound.setString("CustomName", customName);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		readFromNBTS(nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		writeToNBTS(nbtTagCompound);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeToNBTS(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBTS(pkt.func_148857_g());
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot >= 0 && slot < this.brewingItemStacks.length ? this.brewingItemStacks[slot] : null;
	}

	@Override
	public ItemStack decrStackSize(int from, int to) {
		if (from >= 0 && from < this.brewingItemStacks.length) {
			ItemStack itemStack = this.brewingItemStacks[from];
			this.brewingItemStacks[from] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (slot >= 0 && slot < this.brewingItemStacks.length) {
			ItemStack itemStack = this.brewingItemStacks[slot];
			this.brewingItemStacks[slot] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (slot >= 0 && slot < this.brewingItemStacks.length) {
			this.brewingItemStacks[slot] = stack;
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
		    : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
		        (double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 3 ? stack.getItem().isPotionIngredient(stack)
		    : stack.getItem() instanceof ItemPotion || stack.getItem() == Items.glass_bottle;
	}

	@SideOnly(Side.CLIENT)
	public void setBrewTime(int value) {
		this.brewTime = value;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 1 ? upSlots : sideSlots;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return this.isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return true;
	}
}
