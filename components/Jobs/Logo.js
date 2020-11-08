import React, {useEffect, useState} from "react";
import ITCareerService from "../../services/ITService";
import AuthService from "../../services/auth.service";
import Job from "./Job";
import OneJob from "./oneJobBox";

const Logo=(props)=>{
    const [logo,setLogo]=useState({});


    useEffect(()=>{
        ITCareerService.getCompanyLogo(props.job.company).then((res)=>{
            setLogo(res.data);
        });



    });
    return(
        <div>
            <OneJob logo={logo} job={props.job}/>
        </div>
    );

};
export default Logo;