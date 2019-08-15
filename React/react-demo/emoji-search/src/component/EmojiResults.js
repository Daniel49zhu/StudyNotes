import React from 'react'
import EmojiResultRow from './EmojiResultRow'

export default class EmojiResults extends React.Component {
    render=()=>{
        return (
            <div className="component-emoji-results">
            {this.props.emojiData.map(emojiData => (
              <EmojiResultRow
                key={emojiData.title}
                symbol={emojiData.symbol}
                title={emojiData.title}
              />
            ))}
          </div>
        )
    }
}