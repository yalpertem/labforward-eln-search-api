import React from "react";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import * as Const from "../../util/Constants";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {},
  },
  warning: {
    color: "#ff1744",
  },
  exactMatch: {
    color: "#357a38",
  },
  similarMatch: {
    color: "#b28704",
  },
}));

const getSearchResultStatus = (show, searchResult) => {
  if (show && searchResult != null) {
    if (searchResult.searchResultItems.length === 0) {
      return Const.SEARCH_RESULT_WARNING;
    } else {
      return Const.SEARCH_RESULT_SHOW;
    }
  } else {
    return Const.SEARCH_RESULT_HIDE;
  }
};

const SearchResults = (props) => {
  const classes = useStyles();
  const status = getSearchResultStatus(props.show, props.searchResult);

  switch (status) {
    case Const.SEARCH_RESULT_HIDE:
      return "";

    case Const.SEARCH_RESULT_WARNING:
      return (
        <Typography
          variant="subtitle2"
          gutterBottom
          className={classes.warning}
        >
          No results found for "{props.lastSearchWord}"
        </Typography>
      );

    case Const.SEARCH_RESULT_SHOW:
      return props.searchResult.searchResultItems.map((result) => {
        const isExact = result.distance === 0;
        return (
          <Typography
            key={result.word}
            variant="subtitle2"
            gutterBottom
            className={isExact ? classes.exactMatch : classes.similarMatch}
          >
            {isExact ? "Exact" : "Similar"} match: "{result.word}" occurs{" "}
            {result.frequency} times.
          </Typography>
        );
      });

    default:
      return "";
  }
};

export default SearchResults;
