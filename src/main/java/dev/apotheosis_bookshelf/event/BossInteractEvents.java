package dev.apotheosis_bookshelf.event;

import dev.apotheosis_bookshelf.ApotheosisBookshelfMod;
import dev.apotheosis_bookshelf.registry.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Handles right-clicking bosses with catalyst items to obtain Primal crafting materials.
 */
@Mod.EventBusSubscriber(modid = ApotheosisBookshelfMod.MOD_ID)
public class BossInteractEvents {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        ItemStack held = event.getItemStack();
        Entity target = event.getTarget();

        if (isEnderDragon(target) && held.is(Items.END_CRYSTAL)) {
            convertItemOnBoss(event, player, level, target, held, ModItems.PRIMAL_END_CRYSTAL.get());
        } else if (target instanceof WitherBoss && held.is(Items.NETHER_STAR)) {
            convertItemOnBoss(event, player, level, target, held, ModItems.PRIMAL_NETHER_STAR.get());
        }
    }

    private static boolean isEnderDragon(Entity entity) {
        return entity instanceof EnderDragon
                || (entity instanceof EnderDragonPart part && part.parentMob instanceof EnderDragon);
    }

    private static void convertItemOnBoss(
            PlayerInteractEvent.EntityInteract event,
            Player player,
            Level level,
            Entity target,
            ItemStack held,
            Item resultItem) {
        if (!level.isClientSide) {
            if (!player.getAbilities().instabuild) {
                held.shrink(1);
            }
            ItemStack result = new ItemStack(resultItem);
            if (!player.getInventory().add(result)) {
                player.drop(result, false);
            }
            level.playSound(null, target.getX(), target.getY(), target.getZ(),
                    SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 1.2F);
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.ENCHANT,
                        target.getX(), target.getY() + target.getBbHeight() * 0.5, target.getZ(),
                        24, 0.8, 0.8, 0.8, 0.5);
            }
        }
        event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
        event.setCanceled(true);
    }
}
