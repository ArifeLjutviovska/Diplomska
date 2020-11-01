import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Header from "../Header/header";
import Footer from "../Footer/footer";
import Login from "../Login/login.component";
import Register from "../Register/register.component";




class App extends Component {

    componentDidMount() {


    }


    render() {



        return (
            <Router>

                    <Header/>


                    <div className="App" >
                        <Switch>




                            <Route id="login" exact path="/login" component={Login} />




                             <Route  exact  path="/register" component={Register} />




                        </Switch>
                    </div>
                    <Footer/>




            </Router>
        );
    }
}

export default App;