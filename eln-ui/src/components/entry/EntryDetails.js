import React from "react";
import Container from "@material-ui/core/Container";
import Typography from "@material-ui/core/Typography";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import SearchIcon from "@material-ui/icons/Search";
import { makeStyles } from "@material-ui/core/styles";
import SearchResults from "../content/SearchResults";
import HighlightedContent from "../content/HighlightedContent";
import BackToMainPageButton from "../buttons/BackToMainPageButton";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(2),
    },
  },
  title: {
    marginTop: "1ch",
  },
}));

const EntryDetails = (props) => {
  const classes = useStyles();
  return (
    <>
      <Container maxWidth="sm" className={classes.root}>
        <Typography>
          You can search for a word in your document to find exact and similar
          matches. Please give it a try.
        </Typography>
        <TextField
          id="search-word"
          label="Search for a word"
          variant="outlined"
          size="small"
          value={props.searchWord}
          onChange={props.handleTextInputChange("searchWord")}
        />
        <Button
          variant="contained"
          color="primary"
          onClick={props.searchWordClicked}
        >
          <SearchIcon />
          Search
        </Button>
        <SearchResults
          searchResult={props.searchResult}
          show={props.showSearchResult}
          searchWord={props.searchWord}
          lastSearchWord={props.lastSearchWord}
        />

        <Card>
          <CardContent>
            <Typography gutterBottom variant="h4" component="h2">
              {props.entryTitle}
            </Typography>

            <HighlightedContent
              text={props.entryContent}
              searchResult={props.searchResult}
            />
          </CardContent>
        </Card>
      </Container>
      <BackToMainPageButton clicked={props.backButtonClicked} />
    </>
  );
};

export default EntryDetails;
