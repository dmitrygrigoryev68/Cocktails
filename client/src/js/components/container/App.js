import React, { Component } from "react";
import Input from "../view/Input.js";
import axios from "axios"
import {BrowserRouter,Route} from "react-router-dom";
import Home from "./Home";
import Cocktails from "./Cocktails";

class App extends Component {
    constructor() {
        super();

        this.state = {
            seo_title: "Search",
            cocktails: [
                {"name": "Wodka",
                "description": "4 cl Wodka"}
            ]
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }

    handleChange(event) {
        this.setState({[event.target.id]: event.target.value});
    }


    handleClick(event) {
        event.preventDefault();

        axios.get("http://localhost:8082/recipeDB").then(res => {
            const cocktails = res.data;
            this.setState({cocktails});
        })
    }


    render() {
        const { seo_title, cocktails } = this.state
        const listItems = cocktails.map((d) => <li key={d.name}>{d.name}</li>);
        return (
            <BrowserRouter>

             <div>
                 <ul>
                     <li>
                         <Link to="/">Home</Link>
                     </li>
                     <li>
                         <Link to="/cocktails">Cocktails</Link>
                     </li>
                  </ul>
                 <Route exact path="/" component={Home}/>
                 <Route path="/cocktails:id" component={Cocktails}/>
                <form id="article-form">
                    <Input
                        text={seo_title}
                        label="seo title"
                        type="text"
                        id="seo_title"
                        value={seo_title}
                        handleChange={this.handleChange}
                    />
                </form>
                <button onClick={this.handleClick} type="submit">Get Cocktail</button>
                <ul style={listItems.length ? {} : { display: 'none' }}>{listItems}</ul>
             </div>
            </BrowserRouter>
        )}
}


export default App;