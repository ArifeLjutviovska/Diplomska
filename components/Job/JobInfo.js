import React, {useEffect, useState} from "react";
import "./jobContent.css";
import { Icon, InlineIcon } from '@iconify/react';
import bankIcon from '@iconify/icons-fa/bank';
import mapMarker from "@iconify/icons-fa/map-marker";
import calendarCheckO from '@iconify/icons-fa/calendar-check-o';
import ITService from "../../services/ITService";
import userO from '@iconify/icons-fa/user-o';




const JobInfo=(props)=>{

    const [city,setCity]=useState("");
    const [category,setCategory]=useState("");
    const [responsibilities,setResponsibilities]=useState([]);
    const [requiredSkills,setRequiredSkills]=useState([]);
    const [whatWeOffer,setWhatWeOffer]=useState([]);

    useEffect(()=>{
      ITService.getCityNameOfJob(props.job.id).then((res)=>{
          setCity(res.data);
      });
        ITService.getCategoryOfJob(props.job.id).then((res)=>{
            setCategory(res.data);
        });
        ITService.getJobResponsibilities(props.job.id).then((res)=>{
            setResponsibilities(res.data);
        });

        ITService.getJobRequiredSkills(props.job.id).then((res)=>{
            setRequiredSkills(res.data);
        });
        ITService.getJobWhatWeOffer(props.job.id).then((res)=>{
            setWhatWeOffer(res.data);
        });

    });

    const respons=responsibilities.map((r)=>{
        return(
            <li>{r}</li>
        );
    });
    const skills=requiredSkills.map((s)=>{
        return(
            <li>{s}</li>
        )
    });
    const offers=whatWeOffer.map((o)=>{
        return(
            <li>{o}</li>
        );
    });
    return(
        <div className="container">


              <div>
                 <h4 className="vrabotuvanje">Вработување</h4>
                 <hr className="text-secondary"/>
              </div><br/>
              <div className="job-header" >
                  <div className="job-logo-header">
                     <img id="ItemPreview"  className="lg logo-box" src={props.src} alt="logo.png"/>
                  </div>
                  <div className="job-content-header">
                      <h1 className="text-lg-left job-content-h1">{props.job.title}</h1>
                      <br/><br/>
                      <div className="icons">
                          <div className="icons-left">
                              <Icon className="icons-style" icon={bankIcon} /><span className="icons-left-text">{props.job.company}</span>
                               <br/>
                          </div>
                          <div className="icons-left">
                              <br/>
                              <Icon icon={calendarCheckO} className="icon-clock-style"/><span className="icons-clock-left-text">Активен од: {props.job.publicationDateStart}</span>
                          </div>
                          <div className="icons-left">
                              <br/><br/>
                              <Icon icon={calendarCheckO} className="icon-clock-style"/><span className="icons-clock-left-text">Активен до: {props.job.publicationDateEnd}</span>
                          </div>
                          <div className="icons-right">
                              <Icon icon={mapMarker} className="icons-style"/><span className="icons-right-text">Локација: {city}</span>
                          </div>
                          <div className="icons-right">
                              <br/>
                              <Icon icon={userO} className="icons-style"/><span className="icons-right-text">Работно време: {props.jobType}</span>
                          </div>
                          <div className="icons-right-category">
                              <br/><br/>
                              <Icon icon={userO} className="icons-category-style"/><span className="icons-right-text">Категорија: {category}</span>
                          </div>

                      </div>


                  </div>
                  <br/><br/><hr/>
              </div>
           <br/><br/><br/> <hr/>
           <br/><br/>
           <div className="text-left job-content-body">
               <h5>Job description:</h5>
               {props.job.description}<br/>

               <br/><br/>
               <div>
                   <h5>Responsibilities:</h5>
                   {respons}

               </div><br/>
               <div>
               <h5>Required Skills:</h5>
               {skills}

           </div><br/>
               <div>
                   <h5>We provide:</h5>
                   {offers}

               </div>
               <div>
                   <br/><br/><br/>
                   <h5 className="applyInfo">If you want to apply then please send us CV at {props.job.applyEmail}</h5>
               </div>

           </div>
              <div className="before-footer"></div>

        </div>
    );

};
export default JobInfo;