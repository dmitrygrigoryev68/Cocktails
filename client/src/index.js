import App from "./js/components/container/App.js";
import ReactDOM from "react-dom";
import React from "react";
import Cocktails from "./js/components/container/Cocktails";
import { Route, Link, BrowserRouter as Router } from 'react-router-dom'

const routing = (
    <Router>
        <div>
            <ul>
                <li>
                    <Link to="/">App</Link>
                </li>
                <li>
                    <Link to="/cocktail">Cocktails</Link>
                </li>
            </ul>
            <Route exact path="/" component={App} />
            <Route path="/cocktail" component={Cocktails} />
        </div>
    </Router>
)

ReactDOM.render(routing, document.getElementById('app'))
//const wrapper = document.getElementById("app");
//wrapper ? ReactDOM.render(<App />, wrapper) : false;