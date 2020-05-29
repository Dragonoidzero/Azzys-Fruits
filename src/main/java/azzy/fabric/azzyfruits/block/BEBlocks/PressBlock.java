package azzy.fabric.azzyfruits.block.BEBlocks;

import alexiil.mc.lib.attributes.AttributeList;
import alexiil.mc.lib.attributes.Simulation;
import alexiil.mc.lib.attributes.fluid.volume.FluidVolume;
import azzy.fabric.azzyfruits.tileentities.blockentity.BasketEntity;
import azzy.fabric.azzyfruits.tileentities.blockentity.MachineEntity;
import azzy.fabric.azzyfruits.tileentities.blockentity.PressEntity;
import azzy.fabric.azzyfruits.block.BaseMachine;
import azzy.fabric.azzyfruits.util.mixin.BucketInfo;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.sound.SoundEngine;
import net.minecraft.client.sound.SoundExecutor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.function.Supplier;

import static azzy.fabric.azzyfruits.ForgottenFruits.MODID;

public class PressBlock extends BaseMachine{

    public static final Identifier GID = new Identifier(MODID, "press_gui");

    public PressBlock(Settings settings, String identifier, Material material, BlockSoundGroup sound, int glow, VoxelShape bounds, ParticleEffect... effects) {
        super(settings, identifier, material, sound, glow, bounds, effects);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            PressEntity blockEntity = (PressEntity) world.getBlockEntity(pos);

            Item item = player.getStackInHand(hand).getItem();

            if(item instanceof BucketItem && blockEntity != null) {

                FluidVolume fluid = FluidVolume.create(((BucketInfo) item).getFluid(), 1000);
                blockEntity.fluidInv.getTank(0).attemptInsertion(fluid, Simulation.ACTION);

                    if(!player.isCreative()) player.setStackInHand(hand, new ItemStack(Items.BUCKET, 1));

                    world.playSound((PlayerEntity) null, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0f, world.random.nextFloat()*0.05f+0.95f);
            }
            else if (blockEntity != null && !player.isInSneakingPose() && player.getStackInHand(player.getActiveHand()).getItem() != Items.BUCKET) {
                ContainerProviderRegistry.INSTANCE.openContainer(GID, player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PressEntity) {
                ItemScatterer.spawn(world, (BlockPos)pos, (Inventory)((PressEntity)blockEntity));
                // update comparators
                world.updateHorizontalAdjacent(pos, this);
            }
            super.onBlockRemoved(state, world, pos, newState, moved);
        }
    }

    @Override
    public void addAllAttributes(World world, BlockPos pos, BlockState state, AttributeList<?> to) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof PressEntity) {
            PressEntity tank = (PressEntity) be;
            tank.fluidInv.offerSelfAsAttribute(to, null, null);
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new PressEntity();
    }
}
