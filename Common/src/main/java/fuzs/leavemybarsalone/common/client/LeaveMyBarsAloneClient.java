package fuzs.leavemybarsalone.common.client;

import fuzs.leavemybarsalone.common.LeaveMyBarsAlone;
import fuzs.leavemybarsalone.common.config.ClientConfig;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.GuiLayersContext;
import fuzs.puzzleslib.common.api.client.gui.v2.ScreenHelper;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public class LeaveMyBarsAloneClient implements ClientModConstructor {
    public static final Identifier FOOD_LEVEL_GUI_LAYER = LeaveMyBarsAlone.id("food_level");

    @Override
    public void onRegisterGuiLayers(GuiLayersContext context) {
        context.registerGuiLayer(FOOD_LEVEL_GUI_LAYER,
                GuiLayersContext.VEHICLE_HEALTH,
                (GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) -> {
                    if (LeaveMyBarsAlone.CONFIG.get(ClientConfig.class).foodLevel) {
                        Hud hud = Minecraft.getInstance().gui.hud;
                        int vehicleMaxHearts = hud.getVehicleMaxHearts(hud.getPlayerVehicleWithHealth());
                        if (hud.minecraft.gameMode.canHurtPlayer() && vehicleMaxHearts > 0) {
                            Player player = hud.getCameraPlayer();
                            int posX = guiGraphics.guiWidth() / 2 + 91;
                            hud.extractFood(guiGraphics,
                                    player,
                                    guiGraphics.guiHeight()
                                            - ScreenHelper.getRightStatusBarHeight(FOOD_LEVEL_GUI_LAYER),
                                    posX);
                        }
                    }
                });
        context.addRightStatusBarHeightProvider(FOOD_LEVEL_GUI_LAYER, (Player player) -> {
            if (LeaveMyBarsAlone.CONFIG.get(ClientConfig.class).foodLevel) {
                Hud hud = Minecraft.getInstance().gui.hud;
                int vehicleMaxHearts = hud.getVehicleMaxHearts(hud.getPlayerVehicleWithHealth());
                return hud.minecraft.gameMode.canHurtPlayer() && vehicleMaxHearts > 0 ? 10 : 0;
            } else {
                return 0;
            }
        });
    }
}
