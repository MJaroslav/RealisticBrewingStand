package com.github.mjaroslav.realisticbrewingstand.asm.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.mjaroslav.realisticbrewingstand.util.Utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(BrewingStandBlockEntity.class)
public abstract class MixinBrewingStandBlockEntity extends LockableContainerBlockEntity {
    protected MixinBrewingStandBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos,
            BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Shadow
    private DefaultedList<ItemStack> inventory;

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() { // Virtual
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() { // Virtual
        NbtCompound nbt = new NbtCompound();
        Inventories.writeNbt(nbt, inventory, true);
        return nbt;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/Arrays;equals([Z[Z)Z"))
    private static boolean redirect(boolean[] a, boolean[] b) { // Cut if body
        return true;
    }

    private ItemStack[] prevStacks = new ItemStack[3];

    @Inject(method = "tick", at = @At("TAIL"))
    private static void tick(World world, BlockPos pos, BlockState state, BrewingStandBlockEntity blockEntity,
            CallbackInfo ci) { // Extend
        var flag = false;
        for (int i = 0; i < 3; ++i)
            flag = flag || Utils.isStackChanged(((MixinBrewingStandBlockEntity) (Object) blockEntity).prevStacks[i],
                    ((MixinBrewingStandBlockEntity) (Object) blockEntity).inventory.get(i));
        if (flag) {
            for (int i = 0; i < 3; ++i)
                ((MixinBrewingStandBlockEntity) (Object) blockEntity).prevStacks[i] = ((MixinBrewingStandBlockEntity) (Object) blockEntity).inventory
                        .get(i).copy();
            if (!(state.getBlock() instanceof BrewingStandBlock))
                return;
            if (world instanceof ServerWorld serverWorld) { // Ticker always server side, but...
                serverWorld.getChunkManager().markForUpdate(pos); // Just sync when any item changed
                BrewingStandBlockEntity.markDirty(world, pos, state);
            }
        }
    }
}
