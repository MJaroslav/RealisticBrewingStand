package mjaroslav.mcmods.realisticbrewingstand.common.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mjaroslav.mcmods.realisticbrewingstand.RealisticBrewingStandMod;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStand;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockFixedStand extends BlockBrewingStand {
	private Random rand = new Random();
	@SideOnly(Side.CLIENT)
	private IIcon icon;

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 25;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileFixedStand();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
		setBlockBounds(0.4375F, 0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		setBlockBoundsForItemRender();
		super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0F, 0F, 0F, 1F, 0.125F, 1F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
	    float hitY, float hitZ) {
		if (!world.isRemote)
			player.openGui(RealisticBrewingStandMod.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		if (stack.hasDisplayName() && world.getTileEntity(x, y, z) instanceof TileFixedStand)
			((TileFixedStand) world.getTileEntity(x, y, z)).setCustomName(stack.getDisplayName());
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if (world.getTileEntity(x, y, z) instanceof TileFixedStand) {
			TileFixedStand stand = (TileFixedStand) world.getTileEntity(x, y, z);
			for (int slot = 0; slot < stand.getSizeInventory(); ++slot) {
				ItemStack itemStack = stand.getStackInSlot(slot);
				if (itemStack != null) {
					float mX = rand.nextFloat() * 0.8F + 0.1F;
					float mY = rand.nextFloat() * 0.8F + 0.1F;
					float mZ = rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityItem = new EntityItem(world, x + mX, y + mY, z + mZ, itemStack);
					entityItem.motionX = rand.nextGaussian() * 0.05F;
					entityItem.motionY = rand.nextGaussian() * 0.05F + 0.2F;
					entityItem.motionZ = rand.nextGaussian() * 0.05F;
					world.spawnEntityInWorld(entityItem);
				}
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int i) {
		return Items.brewing_stand;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		world.spawnParticle("smoke", x + 0.4F + rand.nextFloat() * 0.2F, y + 0.7F + rand.nextFloat() * 0.3F,
		    z + 0.4F + rand.nextFloat() * 0.2F, 0.0D, 0.0D, 0.0D);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getItem(World world, int x, int y, int z) {
		return Items.brewing_stand;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory((TileFixedStand) world.getTileEntity(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		icon = register.registerIcon(getTextureName() + "_base");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFixedBrewingStandBase() {
		return icon;
	}
}