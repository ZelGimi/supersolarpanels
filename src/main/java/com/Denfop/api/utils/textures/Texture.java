package com.Denfop.api.utils.textures;

import net.minecraft.client.renderer.texture.AbstractTexture;

import java.awt.image.BufferedImage;

/**
 * @author MozG
 */
public abstract class Texture extends AbstractTexture {

    protected BufferedImage imageData;

    public BufferedImage getImage() {
        return imageData;
    }

}
