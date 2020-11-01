import React,{useEffect, useState}  from "react";
import AuthService from "../../services/auth.service";

const Home=(props)=>{

    const [bytes,setBytes]=useState([]);
    const [img,setImg]=useState({});
    const [src,setSrc]=useState("");

    useEffect(() => {
        AuthService.getImage("20a15f7c-c9d1-4a25-9772-3617f5d94c50").then((data)=>{
              setImg(data.data);
              setBytes(img.data);
              if(img.filetype==="image/png"){
                  setSrc('data:image/png;base64,'+bytes);
              }else{
                  setSrc('data:image/jpeg;base64,'+bytes);
              }

        });
        


        



    });




    return(

        <div>
            <img id="ItemPreview" src={src} alt="imjg.png"/>
        </div>
    );

};
export default Home;