
package net.mcreator.reignmod.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.procedures.CapitalBlockCheckProcedure;

public class MarketBlockBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public MarketBlockBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.POLISHED_DEEPSLATE).strength(2f, 10f).noOcclusion().pushReaction(PushReaction.BLOCK).isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(0, 5, 0, 16, 16, 12), box(12, 5, 12, 16, 16, 16), box(0, 5, 12, 4, 16, 16), box(4, 12, 12, 12, 16, 16), box(4, 5, 12, 12, 6, 16), box(4, 5, 16, 12, 6, 17), box(4, 11, 11, 12, 12, 16), box(4, 6, 12, 5, 11, 15),
					box(11, 6, 12, 12, 11, 15), box(0, 0, 0, 16, 5, 16));
			case NORTH -> Shapes.or(box(0, 5, 4, 16, 16, 16), box(0, 5, 0, 4, 16, 4), box(12, 5, 0, 16, 16, 4), box(4, 12, 0, 12, 16, 4), box(4, 5, 0, 12, 6, 4), box(4, 5, -1, 12, 6, 0), box(4, 11, 0, 12, 12, 5), box(11, 6, 1, 12, 11, 4),
					box(4, 6, 1, 5, 11, 4), box(0, 0, 0, 16, 5, 16));
			case EAST -> Shapes.or(box(0, 5, 0, 12, 16, 16), box(12, 5, 0, 16, 16, 4), box(12, 5, 12, 16, 16, 16), box(12, 12, 4, 16, 16, 12), box(12, 5, 4, 16, 6, 12), box(16, 5, 4, 17, 6, 12), box(11, 11, 4, 16, 12, 12), box(12, 6, 11, 15, 11, 12),
					box(12, 6, 4, 15, 11, 5), box(0, 0, 0, 16, 5, 16));
			case WEST -> Shapes.or(box(4, 5, 0, 16, 16, 16), box(0, 5, 12, 4, 16, 16), box(0, 5, 0, 4, 16, 4), box(0, 12, 4, 4, 16, 12), box(0, 5, 4, 4, 6, 12), box(-1, 5, 4, 0, 6, 12), box(0, 11, 4, 5, 12, 12), box(1, 6, 4, 4, 11, 5),
					box(1, 6, 11, 4, 11, 12), box(0, 0, 0, 16, 5, 16));
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

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		if (worldIn instanceof LevelAccessor world) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return CapitalBlockCheckProcedure.execute(world, x, z);
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}
}
