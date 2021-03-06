package com.InfinityRaider.AgriCraft.items;

import com.InfinityRaider.AgriCraft.blocks.BlockCrop;
import com.InfinityRaider.AgriCraft.init.Blocks;
import com.InfinityRaider.AgriCraft.reference.Constants;
import com.InfinityRaider.AgriCraft.tileentity.TileEntityChannel;
import com.InfinityRaider.AgriCraft.tileentity.TileEntityCrop;
import com.InfinityRaider.AgriCraft.tileentity.TileEntitySeedData;
import com.InfinityRaider.AgriCraft.tileentity.TileEntityTank;
import com.InfinityRaider.AgriCraft.utility.LogHelper;
import com.InfinityRaider.AgriCraft.utility.SeedHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemDebugger extends ModItem {
    public ItemDebugger() {
        super();
        this.setCreativeTab(null);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            Block block = world.getBlock(x, y, z);
            LogHelper.debug("Clicked block at: (" + x + "," + y + "," + z + "): "+Block.blockRegistry.getNameForObject(block)+":"+world.getBlockMetadata(x, y, z));
            if(block instanceof BlockBush) {
                //print data for crop
                if (block == Blocks.blockCrop) {
                    TileEntityCrop cropTE = (TileEntityCrop) world.getTileEntity(x, y, z);
                    BlockCrop blockCropBlock = (BlockCrop) world.getBlock(x, y, z);
                    if (cropTE.crossCrop) {
                        LogHelper.debug(" - This is a crosscrop");
                    }
                    if (cropTE.hasPlant()) {
                        LogHelper.debug(" - This crop has a plant");
                        LogHelper.debug(" - Seed: " + ((ItemSeeds) cropTE.seed).getUnlocalizedName());
                        LogHelper.debug(" - RegisterName: " + Item.itemRegistry.getNameForObject(cropTE.seed) + ':' + cropTE.seedMeta);
                        LogHelper.debug(" - Plant: " + SeedHelper.getPlant((ItemSeeds) cropTE.seed).getUnlocalizedName());
                        LogHelper.debug(" - Meta: " + blockCropBlock.getPlantMetadata(world, x, y, z));
                        LogHelper.debug(" - Growth: " + cropTE.growth);
                        LogHelper.debug(" - Gain: " + cropTE.gain);
                        LogHelper.debug(" - Strength: " + cropTE.strength);
                        LogHelper.debug(" - Fertile: " + cropTE.isFertile());
                        LogHelper.debug(" - Mature: " + cropTE.isMature());
                    }
                }
                //print data for a random plant
                else {
                    TileEntity te = world.getTileEntity(x, y-1, z);
                    LogHelper.debug("This is a plant "+(te==null?"without tile entity":"with tile entity"));
                    if(te!= null && te instanceof TileEntitySeedData) {
                        LogHelper.debug("Found seedData:");
                        TileEntitySeedData seedData = (TileEntitySeedData) te;
                        LogHelper.debug(" - Growth: " + seedData.growth);
                        LogHelper.debug(" - Gain: " + seedData.gain);
                        LogHelper.debug(" - Strength: " + seedData.strength);
                        LogHelper.debug(" - Analyzed: " + seedData.analyzed);
                    }
                }
            }
            //print data for tank
            else if (block == Blocks.blockWaterTank) {
                TileEntityTank tank = (TileEntityTank) world.getTileEntity(x, y, z);
                LogHelper.debug("Tank: " + (tank.isWood() ? "wood" : "iron") + " (single capacity: " + tank.getSingleCapacity() + ")");
                LogHelper.debug("  - MultiBlock: " + tank.isMultiBlock());
                LogHelper.debug("  - Connected tanks: " + tank.getConnectedTanks());
                int[] size = tank.getDimensions();
                LogHelper.debug("  - MultiBlock Size: " + size[0] + "x" + size[1] + "x" + size[2]);
                LogHelper.debug("  - FluidLevel: " + tank.getFluidLevel() + "/" + tank.getTotalCapacity());
                LogHelper.debug("  - FluidHeight: "+tank.getFluidY());
                boolean left = tank.isMultiBlockPartner(world.getTileEntity(x - 1, y, z));
                boolean right = tank.isMultiBlockPartner(world.getTileEntity(x + 1, y, z));
                boolean back = tank.isMultiBlockPartner(world.getTileEntity(x, y, z - 1));
                boolean front = tank.isMultiBlockPartner(world.getTileEntity(x, y, z + 1));
                boolean top = tank.isMultiBlockPartner(world.getTileEntity(x, y + 1, z));
                boolean below = tank.isMultiBlockPartner(world.getTileEntity(x, y - 1, z));
                LogHelper.debug("  - Found multiblock partners on: " + (left ? "left, " : "") + (right ? "right, " : "") + (back ? "back, " : "") + (front ? "front, " : "") + (top ? "top, " : "") + (below ? "below" : ""));
                LogHelper.debug("Water level is on layer "+ (int) Math.floor(((float)tank.getFluidLevel()-0.1F)/((float)(tank.getSingleCapacity()*tank.getXSize()*tank.getZSize())))+ ".");
                LogHelper.debug("Tank clicked is on  layer "+tank.getYPosition()+".");
                LogHelper.debug("Water height is "+tank.getFluidY());
                LogHelper.debug("Tank material is: " +Item.itemRegistry.getNameForObject(tank.getMaterial().getItem())+":"+tank.getMaterial().getItemDamage());
            }
            //print data for channel
            else if (block == Blocks.blockWaterChannel) {
                TileEntityChannel channel = (TileEntityChannel) world.getTileEntity(x, y, z);
                LogHelper.debug("Chanel material is: " +Item.itemRegistry.getNameForObject(channel.getMaterial().getItem())+":"+channel.getMaterial().getItemDamage());
                LogHelper.debug("  - FluidLevel: " + channel.getFluidLevel()+"/"+ Constants.mB/2);
                LogHelper.debug("  - FluidHeight: "+channel.getFluidHeight());
            }
            //print data for farmland
            else if(block == net.minecraft.init.Blocks.farmland) {
                LogHelper.debug("Farmland meta: "+world.getBlockMetadata(x, y, z));
            }
        }

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
    }
}
