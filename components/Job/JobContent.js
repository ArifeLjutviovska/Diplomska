import React, {useEffect, useState} from "react";
import "./jobContent.css";
import {useParams} from "react-router-dom";
import ITService from "../../services/ITService";
import JobLogo from "./Logo";

const JobContent=(props)=> {

    const {id}=useParams();
    const [job,setJob]=useState({});


    useEffect(()=>{
        ITService.getJobById(id).then((res)=>{
            setJob(res.data);
        })


    });
    return(

            <JobLogo job={job}/>

    );

};
export default JobContent;