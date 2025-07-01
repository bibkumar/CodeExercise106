package org.bibhav.repository;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.dto.EmployeeDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public List<EmployeeDto> getEmployeeDtoList() throws ApplicationException, BadRequestException {
        try (Stream<String> lines = Files.lines(Paths.get(dataFilePath))) {
            List<String> linesLst = lines.skip(1).collect(Collectors.toList());
            List<EmployeeDto> employeeDtoList = new ArrayList<>();
            for (String line : linesLst) {
                if (line == null || line.isEmpty()) {
                    throw new BadRequestException("Invalid data format in processing file");
                }
                List<String> lineLst = Arrays.asList(line.split(","));
                if (lineLst.size() < 4 || lineLst.size() > 5) {
                    throw new BadRequestException("Invalid data format in processing file");
                }
                EmployeeDto employeeDto = new EmployeeDto(lineLst);
                employeeDtoList.add(employeeDto);
            }
            return employeeDtoList;
        } catch (IOException e) {
            throw new ApplicationException("Error occurred in reading file");
        }
    }
}
