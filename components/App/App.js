import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Header from "../Header/header";
import Footer from "../Footer/footer";
import Login from "../Login/login.component";
import Register from "../Register/register.component";
import homePage from "../ITCareerHomePage/homePage";
import Jobs from "../Jobs/jobs";
import AuthService from "../../services/auth.service";
import JobContent from "../Job/JobContent";
import ITService from "../../services/ITService";


class App extends Component {

    constructor(props) {
        super(props);
        this.logOut = this.logOut.bind(this);

        this.state = {
            currentUser: undefined,
            authName:"",
        };
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();


        if (user) {


            this.setState({
                currentUser: AuthService.getCurrentUser(),


            });
            AuthService.getLogedInUserName().then((data)=>{
                this.setState({
                    authName:data.data
                })
            });
        }

    }

    logOut() {
        AuthService.logout();
    }


    render() {

        return (
            <Router>
                    <Header currentUser={this.state.currentUser}  onLogOut={this.logOut}  />
                    <div className="App" >
                        <Switch>
                            <Route exact path="/" component={homePage}/>
                            <Route exact  path="/jobs" component={Jobs}/>
                            <Route exact path="/login" component={Login} />
                             <Route  exact  path="/register" component={Register} />
                            <Route exact path="/jobs/:id" component={JobContent}/>

                        </Switch>
                    </div>
                    <Footer/>




            </Router>
        );
    }
}

export default App;