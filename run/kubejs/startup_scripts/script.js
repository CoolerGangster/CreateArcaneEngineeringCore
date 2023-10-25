onEvent("create.spout.special", event => {
    //Creates a handler for spout, the id is required as there's no way to generate a consistent uuid here.
    //
    //Spout will call the handler with simulate = true for every tick, if the returned value > 0, then
    //the spout will start its animation, handler will be called with simulate = false again at the end of
    //the animation.
    //
    //The returned integer is how much fluid should this operation consume.

    let spoutInteraction = (blockId, fluidId, fluidAmount, result, requiredAmount) => {
    event.addSpoutHandler(blockId + "_spout_interaction", blockId, (block, fluid, simulate) => {
        /*
        blockId = The block required below the spout
        fluidId = The fluid to be used
        fluidAmount = The amount of fluid consumed in an interaction and the amount needed to start an interaction if requiredAmount is left undefined. 0 wont work, even if requiredAmount is defined.
        result = The block that is placed instead of the old block. A random tick will be called if left undefined.
        requiredAmount = the amount of fluid required to start an interaction. 0 doesnt work.
        */
        if (!requiredAmount)
        requiredAmount = fluidAmount
        if (fluid.id == fluidId && fluid.amount >= requiredAmount) {
            if (!simulate)
                if (result)
                block.setBlockState(Block.getBlock(result).defaultBlockState(), 2);
                else
                block.blockState.randomTick(block.level.minecraftLevel, block.pos, block.level.minecraftLevel.random)
            return fluidAmount;
        }
        return 0;
        });
    }
    spoutInteraction("minecraft:wheat", "minecraft:water", 100)
    let corundum = ["red", "orange", "yellow", "green", "blue", "indigo", "violet", "white", "black"]
    corundum.forEach(Color => {
        spoutInteraction("quark:" + Color + "_corundum", "minecraft:water", 100)
    });


})

onEvent('fluid.registry', event => {
    event.create('sky_solution')
    .thickTexture(0x000033)
    .bucketColor(0x000033)
    .displayName('Volatile Sky Solution')

    event.create('superheated_anthracene')
    .thickTexture(0xFFFFF)
    .bucketColor(0xFFFFF)
    .displayName('Superheated Anthracene')
    .noBucket()
    .noBlock()
    .stillTexture('tconstruct:block/fluid/molten/still')
    .flowingTexture('tconstruct:block/fluid/molten/flowing')


    event.create('refined_oil') //for pneumaticraft fuels
    .thinTexture(0x090909)
    .bucketColor(0x090909)
    .displayName('Refined Oil')

    event.create('dichromic_acid')
    .thinTexture(0x800020)
    .bucketColor(0x800020)
    .displayName('Dichromic Acid')

    event.create('chromatic_waste')
    .thinTexture(0x3A4100)
    .bucketColor(0x3A4100)
    .displayName('Chromatic Waste')

    event.create('sulfuric_acid')
    .thinTexture(0x0AFFFFFF)
    .bucketColor(0x0AFFFFFF)
    .displayName('Sulfuric Acid')

    event.create('invar_slurry')
    .thickTexture(0x333300)
    .bucketColor(0x333300)
    .displayName('Invar Slurry')

    event.create('liquid_anthraquinone')
    .thinTexture(0x9F2B68)
    .bucketColor(0x9F2B68)
    .displayName('Anthraquinone')

    event.create('green_anthraquinone')
    .thinTexture(0x03732F)
    .bucketColor(0x03732F)
    .displayName('Liquid Green Dye')

    event.create('portal_actuation_fluid')
    .thinTexture(0x03732F)
    .bucketColor(0x03732F)
    .displayName('Portal Actuation Fluid')

    event.create('god_actuation_fluid')
    .thinTexture(0x0AFFFFFF)
    .bucketColor(0x0AFFFFFF)
    .displayName('God Actuation Fluid')

    event.create('aureal_essence')
    .thinTexture(0xFFFFFF)
    .bucketColor(0xFFFFFF)
    .displayName('Aureal Essence')

    event.create('liquid_egg')
    .thinTexture(0xF0E5C2)
    .bucketColor(0xF0E5C2)
    event.create('liquid_omlett')
    .thinTexture(0xDAD37F)
    .bucketColor(0xDAD37F)



    function SourceFluids(Name,id,bucket) {
        event.create(id)
        .displayName(Name)
        .stillTexture('kubejs:block/'+ id + "_still")
        .flowingTexture('kubejs:block/'+ id +'_flow')
    }

    const Source = [
        ['Sourceberry Juice','sourceberry_juice'],
        ['Magebloom Juice','magebloom_juice'],
        ['Magebloom Sourceberry Smoothie','magebloom_sourceberry_smoothie'],
        ['Impure Source','impure_source'],
        ['Source','source']

    ]
    Source.forEach(element => {
        SourceFluids(element[0],element[1])

    });

    function Essence(Name,id,color) {
        event.create(id + "_essence")
        .displayName(Name+ " Essence")
        .thinTexture(color)
        .stillTexture('tconstruct:block/fluid/molten/still')
        .flowingTexture('tconstruct:block/fluid/molten/flowing')
        .luminosity(8)
    }
    Essence("Source","source",0x9F2B68)
    Essence("Soul","soul",0x000099)
    Essence("Nature","nature",0x009933)
    Essence("Dry","dry",0xffcc00)
    Essence("Ice","ice",0x00ccff)


    function juice(id,color) {
        event.create(id + "_juice")
        .thinTexture(color)
        .stillTexture('tconstruct:block/fluid/molten/still')
        .flowingTexture('tconstruct:block/fluid/molten/flowing')
        .luminosity(8)
    }

    juice("vexing",0x9F2B68)
    juice("cascading",0x000099)
    juice("flourishing",0x009933)
    juice("blazing",0x800020)

    event.create('inertia_essence')
    .thinTexture(0x009933)
    .bucketColor(0x009933)


    event.create("cae:lst")
    .displayName("Liquid Space Time")
    .stillTexture('cae:block/lst_still')
    .flowingTexture('cae:block/lst_flow')

    event.create('liquid_resent')
    .stillTexture('kubejs:block/liquid_resent')
    .displayName('Liquidified Resent')




});