package net.mcreator.reignmod.basics.lock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.mcreator.reignmod.basics.lock.ReignModBlockEntities;

public class DoorLockBlockEntity extends BlockEntity implements IDoorLockHandler {

    private String owner = "";
    private String ownerName = "";
    private String lockType = "";

    public DoorLockBlockEntity(BlockPos pos, BlockState state) {
        super(ReignModBlockEntities.DOOR_LOCK.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.owner = tag.getString("Owner");
        this.ownerName = tag.getString("OwnerName");
        this.lockType = tag.getString("LockType");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("Owner", this.owner);
        tag.putString("OwnerName", this.ownerName);
        tag.putString("LockType", this.lockType);
    }

    @Override public String reign$getOwner() { return owner; }
    @Override public void reign$setOwner(String owner) { this.owner = owner; }

    @Override public String reign$getOwnerName() { return ownerName; }
    @Override public void reign$setOwnerName(String name) { this.ownerName = name; }

    @Override public String reign$getLockType() { return lockType; }
    @Override public void reign$setLockType(String type) { this.lockType = type; }

    public void reign$setLockData(String owner, String ownerName, String lockType) {
        this.owner = owner;
        this.ownerName = ownerName;
        this.lockType = lockType;
    }
}
