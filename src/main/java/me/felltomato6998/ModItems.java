package me.felltomato6998;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

import static me.felltomato6998._Fabric_Documentation_.POISON_FOOD_COMPONENT;
import static me.felltomato6998._Fabric_Documentation_.POISON_FOOD_CONSUMABLE_COMPONENT;

public class ModItems {
    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(_Fabric_Documentation_.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
    public static final Item SUSPICIOUS_SUBSTANCE = register("suspicious_substance", Item::new, new Item.Properties());
    public static final Item POISONOUS_APPLE = register(
            "poisonous_apple",
            Item::new,
            new Item.Properties().food(POISON_FOOD_COMPONENT, POISON_FOOD_CONSUMABLE_COMPONENT)
    );


    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.accept(ModItems.SUSPICIOUS_SUBSTANCE));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((itemGroup) -> itemGroup.accept(ModItems.POISONOUS_APPLE));
    }
}
