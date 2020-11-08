import React from "react";
import {Icon} from "@iconify/react";
import mapMarker from "@iconify/icons-fa/map-marker";
import clockO from "@iconify/icons-fa/clock-o";
import calendarCheckO from '@iconify/icons-fa/calendar-check-o';

const Job=(props)=>{
    return(

        <div className="job">
            <div >
                <img id="ItemPreview"  className="logo-box" src={props.src} alt="logo.png"/>
            </div>
            <div className="content-box">

                <div>
                    <a href={"/jobs/"+props.job.id}><h3 className="text-dark title-box">{props.job.title}</h3></a>

                    <span className="text-secondary jobType">{props.type}</span>
                </div>
                <div>
                    <br/>
                    <br/>
                    <Icon className="loc-icon" icon={mapMarker} />
                    <span className="loc-text">{props.job.city.name}</span>
                    <br/>
                    <Icon icon={calendarCheckO} className="clock-icon"/>
                    <span className="clock-text">application deadline: {props.job.publicationDateEnd}</span>
                </div>


            </div>
        </div>
    );

};
export default Job;