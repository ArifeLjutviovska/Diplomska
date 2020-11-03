package mk.finki.diplomska.rabota.diplomska.services.Impl;

import mk.finki.diplomska.rabota.diplomska.models.*;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.StudentNotFoundException;
import mk.finki.diplomska.rabota.diplomska.payload.request.StudentUpdateModel;
import mk.finki.diplomska.rabota.diplomska.repository.JobExperienceRepository;
import mk.finki.diplomska.rabota.diplomska.repository.SkillsRepository;
import mk.finki.diplomska.rabota.diplomska.repository.StudentRepository;
import mk.finki.diplomska.rabota.diplomska.repository.SubjectRepository;
import mk.finki.diplomska.rabota.diplomska.services.StudentService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SkillsRepository skillsRepository;
    private final SubjectRepository subjectRepository;
    private final JobExperienceRepository jobExperienceRepository;

    public StudentServiceImpl(StudentRepository studentRepository, SkillsRepository skillsRepository, SubjectRepository subjectRepository, JobExperienceRepository jobExperienceRepository) {
        this.studentRepository = studentRepository;
        this.skillsRepository = skillsRepository;
        this.subjectRepository = subjectRepository;
        this.jobExperienceRepository = jobExperienceRepository;
    }


    @Override
    public List<StudentUser> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @Override
    public StudentUser findById(Long id) {
        return this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public StudentUser findByName(String name) {
        return this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public StudentUser updateStudent(Long id, StudentUpdateModel model) {
        StudentUser user=this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if(model.getStudentName()!=null){
            if (!user.getName().equals(model.getStudentName())) {
                user.setName(model.getStudentName());
            }
        }
        if(model.getEmail()!=null){
            if (!user.getEmail().equals(model.getEmail())) {
                user.setEmail(model.getEmail());
            }
        }
        if(model.getPassword()!=null){
            if (!user.getPassword().equals(model.getPassword())) {
                user.setPassword(model.getPassword());
            }
        }
        if(model.getImg()!=null){
            if (user.getImg()!=model.getImg()) {
                user.setImg(model.getImg());
            }
        }
        if(model.getCV()!=null){
            if (user.getCV()!=model.getCV()) {
                user.setCV(model.getCV());
            }
        }
        if(model.getSkills()!=null&&model.getSkills().size()!=0) {
            List<Skill> skills=new ArrayList<Skill>();
            model.getSkills().forEach(s-> {
                //System.out.println(this.skillsRepository.existByNameStudent(s.getName(),user.getId()));
                if(this.skillsRepository.findByNameStudent(s.getName(),user.getId())==null) {
                    if(this.skillsRepository.existsByName(s.getName())){
                        skills.add(this.skillsRepository.findByName(s.getName()));
                    }else {

                            skills.add(this.skillsRepository.save(new Skill(s.getName(),s.getIsFor())));


                    } }});

           user.getSkills().addAll(skills);

        }

        if(model.getSubjects()!=null&&model.getSubjects().size()!=0) {
            List<Subject> subjects=new ArrayList<Subject>();
            List<Skill> subjectSkill=new ArrayList<>();
            model.getSubjects().forEach(s-> {
                if(!this.subjectRepository.existByNameStudent(s.getName(),user.getId())) {
                    if(this.subjectRepository.existsByName(s.getName())){
                        subjects.add(this.subjectRepository.findByName(s.getName()));
                        user.getSkills().addAll(this.subjectRepository.findByName(s.getName()).getSkills());
                    }else {

                        s.getSkills().forEach(sk->{
                            if(this.skillsRepository.findByNameStudent(sk.getName(),user.getId())==null){
                                if(this.skillsRepository.existsByName(sk.getName())){
                                    subjectSkill.add(this.skillsRepository.findByName(sk.getName()));
                                }else{
                                    subjectSkill.add(this.skillsRepository.save(new Skill(sk.getName(),sk.getIsFor())));
                                }
                            }
                        });
                        subjects.add(this.subjectRepository.save(new Subject(s.getName(),s.getDescription(),subjectSkill)));
                        user.getSkills().addAll(subjectSkill);


                    } }});

            user.getSubjects().addAll(subjects);

        }
        if(model.getExperience()!=null&&model.getExperience().size()!=0){
            List<JobExperience> experiences=new ArrayList<JobExperience>();
            model.getExperience().forEach(e->{
                if(this.jobExperienceRepository.findByNameStudent(e.getCompanyName(),e.getJobIsFor(),user.getId())==null){
                    try {
                        experiences.add(this.jobExperienceRepository.save(new JobExperience(e.getCompanyName(),e.getJobIsFor(),e.getDescription(),new SimpleDateFormat("dd/MM/yyyy").parse(e.getDateStart()),new SimpleDateFormat("dd/MM/yyyy").parse(e.getDateEnd()))));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            user.getExperience().addAll(experiences);
        }
        user.setRoles(user.getRoles());
        return this.studentRepository.save(user);

    }

    @Override
    public String deleteStudent(Long id) {
        StudentUser user=this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        this.studentRepository.deleteById(id);
        return "Student user deleted with name "+user.getName();
    }
}
