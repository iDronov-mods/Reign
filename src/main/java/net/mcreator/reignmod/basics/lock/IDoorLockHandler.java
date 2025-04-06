package net.mcreator.reignmod.basics.lock;

public interface IDoorLockHandler {
    String reign$getOwner();
    void reign$setOwner(String owner);

    String reign$getOwnerName();
    void reign$setOwnerName(String ownerName);

    String reign$getLockType();
    void reign$setLockType(String lockType);


    void reign$setLockData(String stringUUID, String string, String lockType);
}
