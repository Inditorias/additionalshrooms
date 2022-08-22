package net.inditorias.additionalshrooms.mixin;

import net.inditorias.additionalshrooms.registries.ModBlocks;
import net.inditorias.additionalshrooms.registries.ModTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;


@Mixin(AxeItem.class)
public class StrippingRecipeAccessor {
    @Inject(at = @At("TAIL"), method = "useOnBlock", cancellable = false)
    private void getStrippedStateTAIL(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir){
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        Optional<BlockState> resultState = Optional.empty();
        if(state.isIn(ModTags.Blocks.MUSHROOM_BLOCKS)){
            resultState = Optional.of(ModBlocks.MUSHROOM_BLOCK_INSIDES.getDefaultState());
        }else if(state.isIn(ModTags.Blocks.MUSHROOM_SLABS)){
            resultState = Optional.of(ModBlocks.MUSHROOM_BLOCK_INSIDES_SLAB.getDefaultState()
                    .with(SlabBlock.TYPE, state.get(SlabBlock.TYPE))
                    .with(SlabBlock.WATERLOGGED, state.get(SlabBlock.WATERLOGGED))
            );
        } else if (state.isIn(ModTags.Blocks.MUSHROOM_STAIRS)) {
            resultState = Optional.of(ModBlocks.MUSHROOM_BLOCK_INSIDES_STAIR.getDefaultState()
                    .with(StairsBlock.FACING, state.get(StairsBlock.FACING))
                    .with(StairsBlock.WATERLOGGED, state.get(StairsBlock.WATERLOGGED))
                    .with(StairsBlock.HALF, state.get(StairsBlock.HALF))
                    .with(StairsBlock.SHAPE, state.get(StairsBlock.SHAPE))
            );
        }

        if(resultState.isPresent()){

            World world = context.getWorld();
            BlockPos blockPos = context.getBlockPos();
            PlayerEntity playerEntity = context.getPlayer();
            ItemStack itemStack = context.getStack();
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
            }

            world.setBlockState(blockPos, resultState.get());
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, resultState.get()));
            if (playerEntity != null) {
                itemStack.damage(1, playerEntity, (p) -> {
                    p.sendToolBreakStatus(context.getHand());
                });
            }
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }
}
