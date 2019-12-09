package base.optionRead;

import base.domain.LoadType;
import lombok.SneakyThrows;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class OptionReader {

    private static final String LOAD_TYPES = "LoadTypes.csv";
    private static final String EQU_A = "EQU_A.csv";
    private static final String STR_GEO_B_1 = "STR_GEO_B_1.csv";
    private static final String STR_GEO_B_2 = "STR_GEO_B_2.csv";
    private static final String STR_GEO_B_3 = "STR_GEO_B_3.csv";
    private static final String STR_GEO_C = "STR_GEO_C.csv";

    @SneakyThrows
    public List<LoadType> createLoadTypesFromOptions() {
        List<List<String>> options = getParsedValues(LOAD_TYPES);
        validateValues(options);
        return createLoadTypes(options.subList(1, options.size()));
    }

    public void enrichLoadTypesWithDesignSituations(List<LoadType> types) {
        enrichWtihSingleDesignSituation(types, EQU_A);
        enrichWtihSingleDesignSituation(types, STR_GEO_B_1);
        enrichWtihSingleDesignSituation(types, STR_GEO_B_2);
        enrichWtihSingleDesignSituation(types, STR_GEO_B_3);
        enrichWtihSingleDesignSituation(types, STR_GEO_C);
    }

    @SneakyThrows
    private void enrichWtihSingleDesignSituation(List<LoadType> types, String designSituationFileName) {
        List<List<String>> options = getParsedValues(designSituationFileName);
    }

    private List<List<String>> getParsedValues(String fileName) throws Exception {
        return FileParser.parseFile(Paths
                .get(ClassLoader.getSystemClassLoader().getResource(fileName).toURI())).stream()
                .map(FileParser::getParsedCsvLine)
                .collect(Collectors.toList());
    }

    private void validateValues(List<List<String>> options) {
        assert options.stream().noneMatch(row -> row.size() != 4);
        validateLoadTypesHeader(options.get(0));
    }

    private void validateLoadTypesHeader(List<String> header) {
        assert header.get(0).equalsIgnoreCase("Load type");
        assert header.get(1).equalsIgnoreCase("y0");
        assert header.get(2).equalsIgnoreCase("y1");
        assert header.get(3).equalsIgnoreCase("y2");
    }

    private List<LoadType> createLoadTypes(List<List<String>> rows) {
        return rows.stream().map(this::createLoadType).collect(Collectors.toList());
    }

    private LoadType createLoadType(List<String> row) {
        return new LoadType(
                row.get(0).trim(),
                convertFloat(row.get(1).trim()),
                convertFloat(row.get(2).trim()),
                convertFloat(row.get(3).trim())
        );
    }

    private Float convertFloat(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        return Float.valueOf(input);
    }

}
