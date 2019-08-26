import React from 'react';
import './App.css';
import SideBar from './SideBar';
import { Layout } from 'antd';
const { Header, Footer, Sider, Content } = Layout;


class App extends React.Component {
  render = ()=> {
    return (
      <SideBar></SideBar>
    )
  }
}

export default App;
