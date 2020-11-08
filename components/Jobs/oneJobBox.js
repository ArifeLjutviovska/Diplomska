import React, {useEffect, useState} from "react";
import "./jobs.css";
import ITCareerService from "../../services/ITService";
import AuthService from "../../services/auth.service";
import { Icon, InlineIcon } from '@iconify/react';
import mapMarker from '@iconify/icons-fa/map-marker';
import clockO from '@iconify/icons-fa/clock-o';
import Job from "./Job";


const OneJob=(props)=>{


    const [bytes,setBytes]=useState([]);
    const [img,setImg]=useState({});
    const [src,setSrc]=useState("");
    const [type,setType]=useState("");


    useEffect(()=>{

        AuthService.getImage(props.logo.id).then((data)=>{
            setImg(data.data);
            setBytes(img.data);
            if(img.filetype==="image/png"){
                setSrc('data:image/png;base64,'+bytes);
            }else{
                setSrc('data:image/jpeg;base64,'+bytes);
            }

        });
        if(props.job.jobType==="Full_Time"){
            setType("Full Time");
        }else if(props.job.jobType==="Part_Time"){
            setType("Part Time");
        }else{
            setType("Internship");
        }


    });
    return(


        <div >
            <Job src={src} job={props.job} type={type}/>
        </div>
    );

};
export default OneJob;