package base.optionRead;

import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static List<String> parseFile(Path document) throws Exception {
        return Files.readAllLines(document);
    }

    @SneakyThrows
    public static List<String> getParsedCsvLine(String csvLine) {
        List<String> line = new ArrayList<>();
        Iterable<CSVRecord> parser;
        parser = CSVParser.parse(csvLine, CSVFormat.DEFAULT);
        parser.forEach(record -> record.forEach(it -> {
                    line.add(it.replaceAll(",", "."));
        }
        ));
        return line;
    }
}