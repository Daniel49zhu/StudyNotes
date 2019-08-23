import React from 'react';
import { Row, Col } from 'antd';

class GridDemo extends React.Component {
    render = () => {

        const backStyle = {
            background: "#00a0e9",
            border: "1px solid #ececec"
        }

        return (
            <div>
                <div className="container">
                    <Row>
                        <Col span={12} style={backStyle}>col-12</Col>
                        <Col span={12} style={backStyle}>col-12</Col>
                    </Row>
                    <Row>
                        <Col span={8} style={backStyle}>col-8</Col>
                        <Col span={8} style={backStyle}>col-8</Col>
                        <Col span={8} style={backStyle}>col-8</Col>
                    </Row>
                    <Row>
                        <Col span={6} style={backStyle}>col-6</Col>
                        <Col span={6} style={backStyle}>col-6</Col>
                        <Col span={6} style={backStyle}>col-6</Col>
                        <Col span={6} style={backStyle}>col-6</Col>
                    </Row>
                </div>

                <div className="gutter-example container">
                    <Row gutter={16}>
                        <Col className="gutter-row" span={6}>
                            <div className="gutter-box">col-6</div>
                        </Col>
                        <Col className="gutter-row" span={6}>
                            <div className="gutter-box">col-6</div>
                        </Col>
                        <Col className="gutter-row" span={6}>
                            <div className="gutter-box">col-6</div>
                        </Col>
                        <Col className="gutter-row" span={6}>
                            <div className="gutter-box">col-6</div>
                        </Col>
                    </Row>
                </div>
            </div>
        );
    }
}

export default GridDemo