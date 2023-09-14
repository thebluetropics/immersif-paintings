package me.thebluetropics.block;

import me.thebluetropics.helper.Helpers;
import net.minecraft.block.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ShortPaintingBlock extends PaintingBlock {
    public ShortPaintingBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        return switch (dir) {
            case NORTH -> Helpers.shape(3, 1, 15, 13, 15, 16);
            case SOUTH -> Helpers.shape(3, 1, 0, 13, 15, 1);
            case EAST -> Helpers.shape(0, 1, 3, 1, 15, 13);
            case WEST -> Helpers.shape(15, 1, 3, 16, 15, 13);
            default -> VoxelShapes.fullCube();
        };
    }
}
