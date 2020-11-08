import React, {useEffect, useState} from "react";
import AuthService from "../../services/auth.service";
import JobInfo from "./JobInfo";

const LogoSrc=(props)=>{

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

            <JobInfo src={src} jobType={type} job={props.job}/>


    );

};
export default LogoSrc;