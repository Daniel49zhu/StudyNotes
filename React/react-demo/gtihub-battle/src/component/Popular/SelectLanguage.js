import React from 'react'
import {jsonLanguages} from './language.json'

export default class SelectLanguage extends React.Component {
    languages = [
        'All',
        'Javascript',
        'Java',
        'CSS',
        'Python',
        'Shell',
        'Go'
    ];

    render = () => {
      debugger
        return (
            <ul className="languages">
            {this.languages.map(lang => (
              <li
              className={lang === this.props.selectedLanguage ? 'selected' : ''}
              onClick={()=>this.props.onSelect(lang)}
              key={lang}
              >
                {lang}
              </li>
            ))}
          </ul>
        );
    }
}