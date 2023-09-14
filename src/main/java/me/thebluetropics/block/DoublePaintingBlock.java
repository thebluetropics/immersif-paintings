package me.thebluetropics.block;

import me.thebluetropics.helper.Helpers;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class DoublePaintingBlock extends Block {
    public DoublePaintingBlock(Settings settings) {
        super(settings);
        setDefaultState(
            getDefaultState()
                .with(Properties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.DOUBLE_BLOCK_HALF, Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        DoubleBlockHalf half = state.get(Properties.DOUBLE_BLOCK_HALF);
        return switch (dir) {
            case NORTH ->
                half.equals(DoubleBlockHalf.LOWER) ? Helpers.shape(1, 2, 15, 15, 16, 16) : Helpers.shape(1, 0, 15, 15, 14, 16);
            case SOUTH ->
                half.equals(DoubleBlockHalf.LOWER) ? Helpers.shape(1, 2, 0, 15, 16, 1) : Helpers.shape(1, 0, 0, 15, 14, 1);
            case EAST ->
                half.equals(DoubleBlockHalf.LOWER) ? Helpers.shape(0, 2, 1, 1, 16, 15) : Helpers.shape(0, 0, 1, 1, 14, 15);
            case WEST ->
                half.equals(DoubleBlockHalf.LOWER) ? Helpers.shape(15, 2, 1, 16, 16, 15) : Helpers.shape(15, 0, 1, 16, 14, 15);
            default -> VoxelShapes.fullCube();
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();

        if (blockPos.getY() < world.getTopY() - 1 && world.getBlockState(blockPos.up()).canReplace(ctx)) {
            for (Direction direction : ctx.getPlacementDirections()) {
                if (direction.getAxis() != Direction.Axis.Y) {
                    BlockState blockState = this.getDefaultState()
                        .with(Properties.HORIZONTAL_FACING, direction.getOpposite())
                        .with(Properties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER);
                    if (!blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) continue;
                    return blockState;
                }
            }
        }

        return null;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return
            WallMountedBlock.canPlaceAt(world, pos, state.get(Properties.HORIZONTAL_FACING).getOpposite()) &&
            WallMountedBlock.canPlaceAt(world, pos.up(), state.get(Properties.HORIZONTAL_FACING).getOpposite());
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        DoubleBlockHalf half = state.get(Properties.DOUBLE_BLOCK_HALF);
        if (dir.getOpposite() == direction && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        if (direction.getAxis().equals(Direction.Axis.Y) && half == DoubleBlockHalf.LOWER == direction.equals(Direction.UP)) {
            if (neighborState.isOf(this) && neighborState.get(Properties.DOUBLE_BLOCK_HALF) != half) {
                return state.with(Properties.HORIZONTAL_FACING, neighborState.get(Properties.HORIZONTAL_FACING));
            }
            return Blocks.AIR.getDefaultState();
        }
        if (half.equals(DoubleBlockHalf.LOWER) && direction.equals(Direction.DOWN) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), state.with(Properties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            BlockPos blockPos;
            BlockState blockState;
            DoubleBlockHalf half = state.get(Properties.DOUBLE_BLOCK_HALF);

            if (half.equals(DoubleBlockHalf.UPPER) && (blockState = world.getBlockState(blockPos = pos.down())).isOf(state.getBlock()) && blockState.get(Properties.DOUBLE_BLOCK_HALF).equals(DoubleBlockHalf.LOWER)) {
                BlockState blockState2 = blockState.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                world.setBlockState(blockPos, blockState2, Block.NOTIFY_ALL | Block.SKIP_DROPS);
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
            }
        }
        super.onBreak(world, pos, state, player);
    }
}
