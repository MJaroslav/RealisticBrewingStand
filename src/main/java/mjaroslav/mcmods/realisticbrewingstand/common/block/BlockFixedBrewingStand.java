package mjaroslav.mcmods.realisticbrewingstand.common.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mjaroslav.mcmods.realisticbrewingstand.RealisticBrewingStandMod;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStand;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockFixedBrewingStand extends BlockBrewingStand {
	private Random rand = new Random();
	@SideOnly(Side.CLIENT)
	private IIcon iconFixedBrewingStandBase;

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
		return new TileEntityFixedBrewingStand();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list,
			Entity entity) {
		this.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
		this.setBlockBoundsForItemRender();
		super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntityFixedBrewingStand tileEntityFixedBrewingStand = (TileEntityFixedBrewingStand) world
					.getTileEntity(x, y, z);
			if (tileEntityFixedBrewingStand != null) {
				player.openGui(RealisticBrewingStandMod.instance, 0, world, x, y, z);
			}
			return true;
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		if (stack.hasDisplayName()) {
			((TileEntityFixedBrewingStand) world.getTileEntity(x, y, z)).setCustomName(stack.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityFixedBrewingStand) {
			TileEntityFixedBrewingStand tileEntityFixedBrewingStand = (TileEntityFixedBrewingStand) tileEntity;
			for (int slot = 0; slot < tileEntityFixedBrewingStand.getSizeInventory(); ++slot) {
				ItemStack itemStack = tileEntityFixedBrewingStand.getStackInSlot(slot);
				if (itemStack != null) {
					float mX = this.rand.nextFloat() * 0.8F + 0.1F;
					float mY = this.rand.nextFloat() * 0.8F + 0.1F;
					float mZ = this.rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem = new EntityItem(world, (double) ((float) x + mX), (double) ((float) y + mY),
							(double) ((float) z + mZ), itemStack);
					float mM = 0.05F;
					entityitem.motionX = (double) ((float) this.rand.nextGaussian() * mM);
					entityitem.motionY = (double) ((float) this.rand.nextGaussian() * mM + 0.2F);
					entityitem.motionZ = (double) ((float) this.rand.nextGaussian() * mM);
					world.spawnEntityInWorld(entityitem);
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
		double mX = (double) ((float) x + 0.4F + rand.nextFloat() * 0.2F);
		double mY = (double) ((float) y + 0.7F + rand.nextFloat() * 0.3F);
		double mZ = (double) ((float) z + 0.4F + rand.nextFloat() * 0.2F);
		world.spawnParticle("smoke", mX, mY, mZ, 0.0D, 0.0D, 0.0D);
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
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		this.iconFixedBrewingStandBase = register.registerIcon(this.getTextureName() + "_base");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFixedBrewingStandBase() {
		return this.iconFixedBrewingStandBase;
	}
}