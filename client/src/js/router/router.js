import {BrowserRouter as Router, Link, Route} from 'react-router-dom'
import React from "react";

import Cocktail from "../components/container/Cocktail.js";
import App from "../App";

const router = (
    <Router>
        <div>
            <ul>
                <li>
                    <Link to="/">App</Link>
                </li>
                <li>
                    <Link to="/cocktail">Cocktail</Link>
                </li>
            </ul>
            <Route exact path="/" component={App}/>
            <Route path="/users" component={Cocktail}/>
        </div>
    </Router>
)

export default router;