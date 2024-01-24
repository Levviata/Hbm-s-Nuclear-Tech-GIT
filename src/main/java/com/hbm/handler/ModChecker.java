package com.hbm.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModChecker {
    private static final String MOD_ID_HBM = "hbm"; // Replace with the actual mod ID for HBM
    private static final String MOD_ID_CGM = "cgm"; // Replace with the actual mod ID for CGM

    public static boolean isItemHeldFromHBM() {
        // Get the currently held item by the player
        ItemStack heldItem = Minecraft.getMinecraft().player.getHeldItemMainhand();

        // Check if the item is from the HBM mod
        return isItemFromMod(heldItem, MOD_ID_HBM);
    }

    public static boolean isItemHeldFromCGM() {
        // Get the currently held item by the player
        ItemStack heldItem = Minecraft.getMinecraft().player.getHeldItemMainhand();

        // Check if the item is from the CGM mod
        return isItemFromMod(heldItem, MOD_ID_CGM);
    }

    private static boolean isItemFromMod(ItemStack stack, String modId) {
        if (!stack.isEmpty()) {
            Item item = stack.getItem();
            ResourceLocation registryName = item.getRegistryName();
            return registryName != null && registryName.getResourceDomain().equals(modId);
        }
        return false;
    }
}
