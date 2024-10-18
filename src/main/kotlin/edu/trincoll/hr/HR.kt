package edu.trincoll.hr

import java.util.Objects

// The HR class should have:
//   - a list of employees
//   - a hire method that takes an employee and returns a new HR object with that employee added
//   - a fire method that takes an id and returns a new HR object with the employee with that id removed
//   - a payEmployees method that returns the total pay of all employees


class HR(private val employees: List<Employee> = emptyList()) {

    fun hire(employee: Employee): HR{
        val newEmployees = employees + employee
        return HR(newEmployees)
    }

   fun fire(employeeId: Int): HR {

       val newEmployees2 = employees.filter { it.id != employeeId}
       return HR(newEmployees2)
   }


    fun payEmployees(): Double {
        var totalPay = 0.0
        for (employee in employees) {
            totalPay = employee.pay() + totalPay
        }

        return totalPay

    }
}