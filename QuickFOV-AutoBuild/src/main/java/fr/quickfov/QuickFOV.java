package fr.quickfov;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = QuickFOV.MODID, name = QuickFOV.NAME, version = QuickFOV.VERSION, clientSideOnly = true)
public class QuickFOV {
    public static final String MODID = "quickfov";
    public static final String NAME = "QuickFOV";
    public static final String VERSION = "1.0.0";

    private static final float QUAKE_PRO_FOV = 110.0F;
    private static KeyBinding toggleKey;
    private static boolean quakeMode = false;
    private static float previousFov = 70.0F;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        toggleKey = new KeyBinding("key.quickfov.toggle", Keyboard.KEY_G, "key.categories.quickfov");
        ClientRegistry.registerKeyBinding(toggleKey);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (toggleKey != null && toggleKey.isPressed()) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.thePlayer == null || mc.gameSettings == null) return;

            if (!quakeMode) {
                previousFov = mc.gameSettings.fovSetting;
                mc.gameSettings.fovSetting = QUAKE_PRO_FOV;
                quakeMode = true;
            } else {
                mc.gameSettings.fovSetting = previousFov;
                quakeMode = false;
            }
        }
    }
}
