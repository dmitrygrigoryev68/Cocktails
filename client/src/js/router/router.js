import {BrowserRouter as Router, Link, Route} from 'react-router-dom'
import React from "react";
import { createBrowserHistory } from "history";

import Cocktail from "../components/container/Cocktail.js";
import App from "../App";

const hist = createBrowserHistory();

const router = (
    <Router history={hist}>
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
            <Route exact path="/cocktail" component={Cocktail}/>
        </div>
    </Router>
)

export default router;