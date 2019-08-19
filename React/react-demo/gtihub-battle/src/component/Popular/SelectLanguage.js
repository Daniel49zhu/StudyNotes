import React from 'react'
const jsonLanguages = require('./language.json')

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
    return (
      <ul className="languages">
        {jsonLanguages.map(data => {
          let lang = data.name
          return (
            <li
              className={lang === this.props.selectedLanguage ? 'selected' : ''}
              onClick={() => this.props.onSelect(lang)}
              key={lang}
            >
              {lang}
            </li>)
        })}
      </ul>
    );
  }
}