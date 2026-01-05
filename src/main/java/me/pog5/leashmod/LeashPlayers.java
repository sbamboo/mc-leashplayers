package me.pog5.leashmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRuleCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LeashPlayers implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("leashmod");

    private static final Identifier RULE_ENABLED_ID = Identifier.of("leashmod", "enabled");
    private static final Identifier RULE_DISTANCE_MIN_ID = Identifier.of("leashmod", "distance_min");
    private static final Identifier RULE_DISTANCE_MAX_ID = Identifier.of("leashmod", "distance_max");
    private static final Identifier RULE_ALLOW_REMOVE_ID = Identifier.of("leashmod", "allow_remove");

    public static final GameRule<Boolean> RULE_ENABLED = GameRuleBuilder
        .forBoolean(true)
        .category(GameRuleCategory.PLAYER)
        .buildAndRegister(RULE_ENABLED_ID);

    public static final GameRule<Double> RULE_DISTANCE_MIN = GameRuleBuilder
        .forDouble(4.0D)
        .category(GameRuleCategory.PLAYER)
        .buildAndRegister(RULE_DISTANCE_MIN_ID);

    public static final GameRule<Double> RULE_DISTANCE_MAX = GameRuleBuilder
        .forDouble(10.0D)
        .category(GameRuleCategory.PLAYER)
        .buildAndRegister(RULE_DISTANCE_MAX_ID);

    public static final GameRule<Boolean> RULE_ALLOW_REMOVE = GameRuleBuilder
        .forBoolean(false)
        .category(GameRuleCategory.PLAYER)
        .buildAndRegister(RULE_ALLOW_REMOVE_ID);


    public static LeashSettings getSettings(ServerWorld world) {
        return new LeashSettings() {
            @Override
            public boolean isEnabled() {
                return world.getGameRules().getValue(RULE_ENABLED);
            }

            @Override
            public double getDistanceMin() {
                return world.getGameRules().getValue(RULE_DISTANCE_MIN);
            }

            @Override
            public double getDistanceMax() {
                return world.getGameRules().getValue(RULE_DISTANCE_MAX);
            }

            @Override
            public boolean allowLeashedRemoveFenceKnot() {
                return world.getGameRules().getValue(RULE_ALLOW_REMOVE);
            }
        };
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized LeashPlayers (1.21.11 GameRuleBuilder API)");
    }
}
