package mk.finki.diplomska.rabota.diplomska.controllers;


import mk.finki.diplomska.rabota.diplomska.models.StudentUser;
import mk.finki.diplomska.rabota.diplomska.services.Impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentUser> getAllStudents(){
        return this.studentService.getAllStudents();
    }

    @GetMapping("/byId/{id}")
    public StudentUser getStudentById(@PathVariable("id") Long id){
        return this.studentService.findById(id);
    }

    @GetMapping("/{name}")
    public StudentUser getStudentByName(@PathVariable("name") String name){
        return this.studentService.findByName(name);
    }
}
