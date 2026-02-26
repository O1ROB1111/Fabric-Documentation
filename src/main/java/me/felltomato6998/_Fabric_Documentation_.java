package me.felltomato6998;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _Fabric_Documentation_ implements ModInitializer {
	public static final String MOD_ID = "ffabric_ddocumentation";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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