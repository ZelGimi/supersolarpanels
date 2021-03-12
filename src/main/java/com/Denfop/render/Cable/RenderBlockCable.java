package com.Denfop.render.Cable;

import com.Denfop.IUCore;
import com.Denfop.tiles.base.TileEntityCable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.Direction;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class RenderBlockCable extends RenderBlock {
    private static final float[] faceColors = new float[]{0.6F, 0.6F, 0.5F, 1.0F, 0.8F, 0.8F};

    private static void renderFace(Tessellator tessellator, RenderBlocks renderBlocks, Block block, int x, int y, int z, IIcon[] textures, Direction face) {
        int dirIndex = face.ordinal();
        tessellator.setColorOpaque_F(faceColors[dirIndex], faceColors[dirIndex], faceColors[dirIndex]);
        switch (face) {
            case XN:
                renderBlocks.renderFaceXNeg(block, x, y, z, textures[dirIndex]);
                break;
            case XP:
                renderBlocks.renderFaceXPos(block, x, y, z, textures[dirIndex]);
                break;
            case YN:
                renderBlocks.renderFaceYNeg(block, x, y, z, textures[dirIndex]);
                break;
            case YP:
                renderBlocks.renderFaceYPos(block, x, y, z, textures[dirIndex]);
                break;
            case ZN:
                renderBlocks.renderFaceZNeg(block, x, y, z, textures[dirIndex]);
                break;
            case ZP:
                renderBlocks.renderFaceZPos(block, x, y, z, textures[dirIndex]);
                break;
        }
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderblocks) {
        super.renderWorldBlock(blockAccess, x, y, z, block, modelId, renderblocks);
        TileEntity te = blockAccess.getTileEntity(x, y, z);
        if (!(te instanceof TileEntityCable))
            return true;
        TileEntityCable cable = (TileEntityCable) te;
        if (cable.foamed == 1)
            return renderblocks.renderStandardBlock(block, x, y, z);
        if (cable.foamed == 2)
            return IUCore.proxy.getRender("wall").renderWorldBlock(blockAccess, x, y, z, block, modelId, renderblocks);
        float th = cable.getCableThickness();
        float sp = (1.0F - th) / 2.0F;
        int connectivity = cable.connectivity;
        int renderSide = cable.renderSide;
        IIcon[] textures = new IIcon[6];
        for (int side = 0; side < 6; side++)
            textures[side] = renderblocks.getBlockIcon(block, blockAccess, x, y, z, side);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess, x, y, z));
        if (connectivity == 0) {
            renderblocks.setRenderBounds(sp, sp, sp, (sp + th), (sp + th), (sp + th));
            for (Direction face : Direction.directions)
                renderFace(tessellator, renderblocks, block, x, y, z, textures, face);
        } else {
            boolean centerRendered = false;
            for (Direction dir : Direction.directions) {
                int dirIndex = dir.ordinal();
                if (dirIndex % 2 == 0) {
                    int mask = 3 << dirIndex;
                    if ((connectivity & mask) == mask) {
                        float[] dim = {sp, sp, sp, sp + th, sp + th, sp + th};
                        dim[dirIndex / 2] = 0.0F;
                        dim[dirIndex / 2 + 3] = 1.0F;
                        renderblocks.setRenderBounds(dim[0], dim[1], dim[2], dim[3], dim[4], dim[5]);
                        for (Direction face : Direction.directions) {
                            if (face.ordinal() / 2 != dirIndex / 2 || (renderSide & 1 << face
                                    .ordinal()) != 0)
                                renderFace(tessellator, renderblocks, block, x, y, z, textures, face);
                        }
                        connectivity &= mask ^ 0xFFFFFFFF;
                        centerRendered = true;
                    }
                }
            }
            for (Direction dir : Direction.directions) {
                int dirIndex = dir.ordinal();
                int mask = 1 << dirIndex;
                if ((connectivity & mask) != 0) {
                    float[] dim = {sp, sp, sp, sp + th, sp + th, sp + th};
                    float min = centerRendered ? (sp + th) : sp;
                    float max = centerRendered ? sp : (sp + th);
                    dim[dirIndex / 2] = (dirIndex % 2 == 0) ? 0.0F : min;
                    dim[dirIndex / 2 + 3] = (dirIndex % 2 == 0) ? max : 1.0F;
                    renderblocks.setRenderBounds(dim[0], dim[1], dim[2], dim[3], dim[4], dim[5]);
                    for (Direction face : Direction.directions) {
                        if (face != dir || (renderSide & mask) != 0)
                            renderFace(tessellator, renderblocks, block, x, y, z, textures, face);
                    }
                    centerRendered = true;
                }
            }
        }
        renderblocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }
}
