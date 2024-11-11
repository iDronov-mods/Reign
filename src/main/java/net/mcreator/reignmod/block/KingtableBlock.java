
package net.mcreator.reignmod.block;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Containers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.world.inventory.KingtableUIMenu;
import net.mcreator.reignmod.block.entity.KingtableBlockEntity;

import io.netty.buffer.Unpooled;

public class KingtableBlock extends Block implements EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public KingtableBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(13, 3, 13, 15, 14, 15), box(13, 3, 1, 15, 14, 3), box(1, 3, 13, 3, 14, 15), box(1, 3, 1, 3, 14, 3), box(3, 13, 1, 13, 14, 15), box(1, 13, 3, 3, 14, 13), box(13, 13, 3, 15, 14, 13), box(0, 8, 6, 1, 9, 9),
					box(0, 9, 0, 1, 10, 4), box(0, 8, 1, 1, 9, 2), box(0, 9, 12, 1, 10, 16), box(0, 8, 14, 1, 9, 16), box(1, 9, 15, 4, 10, 16), box(4, 9, 15, 7, 10, 16), box(1, 8, 15, 3, 9, 16), box(9, 9, 0, 12, 10, 1), box(12, 8, 0, 15, 9, 1),
					box(12, 9, 15, 15, 10, 16), box(14, 8, 15, 16, 9, 16), box(12, 9, 0, 15, 10, 1), box(1, 9, 0, 4, 10, 1), box(0, 8, 0, 3, 9, 1), box(0, 10, 0, 1, 14, 16), box(1, 10, 15, 15, 14, 16), box(1, 10, 0, 15, 14, 1),
					box(1, 0, 13, 3, 3, 15), box(13, 0, 13, 15, 3, 15), box(13, 0, 1, 15, 3, 3), box(1, 0, 1, 3, 3, 3));
			case NORTH -> Shapes.or(box(1, 3, 1, 3, 14, 3), box(1, 3, 13, 3, 14, 15), box(13, 3, 1, 15, 14, 3), box(13, 3, 13, 15, 14, 15), box(3, 13, 1, 13, 14, 15), box(13, 13, 3, 15, 14, 13), box(1, 13, 3, 3, 14, 13), box(15, 8, 7, 16, 9, 10),
					box(15, 9, 12, 16, 10, 16), box(15, 8, 14, 16, 9, 15), box(15, 9, 0, 16, 10, 4), box(15, 8, 0, 16, 9, 2), box(12, 9, 0, 15, 10, 1), box(9, 9, 0, 12, 10, 1), box(13, 8, 0, 15, 9, 1), box(4, 9, 15, 7, 10, 16),
					box(1, 8, 15, 4, 9, 16), box(1, 9, 0, 4, 10, 1), box(0, 8, 0, 2, 9, 1), box(1, 9, 15, 4, 10, 16), box(12, 9, 15, 15, 10, 16), box(13, 8, 15, 16, 9, 16), box(15, 10, 0, 16, 14, 16), box(1, 10, 0, 15, 14, 1),
					box(1, 10, 15, 15, 14, 16), box(13, 0, 1, 15, 3, 3), box(1, 0, 1, 3, 3, 3), box(1, 0, 13, 3, 3, 15), box(13, 0, 13, 15, 3, 15));
			case EAST -> Shapes.or(box(13, 3, 1, 15, 14, 3), box(1, 3, 1, 3, 14, 3), box(13, 3, 13, 15, 14, 15), box(1, 3, 13, 3, 14, 15), box(1, 13, 3, 15, 14, 13), box(3, 13, 13, 13, 14, 15), box(3, 13, 1, 13, 14, 3), box(6, 8, 15, 9, 9, 16),
					box(0, 9, 15, 4, 10, 16), box(1, 8, 15, 2, 9, 16), box(12, 9, 15, 16, 10, 16), box(14, 8, 15, 16, 9, 16), box(15, 9, 12, 16, 10, 15), box(15, 9, 9, 16, 10, 12), box(15, 8, 13, 16, 9, 15), box(0, 9, 4, 1, 10, 7),
					box(0, 8, 1, 1, 9, 4), box(15, 9, 1, 16, 10, 4), box(15, 8, 0, 16, 9, 2), box(0, 9, 1, 1, 10, 4), box(0, 9, 12, 1, 10, 15), box(0, 8, 13, 1, 9, 16), box(0, 10, 15, 16, 14, 16), box(15, 10, 1, 16, 14, 15), box(0, 10, 1, 1, 14, 15),
					box(13, 0, 13, 15, 3, 15), box(13, 0, 1, 15, 3, 3), box(1, 0, 1, 3, 3, 3), box(1, 0, 13, 3, 3, 15));
			case WEST -> Shapes.or(box(1, 3, 13, 3, 14, 15), box(13, 3, 13, 15, 14, 15), box(1, 3, 1, 3, 14, 3), box(13, 3, 1, 15, 14, 3), box(1, 13, 3, 15, 14, 13), box(3, 13, 1, 13, 14, 3), box(3, 13, 13, 13, 14, 15), box(7, 8, 0, 10, 9, 1),
					box(12, 9, 0, 16, 10, 1), box(14, 8, 0, 15, 9, 1), box(0, 9, 0, 4, 10, 1), box(0, 8, 0, 2, 9, 1), box(0, 9, 1, 1, 10, 4), box(0, 9, 4, 1, 10, 7), box(0, 8, 1, 1, 9, 3), box(15, 9, 9, 16, 10, 12), box(15, 8, 12, 16, 9, 15),
					box(0, 9, 12, 1, 10, 15), box(0, 8, 14, 1, 9, 16), box(15, 9, 12, 16, 10, 15), box(15, 9, 1, 16, 10, 4), box(15, 8, 0, 16, 9, 3), box(0, 10, 0, 16, 14, 1), box(0, 10, 1, 1, 14, 15), box(15, 10, 1, 16, 14, 15),
					box(1, 0, 1, 3, 3, 3), box(1, 0, 13, 3, 3, 15), box(13, 0, 13, 15, 3, 15), box(13, 0, 1, 15, 3, 3));
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
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		if (entity instanceof ServerPlayer player) {
			NetworkHooks.openScreen(player, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("King table");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new KingtableUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new KingtableBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof KingtableBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof KingtableBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}
