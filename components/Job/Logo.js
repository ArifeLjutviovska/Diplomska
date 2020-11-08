import React, {useEffect, useState} from "react";
import ITCareerService from "../../services/ITService";
import LogoSrc from "./LogoSrc";

const JobLogo=(props)=>{

    const [logo,setLogo]=useState({});


    useEffect(()=>{
        ITCareerService.getCompanyLogo(props.job.company).then((res)=>{
            setLogo(res.data);
        });



    });
    return(


         <LogoSrc job={props.job} logo={logo}/>

    );

};
export default JobLogo;