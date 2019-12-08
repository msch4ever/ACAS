package base.domain;

import base.domain.constants.SetType;
import lombok.Data;

@Data
public class DesignSituation {

    private final SetType name;
    private final Float g_Gsup;
    private Float z_g_Gsup;
    private final Float g_Q1;
    private Float g_Q1_gamma_1;
    private final Float g_Qi;
    private Float g_Qi_gamma_0;

    public DesignSituation(String name, Float g_Gsup, Float g_Q1, Float g_Qi, LoadType loadType) {
        this.name = SetType.findSetTypeByString(name);
        this.g_Gsup = g_Gsup;
        this.g_Q1 = g_Q1;
        this.g_Qi = g_Qi;
        this.z_g_Gsup = g_Gsup * 0.85f;
        this.g_Q1_gamma_1 = g_Q1 * loadType.getGamma_1();
        this.g_Qi_gamma_0 = g_Qi * loadType.getGamma_0();
    }
}
