import React from 'react'
import SelectLanguage from './SelectLanguage';
import Loading from '../Reusable/Loading';
import { fetchPopularRepos } from '../../utils/api';
import RepoGrid from './RepoGrid'

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

    fetchPopularRepos(lang).then(
        function(repos) {
        this.setState(function() {
            return { repos: repos };
        });
        }.bind(this));
    }

    render = ()=> {
        return (
            <div>
                <SelectLanguage 
                    selectedLanguage={this.state.selectedLanguage}
                    onSelect={this.updateLanguage}
                />

                {!this.state.repos? (
                    <Loading/>
                    )   :   (
                    <RepoGrid repos={this.state.repos} />
                ) }
            </div>
        )
    }
}