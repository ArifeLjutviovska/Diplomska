import React, {useEffect, useState} from "react";
import ITService from "../../services/ITService";
import "./jobs.css";
import Logo from "./Logo";
import OneJob from "./oneJobBox";

const Jobs=(props)=>{

    const [jobs,setJobs]=useState([]);


    useEffect(()=>{
        ITService.getJobs().then((res)=>{
            setJobs(res.data);
        });

     });

    const jobOffer=jobs.map((j)=>{

        return(
            <div className="container">
               <Logo job={j} key={j.id} />
            </div>
        )

    });
    return(
        <div>
            <div className="top-jobs">
            </div>
            {jobOffer}

        </div>
    );

};

export default Jobs;