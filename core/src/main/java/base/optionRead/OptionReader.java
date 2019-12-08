package base.optionRead;

import base.domain.LoadType;
import lombok.SneakyThrows;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class OptionReader {

    @SneakyThrows
    public List<LoadType> createLoadTypesFromOptions() {
        List<List<String>> options = getParsedValues();
        validateValues(options);
        return createLoadTypes(options.subList(1, options.size()));
    }

    private List<List<String>> getParsedValues() throws Exception {
        return FileParser.parseFile(Paths
                .get(ClassLoader.getSystemClassLoader().getResource("Options.csv").toURI())).stream()
                .map(FileParser::getParsedCsvLine)
                .collect(Collectors.toList());
    }

    private void validateValues(List<List<String>> options) {
        assert options.stream().noneMatch(row -> row.size() != 4);
        validateHeader(options.get(0));
    }

    private void validateHeader(List<String> header) {
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
