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

public class ShortPaintingBlock extends PaintingBlock {
    public ShortPaintingBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        switch (dir) {
            case NORTH:
                return Helpers.shape(3, 1, 15, 13, 15, 16);
            case SOUTH:
                return Helpers.shape(3, 1, 0, 13, 15, 1);
            case EAST:
                return Helpers.shape(0, 1, 3, 1, 15, 13);
            case WEST:
                return Helpers.shape(15, 1, 3, 16, 15, 13);
            default:
                return VoxelShapes.fullCube();
        }
    }
}
