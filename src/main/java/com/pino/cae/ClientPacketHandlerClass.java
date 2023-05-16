package com.pino.cae;

import com.mojang.logging.LogUtils;
import net.minecraftforge.network.NetworkEvent;
import org.slf4j.Logger;

import java.util.function.Supplier;

public class ClientPacketHandlerClass {
    static Logger LOGGER = LogUtils.getLogger();
    public static void handlePacket(S2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        // Do stuff
        ctx.get().enqueueWork(
        () -> LOGGER.debug("amongus"));
    }
}
