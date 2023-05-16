package com.pino.cae;

import com.mojang.serialization.Decoder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class ModidPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel net = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("cae", "source"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );


    private static int id = 0;
    private static int id() {
        return id++;
    }

    public static void register(){
        /*net.messageBuilder(S2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
            .decoder(S2CPacket::new)
            .encoder(S2CPacket::encoder)
            .consumer(S2CPacket::handle)
            .add();
           */


    net.registerMessage(id(), S2CPacket.class, S2CPacket::encoder, S2CPacket::decoder, S2CPacket::messageConsumer);
    }


    public static <MSG> void sendToServer(MSG message) {
        net.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, LevelChunk chunk) {
        net.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), message);
    }



}

