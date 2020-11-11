package mk.finki.diplomska.rabota.diplomska.controllers;

import mk.finki.diplomska.rabota.diplomska.models.CompanyUser;
import mk.finki.diplomska.rabota.diplomska.models.DBFile;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanyUpdateRequest;
import mk.finki.diplomska.rabota.diplomska.services.Impl.CompanyServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.awt.image.DataBufferFloat;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyServiceImpl companyService;

    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }



    @GetMapping("/companyNames")
    public List<String> getCompanyNames(){
        return this.companyService.getCompanyNames();
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

    @GetMapping("/companyLogo")
    public DBFile getCompanyLogo(@RequestParam String companyName){
        return this.companyService.getCompanyLogo(companyName);
    }
    @GetMapping("/logoSrc")
    public String getLogoSrc(@RequestParam Long jobId){
        return this.companyService.getLogoSrc(jobId);
    }
    @GetMapping("/companyLogoId")
    public String getLogoId(@RequestParam String companyName){
        return this.companyService.companyLogoId(companyName);
    }
    @GetMapping("/companyByJobId")
    public String getCompanyByJob(@RequestParam Long id){
        return this.companyService.getCompanyNameByJobId(id);
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

    @GetMapping("/logoBytes/{jobId}")
    public byte[] gettLogoBytesOfCompany(@PathVariable("jobId") Long id){
        return this.companyService.getLogoBytes(id);
    }
    @GetMapping("/logoFileType/{jobId}")
    public String getLogoFileTypeOfCompany(@PathVariable("jobId") Long id){
        return this.companyService.getLogoFileType(id);
    }
    @GetMapping("/logo/{jobId}")
    public DBFile getLogoOfCompany(@PathVariable("jobId") Long id){
        return this.companyService.getCompanyLogoByJobId(id);
    }
}
