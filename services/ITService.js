import axios from "axios";

const API_URL = "http://localhost:8090/";

class ITService{
    getJobs(){
        return axios.get(API_URL+"jobs");
    }
    getCompanies(){
        return axios.get(API_URL+"companies");
    }
    getCompanyLogo(companyName){
        return axios.get(API_URL+"companies/companyLogo",{
            params:{
                companyName
            }
        })
    }
    getJobType(type){
        return axios.get(API_URL+"jobs/jobType",{
            params:{
                type
            }
        })
    }
    getJobById(id){
        return axios.get(API_URL+"jobs/"+id);
    }
    getCityNameOfJob(id){
        return axios.get(API_URL+"jobs/"+id+"/cityName");
    }
    getCategoryOfJob(id){
        return axios.get(API_URL+"jobs/"+id+"/categoryName");
    }
    getJobResponsibilities(id){
       return axios.get(API_URL+"jobs/"+id+"/jobResponsibilities");
    }
    getJobRequiredSkills(id){
        return axios.get(API_URL+"jobs/"+id+"/requiredSkills");
    }
    getJobWhatWeOffer(id){
        return axios.get(API_URL+"jobs/"+id+"/whatWeOffer");
    }
    getJobTechnologies(id){
        return axios.get(API_URL+"jobs/"+id+"/technologies");
    }

}
export default new ITService();