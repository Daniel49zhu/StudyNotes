import React from 'react';
import { Button, Radio, Icon } from 'antd';
import './App.css';
const ButtonGroup = Button.Group;

class ButtonDemo extends React.Component {
    state = {
        size: 'large',
    }

    handleSizeChange = e => {
        this.setState({ size: e.target.value });
    };

    render = () => {
        const { size } = this.state;
        return (
            <div className="App">
                <div className="container">
                    <Button type="primary">Primary</Button>
                    <Button>Default</Button>
                    <Button type="dashed">Dashed</Button>
                    <Button type="danger">Danger</Button>
                    <Button type="link">Link</Button>
                </div>
                <div className="container">
                    <Radio.Group value={size} onChange={this.handleSizeChange}>
                        <Radio.Button value="large">Large</Radio.Button>
                        <Radio.Button value="default">Default</Radio.Button>
                        <Radio.Button value="small">Small</Radio.Button>
                    </Radio.Group>
                </div>
                <div className="container">
                    <Button type="primary" size={size}>
                        Primary
              </Button>
                    <Button size={size}>Normal</Button>
                    <Button type="dashed" size={size}>
                        Dashed
              </Button>
                    <Button type="danger" size={size}>
                        Danger
              </Button>
                    <Button type="link" size={size}>
                        Link
              </Button>
                    <br />
                    <Button type="primary" shape="circle" icon="download" size={size} />
                    <Button type="primary" shape="round" icon="download" size={size}>
                        Download
              </Button>
                    <Button type="primary" icon="download" size={size}>
                        Download
              </Button>
                </div>
                <div className="container" >
                    <Button.Group size={size}>
                        <Button type="primary">
                            <Icon type="left" />
                            Backward
                </Button>
                        <Button type="primary">
                            Forward
                  <Icon type="right" />
                        </Button>
                    </Button.Group>
                </div>
                <div className="container">
                    <Button type="primary" shape="circle" icon="search" />
                    <Button type="primary" icon="search">
                        Search
              </Button>
                    <Button shape="circle" icon="search" />
                    <Button icon="search">Search</Button>
                    <br />
                    <Button shape="circle" icon="search" />
                    <Button icon="search">Search</Button>
                    <Button type="dashed" shape="circle" icon="search" />
                    <Button type="dashed" icon="search">
                        Search
              </Button>
                </div>
                <div className="container">

                    <Button type="primary">Primary</Button>
                    <Button type="primary" disabled>
                        Primary(disabled)
              </Button>
                    <br />
                    <Button>Default</Button>
                    <Button disabled>Default(disabled)</Button>
                    <br />
                    <Button type="dashed">Dashed</Button>
                    <Button type="dashed" disabled>
                        Dashed(disabled)
              </Button>
                    <br />
                    <Button type="link">Link</Button>
                    <Button type="link" disabled>
                        Link(disabled)
              </Button>
                    <div style={{ padding: '8px 8px 0 8px', background: 'rgb(190, 200, 200)' }}>
                        <Button ghost>Ghost</Button>
                        <Button ghost disabled>
                            Ghost(disabled)
                </Button>
                    </div>
                </div>

                <div className="container">
                    <Button type="primary" block>
                        Primary
        </Button>
                    <Button block>Default</Button>
                    <Button type="dashed" block>
                        Dashed
        </Button>
                    <Button type="danger" block>
                        Danger
        </Button>
                    <Button type="link" block>
                        Link
        </Button>
                </div>

                <div className="contaniner">
                    <h4>Basic</h4>
                    <ButtonGroup>
                        <Button>Cancel</Button>
                        <Button>OK</Button>
                    </ButtonGroup>
                    <ButtonGroup>
                        <Button disabled>L</Button>
                        <Button disabled>M</Button>
                        <Button disabled>R</Button>
                    </ButtonGroup>
                    <ButtonGroup>
                        <Button>L</Button>
                        <Button>M</Button>
                        <Button>R</Button>
                    </ButtonGroup>

                    <h4>With Icon</h4>
                    <ButtonGroup>
                        <Button type="primary">
                            <Icon type="left" />
                            Go back
          </Button>
                        <Button type="primary">
                            Go forward
            <Icon type="right" />
                        </Button>
                    </ButtonGroup>
                    <ButtonGroup>
                        <Button type="primary" icon="cloud" />
                        <Button type="primary" icon="cloud-download" />
                    </ButtonGroup>
                </div>
            </div>
        );
    }
}

export default ButtonDemo;