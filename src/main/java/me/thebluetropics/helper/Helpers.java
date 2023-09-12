package me.thebluetropics.helper;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class Helpers {
    public static VoxelShape shape(int x0, int y0, int z0, int x1, int y1, int z1) {
        return VoxelShapes.cuboid(
            (float) x0 / 16f,
            (float) y0 / 16f,
            (float) z0 / 16f,
            (float) x1 / 16f,
            (float) y1 / 16f,
            (float) z1 / 16f
        );
    }
}
