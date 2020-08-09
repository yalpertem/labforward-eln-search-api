import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import BackToMainPageButton from "../buttons/BackToMainPageButton";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(2),
      textAlign: "center",
    },
  },
  title: {
    marginTop: "1ch",
  },
}));

const AddEntryForm = (props) => {
  const classes = useStyles();

  return (
    <>
      <Container maxWidth="sm">
        <form className={classes.root} noValidate autoComplete="off">
          <Typography variant="h4" className={classes.title}>
            Create new entry
          </Typography>

          <div>
            <TextField
              id="new-entry-title"
              label="Entry title"
              variant="outlined"
              fullWidth={true}
              value={props.newTitle}
              onChange={props.handleTextInputChange("newTitle")}
            />
          </div>
          <div>
            <TextField
              id="new-entry-content"
              label="Entry content"
              fullWidth={true}
              multiline
              rows={16}
              variant="outlined"
              value={props.newContent}
              onChange={props.handleTextInputChange("newContent")}
            />
          </div>
          <div>
            <Button
              onClick={props.addEntryClicked}
              variant="contained"
              color="primary"
            >
              Create
            </Button>
          </div>
        </form>
      </Container>
      <BackToMainPageButton clicked={props.backButtonClicked} />
    </>
  );
};

export default AddEntryForm;
