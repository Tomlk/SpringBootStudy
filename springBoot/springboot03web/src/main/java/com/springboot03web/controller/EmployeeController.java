package com.springboot03web.controller;

import com.springboot03web.dao.DepartmentDao;
import com.springboot03web.dao.EmployeeDao;
import com.springboot03web.pojo.Department;
import com.springboot03web.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    //
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list.html";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
    @PostMapping("/emps")
    public String addEmp(Employee employee){
        System.out.println(employee);
        //添加操作 forward
        employeeDao.save(employee);//保存员工信息
        return "redirect:/emps";
    }

    //去员工的修改页面
    @GetMapping("/emps/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id,Model model){
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);

        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

//    @PostMapping("/emps")
//    public String updateEmp(Employee employee){
//        employeeDao.save(employee);
//        return "redirect:/emps";
//    }

}
