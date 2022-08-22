package net.inditorias.additionalshrooms.registries;

import net.inditorias.additionalshrooms.AdditionalShrooms;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModTags {

    public static void register(){

    }

    public static class Blocks{

        public static final TagKey<Block> MUSHROOM_BLOCKS = createCommonTag("mushroom_blocks");
        public static final TagKey<Block> MUSHROOM_SLABS = createCommonTag("mushroom_slabs");
        public static final TagKey<Block> MUSHROOM_STAIRS = createCommonTag("mushroom_stairs");


        private static TagKey<Block> createTag(String name){
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(AdditionalShrooms.MOD_ID, name));
        }

        private static TagKey<Block> createCommonTag(String name){
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }
    }

    public static class Items{
        private static TagKey<Item> createTag(String name){
            return TagKey.of(Registry.ITEM_KEY, new Identifier(AdditionalShrooms.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }
    }
}