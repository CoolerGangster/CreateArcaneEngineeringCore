package com.pino.cae.sounds;

import com.pino.cae.Cae;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class soundinit {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Cae.MOD_ID);

    public static final RegistryObject<SoundEvent> ARCANE_OBELISK_CREATION =
            registerSoundEvent("arcane_obelisk_creation");
    public static final RegistryObject<SoundEvent> WRAITH_SOULCONVERT =
            registerSoundEvent("wraith_soulconvert");
    public static final RegistryObject<SoundEvent> OBELISK_PLACE =
            registerSoundEvent("obelisk_place");
    public static final RegistryObject<SoundEvent> OBELISK_BREAK =
            registerSoundEvent("obelisk_break");
    public static final RegistryObject<SoundEvent> DEVELOPER =
            registerSoundEvent("developer");
    public static final RegistryObject<SoundEvent> FORGE_START =
            registerSoundEvent("forge_start");
    public static final RegistryObject<SoundEvent> FORGE_CREATE =
            registerSoundEvent("forge_create");
    public static final RegistryObject<SoundEvent> SKELETON_CONVERT =
            registerSoundEvent("skeleton_convert");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name,() -> new SoundEvent(new ResourceLocation(Cae.MOD_ID,name)));
    }
    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }


}
