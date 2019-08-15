import React from 'react'
import '../style/SearchInput.css'

export default class SearchInput extends React.Component {

    handleChange = (event)=> {
        this.props.textChange(event);
    }



    render=()=>{
        return (
            <div className="component-search-input">
            <div>
              <input onChange={this.handleChange} />
            </div>
          </div>
        )
    }
}