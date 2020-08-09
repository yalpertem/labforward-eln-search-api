import React from "react";
import Fab from "@material-ui/core/Fab";
import AddIcon from "@material-ui/icons/Add";
import { makeStyles } from "@material-ui/core/styles";

const AddScreenButton = (props) => {
  const useStyles = makeStyles((theme) => ({
    entryButtonStyle: {
      position: "fixed",
      bottom: theme.spacing(2),
      right: theme.spacing(24),
    },
  }));

  const classes = useStyles();

  return (
    <>
      <Fab
        onClick={props.clicked}
        color="primary"
        variant="extended"
        aria-label="add"
        className={classes.entryButtonStyle}
      >
        <AddIcon /> Add New Entry
      </Fab>
    </>
  );
};

export default AddScreenButton;
