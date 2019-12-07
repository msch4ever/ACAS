package base;

import lombok.Data;

@Data
public class DesignSituation {

    private final SetType name;
    private final float g_Gsup;
    private float z_g_Gsup;
    private final float g_Q1;
    private float g_Q1_gamma_1;
    private final float g_Qi;
    private float g_Qi_gamma_0;

    public DesignSituation(String name, float g_Gsup, float g_Q1, float g_Qi, LoadType loadType) {
        this.name = SetType.findSetTypeByString(name);
        this.g_Gsup = g_Gsup;
        this.g_Q1 = g_Q1;
        this.g_Qi = g_Qi;
        this.z_g_Gsup = g_Gsup * 0.85f;
        this.g_Q1_gamma_1 = g_Q1 * loadType.getGamma_1();
        this.g_Qi_gamma_0 = g_Qi * loadType.getGamma_0();
    }
}
