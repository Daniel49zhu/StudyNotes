import React from 'react'

export default class SelectLanguage extends React.Component {
    languages = [
        'All',
        'Javascript',
        'Ruby',
        'Java',
        'CSS',
        'Python',
        'PHP',
        'Shell'
    ];

    render = () => {
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