import React from 'react';
import './App.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Nav from './Nav';
import Home from './Home';
import Popular from '../Popular/Popular';
import Search from '../Search/Search';
import Battle from '../Battle/Battle';


function App() {
  return (
    <BrowserRouter>
        <div className="container">
          <Nav />

          <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/battle" component={Battle} />
            <Route path="/popular" component={Popular} />
            <Route path="/search" component={Search} />
          </Switch>
        </div>
    </BrowserRouter>
  );
}

export default App;
