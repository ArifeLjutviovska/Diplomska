package mk.finki.diplomska.rabota.diplomska.controllers;


import mk.finki.diplomska.rabota.diplomska.models.DBFile;
import mk.finki.diplomska.rabota.diplomska.models.JobExperience;
import mk.finki.diplomska.rabota.diplomska.models.Skill;
import mk.finki.diplomska.rabota.diplomska.models.StudentUser;
import mk.finki.diplomska.rabota.diplomska.payload.request.StudentUpdateModel;
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

    //update
    @PatchMapping("/{id}")
    public StudentUser studentUpdate(@PathVariable("id") Long id,@RequestBody StudentUpdateModel updateModel){
        return this.studentService.updateStudent(id,updateModel);
    }

    //delete
    @DeleteMapping("/{id}")
    public String studentDelete(@PathVariable("id") Long id){

        return this.studentService.deleteStudent(id);
    }

    @GetMapping("/{id}/img")
    public DBFile getImg(@PathVariable("id") Long id){
        return this.studentService.getStudentImg(id);
    }
    @GetMapping("/{id}/hasImg")
    public boolean hasImg(@PathVariable("id") Long id){
        return this.studentService.StudentHasImg(id);
    }
    @GetMapping("/{name}/nameHasImg")
    public boolean hasNameImg(@PathVariable("name") String name){
        return this.studentService.StudentHasImg(name);
    }
    @GetMapping("/{name}/nameImg")
    public DBFile getImg(@PathVariable("name") String name){
        return this.studentService.getStudentImg(name);
    }

    @GetMapping("/{name}/skills")
    public List<String>  getStudentSkills(@PathVariable("name") String name){
        return this.studentService.getStudentSkills(name);
    }
    @GetMapping("/{name}/subjects")
    public List<String>  getStudentSubjectNames(@PathVariable("name") String name){
        return this.studentService.getSubjectNames(name);
    }
    @GetMapping("/{name}/languages")
    public List<String>  getStudentLanguages(@PathVariable("name") String name){
        return this.studentService.getLanguages(name);
    }
    @GetMapping("/{name}/branch")
    public String  getStudentBranch(@PathVariable("name") String name){
        return this.studentService.getBranch(name);
    }
    @GetMapping("/{name}/city")
    public String  getStudentCity(@PathVariable("name") String name){
        return this.studentService.getStudentCity(name);
    }
    @GetMapping("/{name}/experiences")
    public List<JobExperience>  getStudentExperiences(@PathVariable("name") String name){
        return this.studentService.getExperiences(name);
    }
}
