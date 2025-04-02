package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.lab1b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.lab1b.model.Employee;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.lab1b.model.PensionPlan;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PensionCLI {

    public static void main(String[] args) throws Exception {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Daniel", "Agar", LocalDate.parse("2018-01-17"), 105945.50,
                new PensionPlan("EX1089", LocalDate.parse("2023-01-17"), 100.00)));
        employees.add(new Employee(2, "Benard", "Shaw", LocalDate.parse("2018-10-03"), 197750.00, null));
        employees.add(new Employee(3, "Carly", "Agar", LocalDate.parse("2014-05-16"), 842000.75,
                new PensionPlan("SM2307", LocalDate.parse("2019-11-04"), 1555.50)));
        employees.add(new Employee(4, "Wesley", "Schneider", LocalDate.parse("2018-11-02"), 74500.00, null));
        employees.add(new Employee(
                5,
                "Grace",
                "Thomas",
                LocalDate.parse("2022-08-15"),
                92000.00,
                null
        ));

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

        System.out.println("\n=== All Employees (Sorted by Salary Desc, LastName Asc) ===");
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getYearlySalary).reversed()
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());

        System.out.println(writer.writeValueAsString(sortedEmployees));

        System.out.println("\n=== Quarterly Upcoming Enrollees Report ===");
        LocalDate today = LocalDate.now();
        Month nextQuarterStartMonth = Month.of(((today.getMonthValue() - 1) / 3 + 1) * 3 + 1);
        int year = today.getMonthValue() >= 10 ? today.getYear() + 1 : today.getYear();

        LocalDate nextQuarterStart = LocalDate.of(year, nextQuarterStartMonth, 1);
        LocalDate nextQuarterEnd = nextQuarterStart.plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());


        List<Employee> upcomingEnrollees = employees.stream()
                .filter(e -> e.getPensionPlan() == null)
                .filter(e -> {
                    LocalDate thirdAnniversary = e.getEmploymentDate().plusYears(3);
                    return (!thirdAnniversary.isBefore(nextQuarterStart) && !thirdAnniversary.isAfter(nextQuarterEnd));
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());

        System.out.println(writer.writeValueAsString(upcomingEnrollees));
    }
}
