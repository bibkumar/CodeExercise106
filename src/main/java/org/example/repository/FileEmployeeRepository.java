package org.example.repository;

import org.example.model.EmployeeDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Employee Repository mapped to file as data source.
 *
 * @author BibhavKumar
 */
public class FileEmployeeRepository implements EmployeeRepository {

    private final String dataFilePath;

    public FileEmployeeRepository(final String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        try (Stream<String> lines = Files.lines(Paths.get(dataFilePath))) {
            return lines.map(line -> Arrays.asList(line.split(",")))
                    .skip(1)
                    .map(EmployeeDto::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error occurred in reading file");
            throw new RuntimeException(e);
        }
    }
}
