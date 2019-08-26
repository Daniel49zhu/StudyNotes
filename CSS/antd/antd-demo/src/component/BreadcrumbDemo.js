import React from 'react'
import { Breadcrumb, Icon } from 'antd';

class BreadcrumbDemo extends React.Component {
    render = () => {
        return (
            <div className="container">
                <Breadcrumb>
                    <Breadcrumb.Item href="">
                        <Icon type="home" />
                    </Breadcrumb.Item>
                    <Breadcrumb.Item href="">
                        <Icon type="user" />
                        <span>Application List</span>
                    </Breadcrumb.Item>
                    <Breadcrumb.Item>Application</Breadcrumb.Item>
                
                </Breadcrumb>
            </div>
        );
    }
}

export default BreadcrumbDemo;