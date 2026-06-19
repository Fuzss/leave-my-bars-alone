package fuzs.leavemybarsalone.common.mixin.client;

import fuzs.leavemybarsalone.common.LeaveMyBarsAlone;
import fuzs.leavemybarsalone.common.config.ClientConfig;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Hud.class)
abstract class HudMixin {

    @ModifyVariable(method = "nextContextualInfoState", at = @At("STORE"), ordinal = 1)
    private boolean nextContextualInfoState(boolean canShowVehicleJumpInfo) {
        if (LeaveMyBarsAlone.CONFIG.get(ClientConfig.class).experienceBar) {
            return canShowVehicleJumpInfo && this.willPrioritizeJumpInfo();
        } else {
            return canShowVehicleJumpInfo;
        }
    }

    @Shadow
    private boolean willPrioritizeJumpInfo() {
        throw new RuntimeException();
    }
}
