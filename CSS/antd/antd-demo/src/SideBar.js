import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import './index.css';
import { Menu, Icon } from 'antd';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import ButtonDemo from './component/ButtonDemo';
import IconDemo from './component/IconDemo';
import GridDemo from './component/GridDemo';

const { SubMenu } = Menu;

class SideBar extends React.Component {
    handleClick = e => {
        console.log('click ', e);
    };

    render() {
        return (
            <Router>
                <div style={{ display: "flex" }}>
                    <div style={{
                        padding: "0 10px 0 0"
                    }}>
                        <Menu
                            onClick={this.handleClick}
                            style={{ width: 256 }}
                            defaultSelectedKeys={['1']}
                            defaultOpenKeys={['sub1']}
                            mode="inline"
                        >
                            <SubMenu
                                key="sub1"
                                title={
                                    <span>
                                        <Icon type="setting" />
                                        <span>Component</span>
                                    </span>
                                }
                            >
                                <Menu.ItemGroup key="g1" title="Common">
                                    <Menu.Item key="1">
                                        <Link to="/button">Button</Link>
                                    </Menu.Item>
                                    <Menu.Item key="2">
                                        <Link to="/icon" >Icon</Link>
                                    </Menu.Item>
                                </Menu.ItemGroup>
                                <Menu.ItemGroup key="g2" title="Layout">
                                    <Menu.Item key="3">
                                        <Link to="/grid">Grid</Link>
                                    </Menu.Item>
                                    <Menu.Item key="4">Layout</Menu.Item>
                                </Menu.ItemGroup>
                            </SubMenu>
                        </Menu>
                    </div>
                    <div style={{ flex: 1, padding: "10px" }}>
                        <Route path="/button" component={ButtonDemo}></Route>
                        <Route path="/icon" component={IconDemo}></Route>
                        <Route path="/grid" component={GridDemo}></Route>
                    </div>
                </div>
            </Router>
        );
    }
}

export default SideBar;