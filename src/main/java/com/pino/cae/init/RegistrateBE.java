package com.pino.cae.init;


import com.pino.cae.kineticBEs.CustomEncasedShaftBE;
import com.pino.cae.kineticBEs.dependencies.PublicEncasedShaftBlockCAE;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.ShaftInstance;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogInstance;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogRenderer;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

import static com.pino.cae.Cae.REGISTRATEKJ;

public class RegistrateBE {

    public static final BlockEntityEntry<KineticBlockEntity> ENCASED_SHAFT;
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COG;
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_LARGE_COG;


    public RegistrateBE() {


    }
    public static List<BlockEntry<PublicEncasedShaftBlockCAE>> initializeList() {
        return new ArrayList<>() {{
            add(BlockInit.GOLD_ENCASED_SHAFT)
                            ;
        }};
    }
    public static void register() {
    }

    static {
        ENCASED_SHAFT = REGISTRATEKJ.blockEntity("encased_shaft", KineticBlockEntity::new).instance(() -> {
            return ShaftInstance::new;
        }, false).validBlocks(BlockInit.GOLD_ENCASED_SHAFT,BlockInit.ZINC_ENCASED_SHAFT,BlockInit.ENDERIUM_ENCASED_SHAFT,BlockInit.SUPERCRITICAL_ENCASED_SHAFT).renderer(() -> {
            return ShaftRenderer::new;
        }).register();
       ENCASED_COG = REGISTRATEKJ.blockEntity("encased_cogwheel", SimpleKineticBlockEntity::new).instance(() -> {
            return EncasedCogInstance::small;
        }, false).validBlocks(BlockInit.GOLD_ENCASED_COGWHEEL,BlockInit.ZINC_ENCASED_COGWHEEL,BlockInit.ENDERIUM_ENCASED_COGWHEEL,BlockInit.SUPERCRITICAL_ENCASED_COGWHEEL).renderer(() -> {
            return EncasedCogRenderer::small;
        }).register();
        ENCASED_LARGE_COG = REGISTRATEKJ.blockEntity("encased_cogwheel_large", SimpleKineticBlockEntity::new).instance(() -> {
            return EncasedCogInstance::large;
        }, false).validBlocks(BlockInit.GOLD_ENCASED_LARGE_COGWHEEL,BlockInit.ZINC_ENCASED_LARGE_COGWHEEL,BlockInit.ENDERIUM_ENCASED_LARGE_COGWHEEL,BlockInit.SUPERCRITICAL_ENCASED_LARGE_COGWHEEL).renderer(() -> {
            return EncasedCogRenderer::large;
        }).register();
    }
}
