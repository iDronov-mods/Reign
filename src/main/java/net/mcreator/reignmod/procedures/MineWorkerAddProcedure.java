package net.mcreator.reignmod.procedures;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.world.inventory.MineUIMenu;
import net.mcreator.reignmod.mines.MineManager;

import io.netty.buffer.Unpooled;

public class MineWorkerAddProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double hitX, double hitY, double hitZ) {
		if (entity == null)
			return;
		double x_ = 0;
		double y_ = 0;
		double z_ = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof PickaxeItem) {
			if (world instanceof ServerLevel _origLevel) {
				LevelAccessor _worldorig = world;
				world = _origLevel.getServer().getLevel(Level.OVERWORLD);
				if (world != null) {
					x_ = hitX;
					y_ = hitY;
					z_ = hitZ;
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("" + x_)), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("" + y_)), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("" + z_)), false);
					var result = MineManager.addWorker((ServerPlayer) entity, new ChunkPos(new BlockPos((int) x_, (int) y_, (int) z_)));
					if (result == MineManager.AddWorkerResult.SUCCESS) {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_worker_add_success").getString())), true);
					} else if (result == MineManager.AddWorkerResult.NOT_SERF) {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_worker_add_not_serf").getString())), true);
					} else if (result == MineManager.AddWorkerResult.NOT_ON_DOMAIN) {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_worker_add_not_on_domain").getString())), true);
					} else if (result == MineManager.AddWorkerResult.ALREADY_WORKING) {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_worker_add_already_working").getString())), true);
					} else if (result == MineManager.AddWorkerResult.TOO_MANY_WORKERS) {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_worker_add_too_many_workers").getString())), true);
					} else if (result == MineManager.AddWorkerResult.MINE_NOT_FOUND) {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_worker_add_mine_exhausted").getString())), true);
					}
				}
				world = _worldorig;
			}
		} else {
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("MineUI");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new MineUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
