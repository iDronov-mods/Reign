
package net.mcreator.reignmod.block;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.world.inventory.TraderPointMenu;
import net.mcreator.reignmod.procedures.CapitalBlockCheckProcedure;

import io.netty.buffer.Unpooled;

public class TradeBlockBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public TradeBlockBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().pushReaction(PushReaction.BLOCK).isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(0, 0, 0, 16, 6, 16), box(0, 14, 0, 16, 20, 16), box(14, 6, 0, 16, 14, 16), box(0, 6, 0, 2, 14, 16), box(2, 6, 0, 14, 14, 11), box(12, 6, 15, 13, 14, 16), box(9, 6, 15, 10, 14, 16), box(6, 6, 15, 7, 14, 16),
					box(3, 6, 15, 4, 14, 16), box(1, 5, 16, 15, 6, 18), box(8, 6, 14, 10, 7, 16));
			case NORTH -> Shapes.or(box(0, 0, 0, 16, 6, 16), box(0, 14, 0, 16, 20, 16), box(0, 6, 0, 2, 14, 16), box(14, 6, 0, 16, 14, 16), box(2, 6, 5, 14, 14, 16), box(3, 6, 0, 4, 14, 1), box(6, 6, 0, 7, 14, 1), box(9, 6, 0, 10, 14, 1),
					box(12, 6, 0, 13, 14, 1), box(1, 5, -2, 15, 6, 0), box(6, 6, 0, 8, 7, 2));
			case EAST -> Shapes.or(box(0, 0, 0, 16, 6, 16), box(0, 14, 0, 16, 20, 16), box(0, 6, 0, 16, 14, 2), box(0, 6, 14, 16, 14, 16), box(0, 6, 2, 11, 14, 14), box(15, 6, 3, 16, 14, 4), box(15, 6, 6, 16, 14, 7), box(15, 6, 9, 16, 14, 10),
					box(15, 6, 12, 16, 14, 13), box(16, 5, 1, 18, 6, 15), box(14, 6, 6, 16, 7, 8));
			case WEST -> Shapes.or(box(0, 0, 0, 16, 6, 16), box(0, 14, 0, 16, 20, 16), box(0, 6, 14, 16, 14, 16), box(0, 6, 0, 16, 14, 2), box(5, 6, 2, 16, 14, 14), box(0, 6, 12, 1, 14, 13), box(0, 6, 9, 1, 14, 10), box(0, 6, 6, 1, 14, 7),
					box(0, 6, 3, 1, 14, 4), box(-2, 5, 1, 0, 6, 15), box(0, 6, 8, 2, 7, 10));
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
			return CapitalBlockCheckProcedure.execute(world);
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		if (entity instanceof ServerPlayer player) {
			NetworkHooks.openScreen(player, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Trade Block");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new TraderPointMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}
}
