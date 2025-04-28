package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.house.HouseManager;

import java.util.UUID;
import java.util.HashMap;

public class SetTrustIndirectVassalsTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		String UUID = "";
		if ((new Object() {
			public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getBoolean(tag);
				return false;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "trust_indirect_vassals")) != (guistate.containsKey("checkboxin:trusting_indirect_vassals") && ((String) guistate.get("checkboxin:trusting_indirect_vassals")).equals("true") ? true : false)) {
			if (world instanceof ServerLevel _origLevel) {
				LevelAccessor _worldorig = world;
				world = _origLevel.getServer().getLevel(Level.OVERWORLD);
				if (world != null) {
					UUID = entity.getStringUUID();
					if (guistate.containsKey("checkboxin:trusting_indirect_vassals") && ((String) guistate.get("checkboxin:trusting_indirect_vassals")).equals("true") ? true : false) {
						HouseManager.getHouseByLordUUID(UUID).toggleOnIndirectVassalsTrust();
					} else {
						HouseManager.getHouseByLordUUID(UUID).toggleOffIndirectVassalsTrust();
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putBoolean("trust_indirect_vassals",
									(guistate.containsKey("checkboxin:trusting_indirect_vassals") && ((String) guistate.get("checkboxin:trusting_indirect_vassals")).equals("true") ? true : false));
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				world = _worldorig;
			}
		}
	}
}
