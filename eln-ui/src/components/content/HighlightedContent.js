import React from "react";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(2),
    },
  },
  exactMatch: {
    backgroundColor: "#357a38",
    color: "#fff",
  },
  similarMatch: {
    backgroundColor: "#b28704",
    color: "#fff",
  },
}));

const flattenSearchResult = (searchResult) => {
  if (
    searchResult === null ||
    searchResult.searchResultItems === null ||
    !Array.isArray(searchResult.searchResultItems)
  ) {
    return null;
  }

  const params = searchResult.searchResultItems
    .map((item) => {
      const firstFlatten = item.occurrences.map((occurrence) => {
        return {
          word: item.word,
          distance: item.distance,
          occurrence: occurrence,
        };
      });
      return firstFlatten;
    })
    .flat(1)
    .sort((a, b) => (a.occurrence < b.occurrence ? 1 : -1));
  return params;
};

// iterate the text from the end to the beginning to highlight parts
const getHighlightedParts = (text, flattenedResult, classes) => {
  if (
    flattenedResult === null ||
    !Array.isArray(flattenedResult) ||
    flattenedResult.length === 0
  ) {
    return text;
  }

  let lastPosition = text.length - 1;
  const parts = [];
  for (let i = 0; i < flattenedResult.length; i++) {
    const { word, distance, occurrence } = flattenedResult[i];
    const endOfWord = occurrence + word.length;
    let rest = text.substr(endOfWord, lastPosition - endOfWord);
    lastPosition = occurrence;
    parts.push({
      spanned: word,
      rest: rest,
      distance: distance,
    });
  }
  parts.push({
    spanned: "",
    rest: text.substr(0, lastPosition),
    distance: -1,
  });
  parts.reverse();

  const highlighted = parts.map((part, index) => {
    const highlightClass =
      part.distance === 0
        ? classes.exactMatch
        : part.distance === 1
        ? classes.similarMatch
        : classes.noMatch;
    return (
      <span key={index}>
        <span className={highlightClass}>{part.spanned}</span>
        <span>{part.rest}</span>
      </span>
    );
  });

  return highlighted;
};

const HighlightedContent = (props) => {
  const classes = useStyles();
  let flattenedResult = flattenSearchResult(props.searchResult);
  const highlighted = getHighlightedParts(props.text, flattenedResult, classes);

  return (
    <>
      <Typography component="p">{highlighted}</Typography>
    </>
  );
};

export default HighlightedContent;
