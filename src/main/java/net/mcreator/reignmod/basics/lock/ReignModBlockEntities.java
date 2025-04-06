package net.mcreator.reignmod.basics.lock;

import net.mcreator.reignmod.ReignModMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;

public class ReignModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ReignModMod.MODID);

    public static final RegistryObject<BlockEntityType<DoorLockBlockEntity>> DOOR_LOCK =
            BLOCK_ENTITIES.register("door_lock", () ->
                    BlockEntityType.Builder.of(DoorLockBlockEntity::new,
                                    getLockableBlocks().toArray(new Block[0]))
                            .build(null));

    private static Set<Block> getLockableBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> block instanceof DoorBlock
                        || block instanceof TrapDoorBlock
                        || block instanceof FenceGateBlock)
                .collect(Collectors.toSet());
    }

    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);
    }
}