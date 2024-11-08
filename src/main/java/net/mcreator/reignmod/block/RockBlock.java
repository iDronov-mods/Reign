
package net.mcreator.reignmod.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class RockBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final EnumProperty<AttachFace> FACE = FaceAttachedHorizontalDirectionalBlock.FACE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public RockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops().noOcclusion().pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL).setValue(WATERLOGGED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
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
			default -> switch (state.getValue(FACE)) {
				case FLOOR -> Shapes.or(box(7, 0, 6, 10, 1, 10), box(7, 0, 6, 10, 1, 10), box(6, 1, 7, 10, 2, 10), box(6, 0, 7, 7, 1, 10));
				case WALL -> Shapes.or(box(7, 6, 0, 10, 10, 1), box(7, 6, 0, 10, 10, 1), box(6, 6, 1, 10, 9, 2), box(6, 6, 0, 7, 9, 1));
				case CEILING -> Shapes.or(box(6, 15, 6, 9, 16, 10), box(6, 15, 6, 9, 16, 10), box(6, 14, 7, 10, 15, 10), box(9, 15, 7, 10, 16, 10));
			};
			case NORTH -> switch (state.getValue(FACE)) {
				case FLOOR -> Shapes.or(box(6, 0, 6, 9, 1, 10), box(6, 0, 6, 9, 1, 10), box(6, 1, 6, 10, 2, 9), box(9, 0, 6, 10, 1, 9));
				case WALL -> Shapes.or(box(6, 6, 15, 9, 10, 16), box(6, 6, 15, 9, 10, 16), box(6, 6, 14, 10, 9, 15), box(9, 6, 15, 10, 9, 16));
				case CEILING -> Shapes.or(box(7, 15, 6, 10, 16, 10), box(7, 15, 6, 10, 16, 10), box(6, 14, 6, 10, 15, 9), box(6, 15, 6, 7, 16, 9));
			};
			case EAST -> switch (state.getValue(FACE)) {
				case FLOOR -> Shapes.or(box(6, 0, 6, 10, 1, 9), box(6, 0, 6, 10, 1, 9), box(7, 1, 6, 10, 2, 10), box(7, 0, 9, 10, 1, 10));
				case WALL -> Shapes.or(box(0, 6, 6, 1, 10, 9), box(0, 6, 6, 1, 10, 9), box(1, 6, 6, 2, 9, 10), box(0, 6, 9, 1, 9, 10));
				case CEILING -> Shapes.or(box(6, 15, 7, 10, 16, 10), box(6, 15, 7, 10, 16, 10), box(7, 14, 6, 10, 15, 10), box(7, 15, 6, 10, 16, 7));
			};
			case WEST -> switch (state.getValue(FACE)) {
				case FLOOR -> Shapes.or(box(6, 0, 7, 10, 1, 10), box(6, 0, 7, 10, 1, 10), box(6, 1, 6, 9, 2, 10), box(6, 0, 6, 9, 1, 7));
				case WALL -> Shapes.or(box(15, 6, 7, 16, 10, 10), box(15, 6, 7, 16, 10, 10), box(14, 6, 6, 15, 9, 10), box(15, 6, 6, 16, 9, 7));
				case CEILING -> Shapes.or(box(6, 15, 6, 10, 16, 9), box(6, 15, 6, 10, 16, 9), box(6, 14, 6, 9, 15, 10), box(6, 15, 9, 9, 16, 10));
			};
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, FACE, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		if (context.getClickedFace().getAxis() == Direction.Axis.Y)
			return super.getStateForPlacement(context).setValue(FACE, context.getClickedFace().getOpposite() == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR).setValue(FACING, context.getHorizontalDirection()).setValue(WATERLOGGED, flag);
		return super.getStateForPlacement(context).setValue(FACE, AttachFace.WALL).setValue(FACING, context.getClickedFace()).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return context.getItemInHand().getItem() != this.asItem();
	}
}
