package net.inditorias.additionalshrooms.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.inditorias.additionalshrooms.AdditionalShrooms;
import net.inditorias.additionalshrooms.blocks.ModStairsBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {


    public static Block MUSHROOM_BLOCK_INSIDES = registerBlock("mushroom_block_insides", new Block(FabricBlockSettings.copy(Blocks.BROWN_MUSHROOM_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    
    public static Block BROWN_MUSHROOM_SLAB = registerBlock("brown_mushroom_block_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.BROWN_MUSHROOM_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    public static Block RED_MUSHROOM_SLAB = registerBlock("red_mushroom_block_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.RED_MUSHROOM_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    public static Block MUSHROOM_STEM_SLAB = registerBlock("mushroom_stem_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.MUSHROOM_STEM)), ItemGroup.BUILDING_BLOCKS);
    public static Block MUSHROOM_BLOCK_INSIDES_SLAB = registerBlock("mushroom_block_insides_slab", new SlabBlock(FabricBlockSettings.copy(ModBlocks.MUSHROOM_BLOCK_INSIDES)), ItemGroup.BUILDING_BLOCKS);
    public static Block NETHER_WART_BLOCK_SLAB = registerBlock("nether_wart_block_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    public static Block WARPED_WART_BLOCK_SLAB = registerBlock("warped_wart_block_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.WARPED_WART_BLOCK)), ItemGroup.BUILDING_BLOCKS);

    public static Block BROWN_MUSHROOM_STAIR = registerBlock("brown_mushroom_block_stair", new ModStairsBlock(Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState(),
            FabricBlockSettings.copy(Blocks.BROWN_MUSHROOM_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    public static Block RED_MUSHROOM_STAIR = registerBlock("red_mushroom_block_stair", new ModStairsBlock(Blocks.RED_MUSHROOM_BLOCK.getDefaultState(),
            FabricBlockSettings.copy(Blocks.RED_MUSHROOM_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    public static Block MUSHROOM_STEM_STAIR = registerBlock("mushroom_stem_stair", new ModStairsBlock(Blocks.MUSHROOM_STEM.getDefaultState(),
            FabricBlockSettings.copy(Blocks.MUSHROOM_STEM)), ItemGroup.BUILDING_BLOCKS);
    public static Block MUSHROOM_BLOCK_INSIDES_STAIR = registerBlock("mushroom_block_insides_stair", new ModStairsBlock(MUSHROOM_BLOCK_INSIDES.getDefaultState(),
            FabricBlockSettings.copy(ModBlocks.MUSHROOM_BLOCK_INSIDES)), ItemGroup.BUILDING_BLOCKS);
    public static Block NETHER_WART_BLOCK_STAIR = registerBlock("nether_wart_block_stair", new ModStairsBlock(Blocks.NETHER_WART_BLOCK.getDefaultState(),
            FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    public static Block WARPED_WART_BLOCK_STAIR = registerBlock("warped_wart_block_stair", new ModStairsBlock(Blocks.WARPED_WART_BLOCK.getDefaultState(),
            FabricBlockSettings.copy(Blocks.WARPED_WART_BLOCK)), ItemGroup.BUILDING_BLOCKS);


    private static Block registerBlock(String name, Block block, ItemGroup tab){
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(AdditionalShrooms.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab){
        return Registry.register(Registry.ITEM, new Identifier(AdditionalShrooms.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerBlocks(){

    }
}
