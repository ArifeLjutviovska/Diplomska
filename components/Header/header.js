import {Link} from "react-router-dom";
import React from "react";
import FormSearch from "../FormSearch/FormSearch";
import "./header.css";

const Header=(props)=>{
return(
    <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand">
            IT Career
        </Link>
        <div className="navbar-nav mr-auto">
            <li className="nav-item">
                <Link to={"/home"} className="nav-link">
                    Home
                </Link>
            </li>
            <li className="nav-item">
                <Link to={"/groups"} className="nav-link">
                    Groups
                </Link>
            </li>
            <li className="nav-item">
                <Link to={"/groups/add"} className="nav-link">
                    Add Group
                </Link>
            </li>
            <li className="nav-item">
                <Link to={"/members"} className="nav-link">
                    Users
                </Link>
            </li>



            {props.admin && (
                <li className="nav-item">
                    <Link to={"/admin"} className="nav-link">
                        Admin Board
                    </Link>
                </li>
            )}

            {props.currentUser && (
                <li className="nav-item">
                    <Link to={"/user"} className="nav-link">
                        User
                    </Link>
                </li>
            )}
            </div>


            {props.currentUser ? (

            <div className="navbar-nav ml-auto">
                <FormSearch className="nav-item"  onSearch={props.onSearch}/>
                <li className="nav-item">
                    <Link to={"/profile"} className="nav-link">
                        {props.currentUser.username}
                    </Link>
                </li>
                <li className="nav-item">
                    <a href="/login" className="nav-linkew" onClick={props.onLogOut}>
                        LogOut
                    </a>
                </li>
            </div>
        ) : (
            <div className="navbar-nav ml-auto">
                <FormSearch className="nav-item"  onSearch={props.onSearch}/>
                <li className="nav-item">
                    <Link to={"/login"} className="nav-link">
                        Login
                    </Link>
                </li>

                <li className="nav-item">
                    <Link to={"/register"} className="nav-link">
                        Sign Up
                    </Link>
                </li>
            </div>
        )}

    </nav>
);
};

export default Header;

