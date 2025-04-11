package net.mcreator.reignmod.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.init.ReignModModBlocks;
import net.mcreator.reignmod.claim.capital.CapitalClaimManager;
import net.mcreator.reignmod.ReignModMod;

public class CapitalCreateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ItemStack crown = ItemStack.EMPTY;
		if (!ReignModModVariables.MapVariables.get(world).CapitalHave) {
			ReignModModVariables.MapVariables.get(world).CAPITAL_X = Math.floor(entity.getX());
			ReignModModVariables.MapVariables.get(world).syncData(world);
			ReignModModVariables.MapVariables.get(world).CAPITAL_Y = Math.floor(entity.getY());
			ReignModModVariables.MapVariables.get(world).syncData(world);
			ReignModModVariables.MapVariables.get(world).CAPITAL_Z = Math.floor(entity.getZ());
			ReignModModVariables.MapVariables.get(world).syncData(world);
			KingdomData.setFundCoordinates((int) x, (int) y, (int) z);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z),
					ReignModModBlocks.FUND.get().defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
					Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
					Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
					Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
					Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 0),
					Blocks.BEDROCK.defaultBlockState(), 3);
			ReignModMod.queueServerWork(10, () -> {
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
						Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
						Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
						Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
						Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
			});
			ReignModMod.queueServerWork(10, () -> {
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
						ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
						ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
						ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
						ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
			});
			ReignModModVariables.MapVariables.get(world).CapitalHave = true;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "setworldspawn");
			CapitalClaimManager.setCapitalCenter((int) Math.floor(x), (int) Math.floor(z));
			CapitalClaimManager.enable();
			CreateCrownProcedure.execute(world);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("translation.key.capital_exists").getString() + " x: " + ReignModModVariables.MapVariables.get(world).CAPITAL_X + " y: "
						+ ReignModModVariables.MapVariables.get(world).CAPITAL_Y + " z: " + ReignModModVariables.MapVariables.get(world).CAPITAL_Z)), false);
		}
	}
}
