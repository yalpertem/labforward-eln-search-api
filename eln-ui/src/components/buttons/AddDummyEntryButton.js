import React from "react";
import Fab from "@material-ui/core/Fab";
import AddIcon from "@material-ui/icons/Add";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  entryButtonStyle: {
    position: "fixed",
    bottom: theme.spacing(2),
    right: theme.spacing(4),
  },
  extendedIcon: {
    marginRight: theme.spacing(1),
  },
}));

const AddDummyEntryButton = (props) => {
  const classes = useStyles();

  return (
    <>
      <Fab
        onClick={props.clicked}
        variant="extended"
        color="secondary"
        aria-label="addAll"
        className={classes.entryButtonStyle}
      >
        <AddIcon className={classes.extendedIcon} /> Add Dummy
      </Fab>
    </>
  );
};

export default AddDummyEntryButton;
