package com.Denfop.DamageSource;

import net.minecraft.util.DamageSource;

public class SSPDamageSource extends DamageSource {
    public static final SSPDamageSource current;

    static {

        current = (SSPDamageSource) new SSPDamageSource(("current")).setDamageBypassesArmor();

    }

    public SSPDamageSource(final String s) {
        super(s);
    }


}
