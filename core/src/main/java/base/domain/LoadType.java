package base.domain;

import base.domain.constants.SetType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Data
public class LoadType {

    private String name;
    private Float gamma_0;
    private Float gamma_1;
    private Float gamma_2;

    private Map<SetType, DesignSituation> situations;

    public LoadType(String name, Float gamma_0, Float gamma_1, Float gamma_2) {
        this.name = name;
        this.gamma_0 = gamma_0;
        this.gamma_1 = gamma_1;
        this.gamma_2 = gamma_2;
        this.situations = Stream.of(SetType.values())
                .collect(HashMap::new, (m, e) -> m.put(e, null), Map::putAll);
    }
}
