package com.github.mjaroslav.realisticbrewingstand.asm.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        Inventories.writeNbt(nbt, this.inventory);
        return nbt;
    }

    private static final boolean[] PLACEHOLDER = new boolean[] { false, false, false };

    @Inject(method = "getSlotsEmpty", at = @At("HEAD"), cancellable = true)
    private void getSlotsEmpty(CallbackInfoReturnable<boolean[]> ci) { // Rewrite
        ci.setReturnValue(PLACEHOLDER);
    }

    private ItemStack[] slotsLastStack = new ItemStack[3];

    @Inject(method = "tick", at = @At("TAIL"))
    private static void tick(World world, BlockPos pos, BlockState state, BrewingStandBlockEntity blockEntity,
            CallbackInfo ci) { // Extend
        // TODO: Remake with mixing to "has bottle" properties.
        var flag = false;
        for (int i = 0; i < 3; ++i)
            flag = flag || ((MixinBrewingStandBlockEntity) (Object) blockEntity).slotsLastStack[i] == null ||
                    !ItemStack.areEqual(((MixinBrewingStandBlockEntity) (Object) blockEntity).inventory.get(i),
                            ((MixinBrewingStandBlockEntity) (Object) blockEntity).slotsLastStack[i]);
        if (flag) {
            for (int i = 0; i < 3; ++i)
                ((MixinBrewingStandBlockEntity) (Object) blockEntity).slotsLastStack[i] = ((MixinBrewingStandBlockEntity) (Object) blockEntity).inventory
                        .get(i);
            if (!(state.getBlock() instanceof BrewingStandBlock))
                return;
            if (world instanceof ServerWorld serverWorld) // Ticker server side, but...
                serverWorld.getChunkManager().markForUpdate(pos);
            // Just sync TileEntity without BlockState
        }
    }
}
