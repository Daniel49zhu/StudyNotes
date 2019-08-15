import React from 'react'
import SelectLanguage from './SelectLanguage';
import Loading from '../Reusable/Loading';

export default class Popular extends React.Component {

    state = {
        selectedLanguage: 'All',
        repos: null
    }

    updateLanguage = (lang)=> {
        this.setState({
          selectedLanguage: lang,
          repos: null
        });
    }

    render = ()=> {
        return (
            <div>
                <SelectLanguage 
                    selectedLanguage={this.state.selectedLanguage}
                    onSelect={this.updateLanguage}
                />

                {!this.state.repos? (<Loading speed="250"/>):(<Loading speed="250"/>)}
            </div>
        )
    }
}