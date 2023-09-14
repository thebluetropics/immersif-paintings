package me.thebluetropics.block;

import me.thebluetropics.helper.Helpers;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class PaintingBlock extends Block {
    public PaintingBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        return switch (dir) {
            case NORTH -> Helpers.shape(1, 2, 15, 15, 14, 16);
            case SOUTH -> Helpers.shape(1, 2, 0, 15, 14, 1);
            case EAST -> Helpers.shape(0, 2, 1, 1, 14, 15);
            case WEST -> Helpers.shape(15, 2, 1, 16, 14, 15);
            default -> VoxelShapes.fullCube();
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis() != Direction.Axis.Y) {
                BlockState blockState = this.getDefaultState().with(Properties.HORIZONTAL_FACING, direction.getOpposite());
                if (!blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) continue;
                return blockState;
            }
        }
        return null;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return WallMountedBlock.canPlaceAt(world, pos, state.get(Properties.HORIZONTAL_FACING).getOpposite());
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(Properties.HORIZONTAL_FACING).getOpposite() == direction && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
