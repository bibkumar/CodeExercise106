## Code Exercise 106

### Task description
BIG COMPANY is employing a lot of employees. Company would like to analyze its organizational
structure and identify potential improvements. Board wants to make sure that every manager earns
at least 20% more than the average salary of its direct subordinates, but no more than 50% more
than that average. Company wants to avoid too long reporting lines, therefore we would like to
identify all employees which have more than 4 managers between them and the CEO.

You are given a CSV file which contains information about all the employees.

Each line represents an employee (CEO included). CEO has no manager specified. Number of rows
can be up to 1000.

Write a simple program which will read the file and report:
- which managers earn less than they should, and by how much
- which managers earn more than they should, and by how much
- which employees have a reporting line which is too long, and by how much

## Getting Started

### Dependencies

#### Prerequisites
- **Java JDK 11**: Required for compiling and running the application.
- **JUnit 5**: Required for unit testing.

#### Maven Configuration
- Dependencies are managed through Maven and should be automatically resolved when building the project.
- Maven Central Repository is used for fetching dependencies.

### Installing

#### Prerequisites
- Java and Maven is installed on machine.

#### Project Installation Steps

To install this project, follow these steps:

1. **Clone the repository:**
    ```bash
   git clone https://github.com/bibkumar/CodeExercise106.git
    ```
2. **Navigate to the project directory:**
    ```bash
   cd CodeExercise106
    ```
3. **Build the project with Maven:**
    ```bash
   mvn clean install
    ```
4. **Start the app with java:**
    ```bash
   java -jar .\target\CodeExercise106-1.0-SNAPSHOT.jar
    ```

### Running tests
```bash
mvn clean install
```

### Project Assumptions:

- CSV file will always contain the first line as header.
- There is only one CEO in the data file.
- All the attributes of the employees are not null except manager id of CEO which is null.
- Company consists of CEO, Managers and Employees.
- CEO is also a Manager.
- All Managers are Employees but not all Employees are Managers
- For my use case real world mapping of Manager and Employee object are sufficient.
- There may be certain Employees which are not managers.
- CSV file path is hard coded in Main.java to facilitate is run on IDE.