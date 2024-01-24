package com.hbm.handler;

import com.mrcrayfish.guns.client.KeyBinds;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import static com.hbm.handler.HbmKeybinds.reloadKey;
import static com.hbm.handler.ModChecker.isItemHeldFromHBM;
import static com.mrcrayfish.guns.client.event.ReloadHandler.setReloading;
@Deprecated
public class KeybindFixer {
    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        // Check if the held item is from the "HBM" mod
        if (isItemHeldFromHBM()) {
            // If the held item is from HBM, cancel the Guns' reload keybind
            if (KeyBinds.KEY_RELOAD.isPressed()) {
                setReloading(false);
            }
        } else {
            // If the held item is not from HBM, cancel HBM's reload keybind
            if (reloadKey.isPressed()) {
                setReloading(true);
            }
        }
    }
}
