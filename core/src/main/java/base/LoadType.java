package base;

import lombok.Value;

import java.util.Map;

@Value
public class LoadType {

    private String name;
    private float gamma_0;
    private float gamma_1;
    private float gamma_2;

    private Map<SetType, DesignSituation> situations;

}
