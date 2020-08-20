import React, {Component} from 'react';
import {
    Route,
    Redirect,
    Switch
  } from 'react-router-dom';
import DasboardAdmin from "./DashboardAdmin";
import DasboardUser from "./DashboardUser";

class MainLayout extends Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        const {userInfo} = this.props;
        if (!userInfo) {
            this.props.history.push({
                pathname: '/login',
                state: {from: this.props.location}
            });
        }
    }

    render() {
        const {userInfo} = this.props;
		console.log(JSON.stringify(userInfo));
        let isAdmin = false;
        if (userInfo && userInfo.authorities) {
            isAdmin = userInfo.authorities[0].authority === "ROLE_ADMIN" ? true : false;
        }
        return (
            <div>
                <Switch>
                    <Route exact path={`/`} render={() => <Redirect to={isAdmin? '/admin-dashboard': '/user-dashboard'}/>}/>

				
                    <Route path="/user-dashboard" 
                        render={(props) => <DasboardUser  
                      currentUser={userInfo}{...props} />}></Route>
                    <Route path="/admin-dashboard" 
                        render={(props) => <DasboardAdmin {...props} />}></Route>
                </Switch>
            </div>
        );
    }
}

export default MainLayout;