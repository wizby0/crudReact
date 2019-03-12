import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import GroupList from './GroupList';
import GroupEdit from './GroupEdit';
import PostList from "./PostList";
import PostEdit from "./PostEdit";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/groups' exact={true} component={GroupList}/>
                    <Route path='/groups/:id' component={GroupEdit}/>
                    <Route path='/posts' exact={true} component={PostList}/>
                    <Route path='/posts/:id' component={PostEdit}/>
                </Switch>
            </Router>
        )
    }
}

export default App;