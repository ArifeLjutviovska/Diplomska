package mk.finki.diplomska.rabota.diplomska.controllers;

import mk.finki.diplomska.rabota.diplomska.models.CompanyUser;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanyUpdateRequest;
import mk.finki.diplomska.rabota.diplomska.services.Impl.CompanyServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyServiceImpl companyService;

    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }


    @GetMapping
    public List<CompanyUser> getAllCompanies(){
        return this.companyService.getAllCompanies();
    }

    @GetMapping("/byId/{id}")
    public CompanyUser getCompanyById(@PathVariable("id") Long id){
        return this.companyService.findById(id);
    }
    @GetMapping("/{name}")
    public CompanyUser getCompanyByName(@PathVariable("name") String name){
        return this.companyService.findByName(name);
    }

    //update
    @PatchMapping("/{id}")
    public CompanyUser updateCompany(@PathVariable("id") Long id,@RequestBody CompanyUpdateRequest updateRequest){
        return this.companyService.updateCompany(id,updateRequest);
    }

    //delete
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable("id") Long id){
        return this.companyService.deleteCompany(id);
    }
}
