
package net.mcreator.reignmod.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class FinialOfMightBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public FinialOfMightBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(4, 2, 4, 12, 4, 12), box(6, 4, 7, 10, 9, 9), box(4, 9, 6, 12, 11, 10), box(12, 9, 6, 14, 11, 10), box(3, 8, 6, 5, 10, 10), box(7, 11, 7, 9, 17, 9), box(1, 0, 1, 15, 2, 15));
			case NORTH -> Shapes.or(box(4, 2, 4, 12, 4, 12), box(6, 4, 7, 10, 9, 9), box(4, 9, 6, 12, 11, 10), box(2, 9, 6, 4, 11, 10), box(11, 8, 6, 13, 10, 10), box(7, 11, 7, 9, 17, 9), box(1, 0, 1, 15, 2, 15));
			case EAST -> Shapes.or(box(4, 2, 4, 12, 4, 12), box(7, 4, 6, 9, 9, 10), box(6, 9, 4, 10, 11, 12), box(6, 9, 2, 10, 11, 4), box(6, 8, 11, 10, 10, 13), box(7, 11, 7, 9, 17, 9), box(1, 0, 1, 15, 2, 15));
			case WEST -> Shapes.or(box(4, 2, 4, 12, 4, 12), box(7, 4, 6, 9, 9, 10), box(6, 9, 4, 10, 11, 12), box(6, 9, 12, 10, 11, 14), box(6, 8, 3, 10, 10, 5), box(7, 11, 7, 9, 17, 9), box(1, 0, 1, 15, 2, 15));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
}
