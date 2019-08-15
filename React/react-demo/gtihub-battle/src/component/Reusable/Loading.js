import React from 'react';

var styles = {
    content: {
      textAlign: 'center',
      fontSize: '35px'
    }
  };

export default class Loading extends React.Component {

    state = {
        text: this.props.text,
        speed: this.props.speed
    };

    componentDidMount() {
        var stopper = this.props.text + '...';
        this.interval = window.setInterval(
          function() {
            if (this.state.text === stopper) {
              this.setState(function() {
                return {
                  text: this.props.text
                };
              });
            } else {
              this.setState(function(prevState) {
                return {
                  text: prevState.text + '.'
                };
              });
            }
          }.bind(this),
          this.props.speed
        );
      }
      componentWillUnmount() {
        window.clearInterval(this.interval);
      }

    render = ()=>{
        return (
            <p style={styles.content}>{this.state.text}</p>
        );
    }
}

Loading.defaultProps = {
    text: 'Loading',
    speed: 300
};