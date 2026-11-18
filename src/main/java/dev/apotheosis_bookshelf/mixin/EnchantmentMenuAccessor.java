package dev.apotheosis_bookshelf.mixin;

import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accessor for {@link EnchantmentMenu#access}.
 */
@Mixin(EnchantmentMenu.class)
public interface EnchantmentMenuAccessor {
    @Accessor("access")
    ContainerLevelAccess getAccess();
}
