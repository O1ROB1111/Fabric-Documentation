package me.felltomato6998;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _Fabric_Documentation_ implements ModInitializer {
	public static final String MOD_ID = "ffabric_ddocumentation";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Consumable POISON_FOOD_CONSUMABLE_COMPONENT = Consumables.defaultFood()
			// The duration is in ticks, 20 ticks = 1 second
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 6 * 20, 1), 1.0f))
			.build();
	public static final FoodProperties POISON_FOOD_COMPONENT = new FoodProperties.Builder()
			.alwaysEdible()
			.build();

	@Override
	public void onInitialize() {
		ModItems.initialize();
		/* SUSPICIOUS_SUBSTANCE */
		// Add the suspicious substance to the composting registry with a 30% chance of increasing the composter's level.
		CompostingChanceRegistry.INSTANCE.add(ModItems.SUSPICIOUS_SUBSTANCE, 0.3f);
		// Add the suspicious substance to the registry of fuels, with a burn time of 30 seconds.
		// Remember, Minecraft deals with logical based-time using ticks.
		// 20 ticks = 1 second.
		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.SUSPICIOUS_SUBSTANCE, 30 * 20);
		});

		LOGGER.info("Hello Fabric world!");
	}
}