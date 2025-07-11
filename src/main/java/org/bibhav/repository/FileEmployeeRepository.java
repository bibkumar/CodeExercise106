package org.bibhav.repository;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.dto.EmployeeDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.bibhav.util.AppConstants.*;

/**
 * Employee Repository mapped to file as a data source.
 * <p>
 * This class implements the `EmployeeRepository` interface and provides functionality
 * to read employee data from a file, validate its format, and convert it into `EmployeeDto` objects.
 * It ensures data integrity by checking for duplicate entries, invalid formats, and proper CEO configuration.
 * <p>
 * Typical use cases include loading employee data for organizational structure analysis,
 * reporting hierarchy evaluation, and other data-driven processes within an organization.
 *
 * @author BibhavKumar
 */
public class FileEmployeeRepository implements EmployeeRepository {

    private final String dataFilePath;

    public FileEmployeeRepository(final String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    /**
     * Retrieves a list of EmployeeDto objects representing employees from the data file.
     *
     * @return A list of EmployeeDto objects containing employee data.
     * @throws ApplicationException If an error occurs while reading the file.
     * @throws BadRequestException  If the data format is invalid or parsing fails.
     */
    public List<EmployeeDto> getEmployeeDtoList() throws ApplicationException, BadRequestException {
        try (Stream<String> lines = Files.lines(Paths.get(dataFilePath))) {
            List<String> linesLst = skipTheHeaderAndGetAllEmployeeLinesFromFile(lines);
            throwBadRequestExceptionIfDuplicateLinesFound(linesLst);
            List<EmployeeDto> employeeDtoList = new ArrayList<>();
            int ceoCount = 0;
            for (String line : linesLst) {
                throwBadRequestExceptionIfEmptyLineEncountered(line);
                List<String> lineLst = Arrays.asList(line.split(","));
                throwBadRequestExceptionIfEachLineRecordCountIsNotFourOrFive(lineLst);
                EmployeeDto employeeDto = new EmployeeDto(lineLst);
                if (Objects.isNull(employeeDto.getManagerId())) {
                    ceoCount += 1;
                }
                employeeDtoList.add(employeeDto);
            }
            throwBadRequestExceptionIfMoreThanOneCeoOrNoCEOIsSuppliedInData(ceoCount);
            return employeeDtoList;
        } catch (IOException e) {
            throw new ApplicationException(ERROR_OCCURRED_IN_READING_FILE);
        }
    }

    private List<String> skipTheHeaderAndGetAllEmployeeLinesFromFile(final Stream<String> lines) {
        return lines.skip(1).collect(Collectors.toList());
    }

    private void throwBadRequestExceptionIfDuplicateLinesFound(final List<String> linesLst) throws BadRequestException {
        Set<String> uniqueLines = new HashSet<>(linesLst);
        if (uniqueLines.size() != linesLst.size()) {
            throw new BadRequestException(DUPLICATE_EMPLOYEE_FOUND);
        }
    }

    private void throwBadRequestExceptionIfEachLineRecordCountIsNotFourOrFive(final List<String> lineLst) throws BadRequestException {
        if (lineLst.size() < 4 || lineLst.size() > 5) {
            throw new BadRequestException(INVALID_DATA_FORMAT_ERROR);
        }
    }

    private void throwBadRequestExceptionIfEmptyLineEncountered(final String line) throws BadRequestException {
        if (line == null || line.isEmpty()) {
            throw new BadRequestException(INVALID_DATA_FORMAT_ERROR);
        }
    }

    private void throwBadRequestExceptionIfMoreThanOneCeoOrNoCEOIsSuppliedInData(int ceoCount) {
        if (ceoCount > 1) {
            throw new BadRequestException(CEO_CONFIGURATION_ERROR);
        }
    }
}
