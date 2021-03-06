package com.InfinityRaider.AgriCraft.init;

import com.InfinityRaider.AgriCraft.blocks.BlockModPlant;
import com.InfinityRaider.AgriCraft.handler.ConfigurationHandler;
import com.InfinityRaider.AgriCraft.reference.Names;
import com.InfinityRaider.AgriCraft.utility.LogHelper;
import com.InfinityRaider.AgriCraft.utility.OreDictHelper;
import com.InfinityRaider.AgriCraft.utility.RegisterHelper;
import net.minecraft.block.Block;

public class ResourceCrops {
    public static BlockModPlant diamahlia;
    public static BlockModPlant ferranium;
    public static BlockModPlant aurigold;
    public static BlockModPlant lapender;
    public static BlockModPlant emeryllis;
    public static BlockModPlant redstodendron;
    public static BlockModPlant cuprosia;
    public static BlockModPlant petinia;
    public static BlockModPlant plombean;
    public static BlockModPlant silverweed;
    public static BlockModPlant jaslumine;
    public static BlockModPlant niccissus;
    public static BlockModPlant platiolus;
    public static BlockModPlant osmonium;

    public static void init() {
        if (ConfigurationHandler.resourcePlants) {
            Block farmland = net.minecraft.init.Blocks.farmland;

            OreDictHelper.getRegisteredOres();
            Items.initFruits();

            //Vanilla resources
            diamahlia = new BlockModPlant(farmland, net.minecraft.init.Blocks.diamond_ore, Items.nuggetDiamond, Items.nuggetDiamondMeta, 5, 6);
            ferranium = new BlockModPlant(farmland, net.minecraft.init.Blocks.iron_ore, Items.nuggetIron, Items.nuggetIronMeta, 4, 1);
            aurigold = new BlockModPlant(farmland, net.minecraft.init.Blocks.gold_ore, net.minecraft.init.Items.gold_nugget, 0, 4, 6);
            lapender = new BlockModPlant(farmland, net.minecraft.init.Blocks.lapis_ore, net.minecraft.init.Items.dye, 4, 3, 6);
            emeryllis = new BlockModPlant(farmland, net.minecraft.init.Blocks.emerald_ore, Items.nuggetEmerald, Items.nuggetEmeraldMeta, 5, 6);
            redstodendron = new BlockModPlant(farmland, net.minecraft.init.Blocks.redstone_ore, net.minecraft.init.Items.redstone, 0, 3, 6);

            RegisterHelper.registerBlock(diamahlia, Names.Crops.cropDiamahlia);
            RegisterHelper.registerBlock(ferranium, Names.Crops.cropFerranium);
            RegisterHelper.registerBlock(aurigold, Names.Crops.cropAurigold);
            RegisterHelper.registerBlock(lapender, Names.Crops.cropLapender);
            RegisterHelper.registerBlock(emeryllis, Names.Crops.cropEmeryllis);
            RegisterHelper.registerBlock(redstodendron, Names.Crops.cropRedstodendron);

            //Modded resources
            if(OreDictHelper.oreCopper!=null) {
                cuprosia = new BlockModPlant(farmland, OreDictHelper.oreCopper, OreDictHelper.oreCopperMeta, Items.nuggetCopper, Items.nuggetCopperMeta, 3, 6);
                RegisterHelper.registerBlock(cuprosia, Names.Crops.cropCuprosia);
            }
            if(OreDictHelper.oreTin!=null) {
                petinia = new BlockModPlant(farmland, OreDictHelper.oreTin, OreDictHelper.oreTinMeta, Items.nuggetTin, Items.nuggetTinMeta, 3, 6);
                RegisterHelper.registerBlock(petinia, Names.Crops.cropPetinia);
            }
            if(OreDictHelper.oreLead!=null) {
                plombean = new BlockModPlant(farmland, OreDictHelper.oreLead, OreDictHelper.oreLeadMeta, Items.nuggetLead, Items.nuggetLeadMeta, 4, 6);
                RegisterHelper.registerBlock(plombean, Names.Crops.cropPlombean);
            }
            if(OreDictHelper.oreSilver!=null) {
                silverweed = new BlockModPlant(farmland, OreDictHelper.oreSilver, OreDictHelper.oreSilverMeta, Items.nuggetSilver, Items.nuggetSilverMeta, 4, 6);
                RegisterHelper.registerBlock(silverweed, Names.Crops.cropSilverweed);
            }
            if(OreDictHelper.oreAluminum!=null) {
                jaslumine = new BlockModPlant(farmland, OreDictHelper.oreAluminum, OreDictHelper.oreAluminumMeta, Items.nuggetAluminum, Items.nuggetAluminumMeta, 4, 1);
                RegisterHelper.registerBlock(jaslumine, Names.Crops.cropJaslumine);
            }
            if(OreDictHelper.oreNickel!=null) {
                niccissus = new BlockModPlant(farmland, OreDictHelper.oreNickel, OreDictHelper.oreNickelMeta, Items.nuggetNickel, Items.nuggetNickelMeta, 4, 6);
                RegisterHelper.registerBlock(niccissus, Names.Crops.cropNiccissus);
            }
            if(OreDictHelper.orePlatinum!=null) {
                platiolus = new BlockModPlant(farmland, OreDictHelper.orePlatinum, OreDictHelper.orePlatinumMeta, Items.nuggetPlatinum, Items.nuggetPlatinumMeta, 4, 1);
                RegisterHelper.registerBlock(platiolus, Names.Crops.cropPlatiolus);
            }
            if(OreDictHelper.oreOsmium!=null) {
                osmonium = new BlockModPlant(farmland, OreDictHelper.oreOsmium, OreDictHelper.oreOsmiumMeta, Items.nuggetOsmium, Items.nuggetOsmiumMeta, 4, 6);
                RegisterHelper.registerBlock(osmonium, Names.Crops.cropOsmonium);
            }

            LogHelper.debug("Resource crops registered");
        }
    }
}
